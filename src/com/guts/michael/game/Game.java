package com.guts.michael.game;

import java.util.Observable;

public class Game extends Observable {

    private IMap map;
    private IEntity player;

    public Game(IMap map, IEntity player) {
        this.map = map;
        this.player = player;
    }

    public void movePlayer(int amount, Direction direction) {
        player.move(amount, direction);
        setChanged();
        notifyObservers();
    }

    public IMap getMap() {
        return map;
    }

    public IEntity getPlayer() {
        return player;
    }

    public void shiftRow(int row, int amount) {
        map.shiftRow(row, amount);
        setChanged();
        notifyObservers();
    }

    public void shiftColumn(int column, int amount) {
        map.shiftColumn(column, amount);
        setChanged();
        notifyObservers();
    }
}
