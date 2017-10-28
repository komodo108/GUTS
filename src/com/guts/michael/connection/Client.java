package com.guts.michael.connection;

import com.guts.michael.Views;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

    private InetAddress ip;
    private JFrame frame;
    private JPanel panel;

    private final int DEFAULT_WIDTH = 400;
    private final int DEFAULT_HEIGHT = 400;

    public Client(String ip) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);

        panel = new JPanel();
        panel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);

        frame = new JFrame(Views.getDEFAULT_NAME());
        frame.setSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        frame.setResizable(Views.isRESIZABLE());

        frame.add(panel);

        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(ip, 26789);
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // com.guts.michael.Main client loop
            while (true) {

                // Read packet
                IPacket packet = Packet.readNextPacket(read);

                //Handle Packet
                if (packet instanceof MapPacket) {
                    // TODO: handle map packet here
                }

                //Render
                render(panel.getGraphics());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        g.drawLine(0, 0, 400, 400);
    }
}
