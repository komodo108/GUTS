package com.guts.michael.game.render;

import com.guts.michael.game.Game;
import com.guts.michael.game.IEntity;
import com.guts.michael.game.SpriteLoader;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Render extends JPanel {

    public void paintMap(Graphics g) {
        Game game = Game.getInstance();
        g.clearRect(0, 0, getWidth(), getHeight());
        try {
            SpriteLoader loader = new SpriteLoader(32, 32, 8, 8);
            for(int x = 0; x < game.getMap().getTiles().length; x++) {
                for(int y = 0; y < game.getMap().getTiles()[x].length; y++) {
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
                }
            }
        } catch (IOException e) {
            System.err.println("spritesheet not found!!");
            System.exit(1);
        }
    }

    public void paintPlayer(Graphics g) {
        IEntity player = Game.getInstance().getPlayer();
        // TODO: paint player
    }
}
