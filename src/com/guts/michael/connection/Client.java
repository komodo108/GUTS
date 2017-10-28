package com.guts.michael.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class Client extends Observable implements Runnable {

    private InetAddress ip;

    public Client(String ip, Observer o) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);
        addObserver(o);
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

                //ServerRender
                setChanged();
                notifyObservers();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
