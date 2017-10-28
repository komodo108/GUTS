package com.guts.michael.connection;

import com.guts.michael.Views;
import com.guts.michael.game.IMap;
import com.guts.michael.game.Map;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    private JFrame frame;
    private JPanel panel;

    private final int DEFAULT_WIDTH = 800;
    private final int DEFAULT_HEIGHT = 800;

    public Server() {
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
            ServerSocket serverSocket = new ServerSocket(26789);
            Socket s = serverSocket.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // TODO: first thing should be to send the initial map
            IMap map = Map.generateRandom();
            write.write(new MapPacket(map).asPacketString());
            write.flush();

            while (true) {

                try {
                    // Read packet
                    IPacket packet = Packet.readNextPacket(read);

                    if (packet == null) {
                        s.close();
                        break;
                    }

                    System.out.println("received " + packet.getType().name());

                    if (packet instanceof MovePacket) {
                        // TODO: handle move packet here
                    }

                    //Render
                    render(panel.getGraphics());

                } catch (CorruptedPacketException e) {
                    System.out.println("received corrupted packet");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        g.drawLine(400, 0, 0, 400);
    }
}
