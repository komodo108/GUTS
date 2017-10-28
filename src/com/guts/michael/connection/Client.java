package com.guts.michael.connection;

import com.guts.michael.game.Game;
import com.guts.michael.game.IEntity;
import com.guts.michael.game.IMap;

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
    private Socket s;
    private BufferedReader read;
    private BufferedWriter write;

    public Client(String ip) throws UnknownHostException {
        this.ip = InetAddress.getByName(ip);
    }

    @Override
    public void run() {
        Game.getInstance().addObserver(this);

        try {
            s = new Socket(ip, 26789);
            write = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
            read = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // Main client loop
            while (true) {
                try {
                    // Read packet
                    IPacket packet = Packet.readNextPacket(read);

                    if (packet == null) {
                        s.close();
                        break;
                    }

                    System.out.println("client received " + packet.getType().name());

                    // Handle Packet
                    if (packet instanceof MapPacket) {
                        map = ((MapPacket) packet).getMap();
                    } else if (packet instanceof EntityPacket) {
                        entity = ((EntityPacket) packet).getEntity();
                    } else if (packet instanceof FinishedPacket) {
                        Game.getInstance().setGame(new Game(map, entity));
                    }


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
        if (arg != null && arg.equals("player1finished")) {
            try {
                write.write(new MovePacket(Game.getInstance().getLastMoveDirection(), Game.getInstance().getLastMoveAmount()).asPacketString());
                write.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setChanged();
        notifyObservers();
    }
}
