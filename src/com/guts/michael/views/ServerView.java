package com.guts.michael.views;

import com.guts.michael.connection.Server;
import com.guts.michael.game.KeyListen;
import com.guts.michael.game.MouseListen;
import com.guts.michael.game.render.ServerRender;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ServerView implements Observer {

    private JFrame frame;
    private ServerRender serverRender;
    private MouseListen mouseListen = new MouseListen();

    private final int DEFAULT_WIDTH = 736;
    private final int DEFAULT_HEIGHT = 736;

    public ServerView() {
        serverRender = new ServerRender();
        serverRender.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        frame = new JFrame(Views.DEFAULT_NAME);
        frame.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setResizable(Views.RESIZABLE);

        frame.add(serverRender);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        Server server = new Server();
        server.addObserver(this);
        new Thread(server).start();

        frame.addMouseListener(mouseListen);

        frame.repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                serverRender.paintComponents(serverRender.getGraphics());
                serverRender.repaint();
            }
        });
    }
}
