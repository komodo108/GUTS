package com.guts.michael.game.render;

import com.guts.michael.game.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ClientRender extends JPanel {

    private int c;
    private SpriteLoader loader;

    @Override
    protected void paintComponent(Graphics g) {
        if(c != 0) {
            try {
                loader = new SpriteLoader(32, 32, 8, 8);
                g.drawImage(loader.getSprites(0), 100, 100, null);
            } catch (IOException e) {
                System.err.println("Spritesheet not found!!");
                System.exit(1);
            }
        } else {
            String s = "Connecting...";
            g.setFont(new Font("Arial", 1, 24));
            g.drawString(s, getWidth()/2 - ((s.length()*24)/4), getHeight()/2 - 12);
            c++;
        }
    }

}
