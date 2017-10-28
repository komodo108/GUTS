package com.guts.michael.game;

import java.util.Observable;

public class Game extends Observable {

    private static Game game;

    public static Game getInstance() {
        if (game == null) {
            game = new Game(Map.generateRandom(), new Entity(0, EntityType.PLAYER, 4, 4, Direction.RIGHT));
        }
        return game;
    }

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
        notifyObservers("player2finished");
    }

    public void shiftColumn(int column, int amount) {
        map.shiftColumn(column, amount);
        setChanged();
        notifyObservers("player2finished");
    }

    public void setGame(Game game) {
        this.map = map;
        this.player = player;
        setChanged();
        notifyObservers();
    }
}
