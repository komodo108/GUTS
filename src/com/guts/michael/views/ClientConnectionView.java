package com.guts.michael.views;

import com.guts.michael.connection.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

public class ClientConnectionView {

    private JFrame frame;
    private JTextField ip;
    private JButton go;
    private JLabel error;

    private Client connection;

    public ClientConnectionView() {
        init();
    }

    private void init() {
        error = new JLabel();
        error.setBounds(Views.DEFAULT_WIDTH/2 - 40, Views.DEFAULT_HEIGHT/2 - 100, 80, 90);
        error.setText(format("Enter Server IP"));

        ip = new JTextField();
        ip.setBounds((Views.DEFAULT_WIDTH/2 - 120), (Views.DEFAULT_HEIGHT/2 - 20), 240, 20);
        ip.setMinimumSize(new Dimension(240, 20));

        go = new JButton("Go");
        go.setMaximumSize(new Dimension(60, 20));
        go.setBounds((Views.DEFAULT_WIDTH/2 - 30), (Views.DEFAULT_HEIGHT/2 + 22), 60, 20);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientView client = new ClientView(ip.getText());
                } catch (UnknownHostException e1) {
                    error.setText(format("There was an error"));
                    e1.printStackTrace();
                }
            }
        });

        frame = new JFrame(Views.DEFAULT_NAME);
        frame.setSize(Views.DEFAULT_SIZE);
        frame.setResizable(Views.RESIZABLE);

        frame.add(ip);
        frame.add(go);
        frame.add(error);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private String format(String text) {
        return "<html><div><p style='text-align: center;'>" + text + "</p></div></html>";
    }
}
