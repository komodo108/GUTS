package com.guts.michael.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connection extends Thread {

    private InetAddress ip;
    private BufferedReader read;

    public Connection(String ip) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(ip, 26789);
            read = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Main listening loop
            main: while (true) {

                // Read packet
                IPacket packet = readNextPacket(read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private IPacket readNextPacket(BufferedReader read) throws IOException {
        StringBuffer packetString = new StringBuffer();
        String line;
        while ((line = read.readLine()) != null && !line.equals("END")) {
            packetString.append(line + '\n');
        }
        return Packet.fromString(packetString.toString());
    }

}
