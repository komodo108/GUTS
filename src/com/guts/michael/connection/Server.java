package com.guts.michael.connection;

import com.guts.michael.game.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Server extends java.util.Observable implements Runnable, Observer {

    private Game game;
    private BufferedWriter write;

    public Server() {
        game = new Game(Map.generateRandom(), new Entity(0, EntityType.PLAYER, 4, 4, Direction.RIGHT));
    }

    @Override
    public void run() {
        game.addObserver(this);

        try {
            ServerSocket serverSocket = new ServerSocket(26789);
            Socket s = serverSocket.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            write = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // TODO: first thing should be to send the initial map
            // TODO: Also send the initial entity state, so the client can make a Game
            writeMap();
            writeEntity();

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
                        MovePacket move = (MovePacket) packet;
                        game.movePlayer(move.getAmount(), move.getDirection());
                    }

                } catch (CorruptedPacketException e) {
                    System.out.println("received corrupted packet");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMap() {
        try {
            write.write(new MapPacket(game.getMap()).asPacketString());
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEntity() {
        try {
            write.write(new EntityPacket(game.getPlayer()).asPacketString());
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void movePlayer(int amount, Direction direction) {
        game.movePlayer(amount, direction);
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
