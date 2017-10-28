package com.guts.michael.game.render;

import java.awt.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerRender extends Render {

    private int c;

    @Override
    protected void paintComponent(Graphics g) {
        if (c != 0) {
            paintMap(g);
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
