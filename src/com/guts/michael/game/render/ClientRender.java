package com.guts.michael.game.render;

import com.guts.michael.game.Game;

import java.awt.*;

public class ClientRender extends Render {

    private int c;

    //Sprite is 32*32

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(c != 0) {
            paintMap(g, true);
            paintPlayer(g);
            paintEnemies(g, true);
            if(Game.getInstance().isVictory()) paintVictory(g);
            if(Game.getInstance().isLose()) paintLose(g);
        } else {
            String s = "Connecting...";
            g.setFont(new Font("Arial", 1, 24));
            g.drawString(s, getWidth()/2 - ((s.length()*24)/4), getHeight()/2 - 12);
            c++;
        }
    }
}
