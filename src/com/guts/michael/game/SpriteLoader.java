package com.guts.michael.game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteLoader {
    BufferedImage spriteSheet = ImageIO.read(new File("src/spriteSheet.png"));

    int width;
    int height;
    int rows;
    int columns;
    BufferedImage[] sprites;

    public SpriteLoader(int width, int height, int rows, int columns) throws IOException {
        sprites = new BufferedImage[rows * columns];
        this.width = width;
        this.height = height;
        this.rows = rows;
        this.columns = columns;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                sprites[(i * columns) + j] = spriteSheet.getSubimage(i * width, j * height, width, height);
            }
        }
    }
    public void paint(Graphics g) {
        g.drawImage(sprites[1], 100, 100, null);
    }
}
