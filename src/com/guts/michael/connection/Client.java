package com.guts.michael.connection;

import com.guts.michael.game.*;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class Client extends Observable implements Runnable, Observer {

    private InetAddress ip;
    private IMap map;
    private IEntity player;
    private List<IEntity> enemies = new LinkedList<>();
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
                        IEntity entity = ((EntityPacket) packet).getEntity();
                        if (entity.getType() == EntityType.PLAYER) {
                            player = entity;
                        } else if (entity.getType() == EntityType.ENEMY) {
                            enemies.add(entity);
                        }
                    } else if (packet instanceof FinishedPacket) {
                        Game.getInstance().setClientTurn(true);
                        Game.getInstance().setGame(map, player, enemies);
                        enemies = new LinkedList<>();
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
            if (Game.getInstance().getLastMoveAmount() == 0) {
                Game.getInstance().setClientTurn(true);
                return;
            }
            try {
                Game.getInstance().setClientTurn(false);
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
