package com.guts.michael.views;

import com.guts.michael.connection.Client;
import com.guts.michael.game.render.ClientRender;
import com.guts.michael.game.render.ServerRender;

import javax.swing.*;
import java.awt.*;
import java.net.UnknownHostException;
import java.util.Observer;

public class ClientView implements Observer {

    private JFrame frame;
    private ClientRender clientRender;

    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 800;

    public ClientView(String ip) throws UnknownHostException {
        clientRender = new ClientRender();
        clientRender.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        frame = new JFrame(Views.DEFAULT_NAME);
        frame.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setResizable(Views.RESIZABLE);

        frame.add(clientRender);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(new Client(ip, this)).start();

        frame.repaint();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                clientRender.paintComponents(clientRender.getGraphics());
                clientRender.repaint();
                frame.repaint();
            }
        });
    }
}
