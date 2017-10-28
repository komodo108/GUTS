package com.guts.michael.game.render;

import javax.swing.*;
import java.awt.*;

public class ServerRender extends JPanel {

    private int c;

    @Override
    protected void paintComponent(Graphics g) {
        if(c != 0) {
            g.drawLine(400, 0, 0, 400);
        } else c++;
    }

}
