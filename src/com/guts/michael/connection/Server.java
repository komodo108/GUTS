package com.guts.michael.connection;

import com.guts.michael.Main;
import com.guts.michael.game.Game;
import com.guts.michael.game.IEntity;
import com.guts.michael.game.IllegalMoveException;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

public class Server extends java.util.Observable implements Runnable, Observer {

    private BufferedWriter write;

    @Override
    public void run() {
        Game.getInstance().addObserver(this);
        Game.getInstance().setClientTurn(true);

        try {

            ServerSocket serverSocket = new ServerSocket(Main.PORT);
            Socket s = serverSocket.accept();
            BufferedReader read = new BufferedReader(new InputStreamReader(s.getInputStream()));
            write = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));

            // Write initial map and state
            writeCurrentGame();

            setChanged();
            notifyObservers();

            while (true) {

                try {
                    // Read packet
                    IPacket packet = Packet.readNextPacket(read);

                    if (packet == null) {
                        System.out.println("server closed because it received null packet");
                        s.close();
                        break;
                    }

                    System.out.println("server received " + packet.getType().name());

                    if (packet instanceof MovePacket) {
                        MovePacket move = (MovePacket) packet;
                        try {
                            Game.getInstance().movePlayer(move.getAmount(), move.getDirection(), true);
                            Game.getInstance().setClientTurn(false);
                        } catch (IllegalMoveException e) {
                            System.err.println("illegal move, ignoring");
                        }
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
            write.write(new MapPacket(Game.getInstance().getMap()).asPacketString());
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writePlayer() {
        try {
            write.write(new EntityPacket(Game.getInstance().getPlayer()).asPacketString());
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeEnemies() {
        try {
            for (IEntity enemy : Game.getInstance().getEnemies()) {
                write.write(new EntityPacket(enemy).asPacketString());
            }
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeFinished() {
        try {
            write.write(new FinishedPacket().asPacketString());
            write.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCurrentGame() {
        writeMap();
        writePlayer();
        writeEnemies();
        writeFinished();
    }

    @Override
    public void update(Observable o, Object arg) {
        setChanged();
        notifyObservers();
        if (arg != null && arg.equals("player2finished")) {
            Game.getInstance().setClientTurn(true);
            writeCurrentGame();
        }
    }
}
