package com.guts.michael.game.render;

import com.guts.michael.game.Direction;
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
        super.paintComponent(g);
        if(c != 0) {
            g.clearRect(0, 0, getWidth(), getHeight());
            try {
                loader = new SpriteLoader(32, 32, 8, 8);
                for(int x = 0; x < game.getMap().getTiles().length; x++) {
                    for(int y = 0; y < game.getMap().getTiles()[x].length; y++) {
                        //if(game.getPlayer().getX() == x && game.getPlayer().getY() == y) {
                            System.out.println("Tile is: " + game.getMap().getTiles()[x][y].getType());
                            switch (game.getMap().getTiles()[x][y].getType()) {
                                case WALL:
                                    //0
                                    g.drawImage(loader.getSprites(0), x * 32, y * 32, null);
                                    break;
                                case SPACE:
                                    //8
                                    g.drawImage(loader.getSprites(8), x * 32, y * 32, null);
                                    break;
                                case SAND:
                                    //8 (for now)
                                    g.drawImage(loader.getSprites(8), x * 32, y * 32, null);
                                    break;
                                case GRASS:
                                    //16
                                    g.drawImage(loader.getSprites(16), x * 32, y * 32, null);
                                    break;
                            }
                        if(game.getPlayer().getX() == x && game.getPlayer().getY() == y) {
                            switch (game.getPlayer().getOrientation()) {
                                case RIGHT:
                                    g.drawImage(loader.getSprites(17), x * 32, y * 32, null);
                                    break;
                                case UP:
                                    g.drawImage(loader.getSprites(2), x * 32, y * 32, null);
                                    break;
                                case DOWN:
                                    g.drawImage(loader.getSprites(9), x * 32, y * 32, null);
                                    break;
                                case LEFT:
                                    g.drawImage(loader.getSprites(25), x * 32, y * 32, null);
                                    break;

                            }
                        }
                        //}
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
