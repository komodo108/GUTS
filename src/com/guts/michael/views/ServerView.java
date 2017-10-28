package com.guts.michael.views;

import com.guts.michael.connection.Server;
import com.guts.michael.game.render.Render;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ServerView implements Observer {

    private JFrame frame;
    private JPanel panel;
    private Render render = new Render();

    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 800;

    public ServerView() {
        panel = new JPanel();
        panel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        frame = new JFrame(Views.DEFAULT_NAME);
        frame.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setResizable(Views.RESIZABLE);

        frame.add(panel);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(new Server(this)).start();
    }

    @Override
    public void update(Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                render.render(panel.getGraphics(), true);
                frame.repaint();
            }
        });
    }
}
