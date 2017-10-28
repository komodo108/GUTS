package com.guts.michael.game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

import static java.awt.event.KeyEvent.*;

public class KeyListen extends Observable implements KeyListener {

    private char key;

    public char getKey() {
        return key;
    }

    private void update() {
        setChanged();
        notifyObservers();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        switch(e.getKeyCode()) {
            case VK_LEFT:
                key = 'l';
                update();
                break;
            case VK_UP:
                key = 'u';
                update();
                break;
            case VK_RIGHT:
                key = 'r';
                update();
                break;
            case VK_DOWN:
                key = 'd';
                update();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
