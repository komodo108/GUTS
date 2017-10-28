package com.guts.michael.game.render;

import java.awt.*;

public class Render {

    public void render(Graphics g, boolean isServer) {
        if(isServer) {
            g.drawLine(0, 0, 400, 400);
        } else {
            g.drawLine(400, 0, 0, 400);
        }
    }

}
