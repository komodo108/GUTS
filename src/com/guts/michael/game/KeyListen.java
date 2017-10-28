package com.guts.michael.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import static java.awt.event.KeyEvent.*;

public class KeyListen extends Observable implements KeyListener {

    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
            case VK_LEFT:
                Game.getInstance().movePlayer(1, Direction.LEFT);
                break;
            case VK_UP:
                Game.getInstance().movePlayer(1, Direction.UP);
                break;
            case VK_RIGHT:
                Game.getInstance().movePlayer(1, Direction.RIGHT);
                break;
            case VK_DOWN:
                Game.getInstance().movePlayer(1, Direction.DOWN);
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) { }
}
