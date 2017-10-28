package com.guts.michael.views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectorView {

    public static SelectorView selector;
    private JFrame frame;
    private JButton client, server;

    public static SelectorView getSelector() {
        if(selector == null) {
            selector = new SelectorView();
            return selector;
        } else {
            return selector;
        }
    }

    private SelectorView() {
        init();
    }

    private void init() {
        client = new JButton("Client");
        client.setBounds(Views.DEFAULT_WIDTH/2 - 85, Views.DEFAULT_HEIGHT/2 - 35, 80, 40);
        client.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Client Made!");
                new ClientConnectionView();
                frame.dispose();
            }
        });

        server = new JButton("Server");
        server.setBounds(Views.DEFAULT_WIDTH/2 + 5, Views.DEFAULT_HEIGHT/2 - 35, 80, 40);
        server.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("New Server Made!");
                new ServerView();
                frame.dispose();
            }
        });

        frame = new JFrame(Views.DEFAULT_NAME);
        frame.setSize(Views.DEFAULT_SIZE);
        frame.setResizable(Views.RESIZABLE);

        frame.add(client);
        frame.add(server);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
