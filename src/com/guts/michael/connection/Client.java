package com.guts.michael.connection;

import com.guts.michael.game.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Observer;

public class Client extends Observable implements Runnable, Observer {

    private InetAddress ip;
    private IMap map;
    private IEntity entity;

    //
    private static Game game = new Game(Map.generateRandom(), new Entity(0, EntityType.PLAYER, 4, 4, Direction.RIGHT));

    private int inital = 0;

    public Client(String ip) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);
    }

    //
    public static Game getGame() {
        return game;
    }

    @Override
    public void run() {
        game.addObserver(this);

        try {
            Socket s = new Socket(ip, 26789);
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            BufferedWriter write = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // Main client loop
            while (true) {
                try {
                    if (inital == 2) {
                        game = new Game(map, entity);
                    }

                    // Read packet
                    IPacket packet = Packet.readNextPacket(read);

                    if (packet == null) {
                        s.close();
                        break;
                    }

                    System.out.println("received " + packet.getType().name());

                    // Handle Packet
                    if (packet instanceof MapPacket && inital < 3) {
                        map = ((MapPacket) packet).getMap();
                        inital++;

                        setChanged();
                        notifyObservers();
                    } else if (packet instanceof EntityPacket && inital < 3) {
                        entity = ((EntityPacket) packet).getEntity();
                        inital++;

                        setChanged();
                        notifyObservers();
                    }

                    //Get user input


                } catch (CorruptedPacketException e) {
                    System.out.println("received corrupted packet");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
    }
}
