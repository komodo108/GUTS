package com.guts.michael.connection;

import com.guts.michael.game.IMap;
import com.guts.michael.game.Map;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observer;

public class Server extends java.util.Observable implements Runnable {

    public Server(Observer o) {
        addObserver(o);
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

                    //ServerRender
                    setChanged();
                    notifyObservers();

                } catch (CorruptedPacketException e) {
                    System.out.println("received corrupted packet");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
