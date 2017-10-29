package com.guts.michael.game.render;

import com.guts.michael.game.Game;
import com.guts.michael.game.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Render extends JPanel {

    private SpriteLoader loader;

    public void paintMap(Graphics g, boolean isClient) {
        //isClient = false;
        Game game = Game.getInstance();
        g.clearRect(0, 0, getWidth(), getHeight());
        try {
            loader = new SpriteLoader(32, 32, 8, 8);
            for (int x = 0; x < game.getMap().getTiles().length; x++) {
                for (int y = 0; y < game.getMap().getTiles()[x].length; y++) {
                    if (!isClient || (Math.abs(game.getPlayer().getX() - x) < 1 && Math.abs(game.getPlayer().getY() - y) < 1)) {
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
                                //24
                                g.drawImage(loader.getSprites(24), x * 32, y * 32, null);
                                break;
                            case GRASS:
                                //16
                                g.drawImage(loader.getSprites(16), x * 32, y * 32, null);
                                break;
                        }
                    } else if (isClient) {
                        g.setColor(Color.BLACK);
                        g.fillRect(x*32, y*32, 32, 32);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("spritesheet not found!!");
            System.exit(1);
        }
    }

    public void paintPlayer(Graphics g) {
        Game game = Game.getInstance();
        for(int x = 0; x < game.getMap().getTiles().length; x++) {
            for (int y = 0; y < game.getMap().getTiles()[x].length; y++) {
                if (game.getPlayer().getX() == x && game.getPlayer().getY() == y) {
                    switch (game.getPlayer().getOrientation()) {
                        case RIGHT:
                            g.drawImage(loader.getSprites(17), x * 32, y * 32, null);
                            break;
                        case UP:
                            g.drawImage(loader.getSprites(9), x * 32, y * 32, null);
                            break;
                        case DOWN:
                            g.drawImage(loader.getSprites(2), x * 32, y * 32, null);
                            break;
                        case LEFT:
                            g.drawImage(loader.getSprites(25), x * 32, y * 32, null);
                            break;

                    }
                }
            }
        }
    }

    public void paintServerControls(Graphics g) {
        Game game = Game.getInstance();
        for(int x = 0; x < game.getMap().getTiles().length; x++) {
            for (int y = 0; y < game.getMap().getTiles()[x].length; y++) {
                if ((x > 0 && y == 0) || (x == 0 && y > 0)) {
                    g.setColor(new Color(255, 0, 0, 50));
                    g.fillRect(x * 32, y * 32, 32, 32);
                }
            }
        }
    }
}
