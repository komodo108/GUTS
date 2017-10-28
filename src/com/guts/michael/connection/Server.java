package com.guts.michael.connection;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(26789);
            Socket s = serverSocket.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // TODO: first thing should be to send the initial map

            // Main server loop
            while (true) {

                // Read packet
                IPacket packet = Packet.readNextPacket(read);

                if (packet instanceof MovePacket) {
                    // TODO: handle move packet here
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
