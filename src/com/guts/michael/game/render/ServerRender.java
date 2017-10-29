package com.guts.michael.game.render;

import com.guts.michael.game.Game;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerRender extends Render {

    private int c;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (c != 0) {
            paintMap(g, false);
            if (!Game.getInstance().isClientTurn()) {
                paintServerControls(g);
            }
            if(Game.getInstance().isVictory()) paintVictory(g);
           // paintPlayer(g);
        } else {
            try {
                String s = "Connect a client on: " + InetAddress.getLocalHost().getHostAddress();
                g.setFont(new Font("Arial", 1, 24));
                g.drawString(s, getWidth() / 2 - ((s.length() * 24) / 4), getHeight() / 2 - 12);
                c++;
            } catch (UnknownHostException e) {
                e.printStackTrace();
            }
        }
    }
}
