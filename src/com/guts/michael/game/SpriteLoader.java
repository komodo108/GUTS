package com.guts.michael.game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SpriteLoader {
    BufferedImage spriteSheet;

    int width;
    int height;
    int rows;
    int columns;
    BufferedImage[] sprites;

    public SpriteLoader(int width, int height, int rows, int columns) throws IOException {
        spriteSheet = ImageIO.read(new File("spritesheet.png"));
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

    public BufferedImage getSprites(int sprite) {
        return sprites[sprite];
    }
}
