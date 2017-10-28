package com.guts.michael.game.render;

import com.guts.michael.game.Game;
import com.guts.michael.game.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ClientRender extends JPanel {

    private int c;
    private SpriteLoader loader;
    private Game game;

    public void setGame(Game game) {
        this.game = game;
    }

    //Sprite is 32*32

    @Override
    protected void paintComponent(Graphics g) {
        if(c != 0) {
            g.setColor(Color.RED);
            g.fillRect(0, 0, 900, 900);
            try {
                loader = new SpriteLoader(32, 32, 8, 8);
                for(int i = 0; i < game.getMap().getTiles()[0].length; i++) {
                    for(int j = 0; j < game.getMap().getTiles().length; i++) {
                        if((int)(game.getPlayer().getX()/32) == i && (int)(game.getPlayer().getY()/32) == j) {
                            System.out.println("Tile is: " + game.getMap().getTiles()[i][j].getType());
                            switch (game.getMap().getTiles()[i][j].getType()) {
                                case WALL:
                                    //0
                                    g.drawImage(loader.getSprites(0), i * 32 + 100, j * 32 + 100, null);
                                    g.drawLine(100, 0, 100, 900);
                                    g.drawLine(0, 100, 900, 100);
                                    break;
                                case SPACE:
                                    //1
                                    g.drawImage(loader.getSprites(1), i * 32, j * 32, null);
                                    break;
                                case SAND:
                                    //1 (for now)
                                    g.drawImage(loader.getSprites(1), i * 32, j * 32, null);
                                    break;
                                case GRASS:
                                    //2
                                    g.drawImage(loader.getSprites(2), i * 32, j * 32, null);
                                    break;
                            }
                        }
                    }
                }
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
