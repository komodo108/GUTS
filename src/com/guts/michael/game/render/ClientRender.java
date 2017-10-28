package com.guts.michael.game.render;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class ClientRender extends Render implements Observer {

    private int c;

    //Sprite is 32*32

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(c != 0) {
            paintMap(g);
        } else {
            String s = "Connecting...";
            g.setFont(new Font("Arial", 1, 24));
            g.drawString(s, getWidth()/2 - ((s.length()*24)/4), getHeight()/2 - 12);
            c++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
