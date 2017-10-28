package com.guts.michael;

import com.guts.michael.connection.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;

public class ClientView {

    private static ClientView clientView;
    private JFrame frame;
    private JTextField ip;
    private JButton go;
    private JLabel error;

    private Client connection;

    public static ClientView getClientView() {
        if(clientView == null) {
            clientView = new ClientView();
            return clientView;
        } else {
            return clientView;
        }
    }

    private ClientView() {
        init();
    }

    private void init() {
        error = new JLabel();
        error.setBounds(Views.getDEFAULT_WIDTH()/2 - 40, Views.getDEFAULT_HEIGHT()/2 - 100, 80, 90);
        error.setText(format("Enter Server IP"));

        ip = new JTextField();
        ip.setBounds((Views.getDEFAULT_WIDTH()/2 - 120), (Views.getDEFAULT_HEIGHT()/2 - 20), 240, 20);
        ip.setMinimumSize(new Dimension(240, 20));

        go = new JButton("Go");
        go.setMaximumSize(new Dimension(60, 20));
        go.setBounds((Views.getDEFAULT_WIDTH()/2 - 30), (Views.getDEFAULT_HEIGHT()/2 + 22), 60, 20);
        go.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection = new Client(ip.getText());
                    connection.start();
                } catch (UnknownHostException e1) {
                    error.setText(format("There was an error"));
                    e1.printStackTrace();
                }
            }
        });

        frame = new JFrame(Views.getDEFAULT_NAME());
        frame.setSize(Views.getDEFAULT_SIZE());
        frame.setResizable(Views.isRESIZABLE());

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
