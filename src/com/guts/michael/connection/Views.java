package com.guts.michael.connection;

import java.awt.*;

public class Views {
    private static final String DEFAULT_NAME = "Tile Maze";
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 200;
    private static final Dimension DEFAULT_SIZE = new Dimension(getDEFAULT_WIDTH(), getDEFAULT_HEIGHT());
    private static final boolean RESIZABLE = false;

    public static String getDEFAULT_NAME() {
        return DEFAULT_NAME;
    }

    public static int getDEFAULT_WIDTH() {
        return DEFAULT_WIDTH;
    }

    public static int getDEFAULT_HEIGHT() {
        return DEFAULT_HEIGHT;
    }

    public static Dimension getDEFAULT_SIZE() {
        return DEFAULT_SIZE;
    }

    public static boolean isRESIZABLE() {
        return RESIZABLE;
    }
}
