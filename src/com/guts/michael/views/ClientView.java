package com.guts.michael.views;

import com.guts.michael.connection.Client;
import com.guts.michael.game.render.Render;
import javafx.beans.Observable;

import javax.swing.*;
import java.awt.*;
import java.net.UnknownHostException;
import java.util.Observer;

public class ClientView implements Observer {

    private JFrame frame;
    private JPanel panel;
    private Render render = new Render();

    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 800;

    public ClientView(String ip) throws UnknownHostException {
        panel = new JPanel();
        panel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        frame = new JFrame(Views.DEFAULT_NAME);
        frame.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setResizable(Views.RESIZABLE);

        frame.add(panel);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        new Thread(new Client(ip, this)).start();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                render.render(panel.getGraphics(), false);
                frame.repaint();
            }
        });
    }
}
