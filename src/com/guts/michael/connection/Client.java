package com.guts.michael.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client extends Thread {

    private InetAddress ip;
    public Client(String ip) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(ip, 26789);
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Main client loop
            while (true) {

                // Read packet
                IPacket packet = Packet.readNextPacket(read);

                // Handle Packet
                if (packet instanceof MapPacket) {
                    // TODO: handle map packet here
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
