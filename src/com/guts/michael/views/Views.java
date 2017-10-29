package com.guts.michael.views;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public abstract class Views {
    public static final String DEFAULT_NAME = "Tile Maze";
    public static final int DEFAULT_WIDTH = 480;
    public static final int DEFAULT_HEIGHT = 360;
    public static final Dimension DEFAULT_SIZE = new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    public static final boolean RESIZABLE = false;

    public static ImageIcon getImage() throws IOException {
        return new ImageIcon(ImageIO.read(new File("res/title.png")).getScaledInstance(Views.DEFAULT_WIDTH, Views.DEFAULT_HEIGHT, Image.SCALE_SMOOTH));
    }
}
