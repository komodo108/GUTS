package com.guts.michael.views;

import com.guts.michael.connection.Client;
import com.guts.michael.game.KeyListen;
import com.guts.michael.game.render.ClientRender;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.net.UnknownHostException;
import java.util.Observer;

public class ClientView implements Observer {

    private JFrame frame;
    private ClientRender clientRender;
    private KeyListen keyListen = new KeyListen();

    private final int DEFAULT_WIDTH = 736;
    private final int DEFAULT_HEIGHT = 758;

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

        Client client = new Client(ip);
        client.addObserver(this);
        new Thread(client).start();

        frame.addKeyListener(keyListen);

        frame.repaint();
    }

    @Override
    public void update(java.util.Observable o, Object arg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                clientRender.paintComponents(clientRender.getGraphics());
                clientRender.repaint();
            }
        });
    }
}
