package com.guts.michael.game;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

public class MouseListen extends Observable implements MouseListener{

    private Game game = Game.getInstance();

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) {
        int x = e.getX()/32;
        int y = e.getY()/32;

        if ((x > 0 && y == 0) || (x == 0 && y > 0)) {
            if(x == 0) {
                //Clicked on row
                game.shiftColumn(y, 1);
            }
            if(y == 0) {
                //Clicked on column
               game.shiftRow(x, 1);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }
}
