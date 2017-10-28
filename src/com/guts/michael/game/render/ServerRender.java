package com.guts.michael.game.render;

import com.guts.michael.game.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;

public class ServerRender extends JPanel {

    private int c;
    private SpriteLoader loader;

    @Override
    protected void paintComponent(Graphics g) {
        if(c != 0) {
            try {
                loader = new SpriteLoader(32, 32, 8, 8);
                g.drawImage(loader.getSprites(1), 100, 100, null);
            } catch (IOException e) {
                System.err.println("Spritesheet not found!!");
            }
        } else {
            try {
                String s = "Connect a client on: " + InetAddress.getLocalHost().getHostAddress();
                g.setFont(new Font("Arial", 1, 24));
                g.drawString(s, getWidth() / 2 - ((s.length() * 24)/4), getHeight() / 2 - 12);
                c++;
            } catch (Exception e) { }
        }
    }

}
