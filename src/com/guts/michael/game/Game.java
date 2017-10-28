package com.guts.michael.game;

import java.util.Observable;
import java.util.Random;

public class Game extends Observable {

    private static Game game;
    private IMap map;
    private IEntity player;
    private int lastMoveAmount;
    private Direction lastMoveDirection;

    public boolean isClientTurn = true;

    public static Game getInstance() {
        if (game == null) {
            game = new Game(Map.generateRandom(), new Entity(0, EntityType.PLAYER, 4, 4, Direction.RIGHT));
        }
        return game;
    }

    public Game(IMap map, IEntity player) {
        // Make sure that the player doesn't start on a wall
        while (true) {
            Random random = new Random();
            int x = random.nextInt(map.getTiles().length);
            int y = random.nextInt(map.getTiles()[x].length);
            if (map.getTileAt(x,  y).getType() != TileType.WALL) {
                player = new Entity(player.getId(), player.getType(), x, y, player.getOrientation());
                break;
            }
        }

        this.map = map;
        this.player = player;
    }

    public void movePlayer(int amount, Direction direction) throws IllegalMoveException {
        if (!isClientTurn) {
            return;
        }
        int newX = player.getX();
        int newY = player.getY();
        switch (direction) {
            case UP:
                newY -= amount;
                break;
            case DOWN:
                newY += amount;
                break;
            case LEFT:
                newX -= amount;
                break;
            case RIGHT:
                newX += amount;
                break;
        }
        if (newX < 0 || newY < 0 || newX >= map.getTiles().length || newY >= map.getTiles()[0].length) {
            throw new IllegalMoveException();
        }
        if (map.getTileAt(newX, newY).getType() == TileType.WALL) {
            throw new IllegalMoveException();
        }
        lastMoveAmount = amount;
        lastMoveDirection = direction;
        player.move(amount, direction);
        setChanged();
        notifyObservers("player1finished");
    }

    public IMap getMap() {
        return map;
    }

    public IEntity getPlayer() {
        return player;
    }

    public void shiftRow(int row, int amount) {
        if (isClientTurn) {
            return;
        }
        map.shiftRow(row, amount);
        setChanged();
        notifyObservers("player2finished");
    }

    public void shiftColumn(int column, int amount) {
        if (isClientTurn) {
            return;
        }
        map.shiftColumn(column, amount);
        setChanged();
        notifyObservers("player2finished");
    }

    public void setGame(Game game) {
        this.map = game.getMap();
        this.player = game.getPlayer();
        setChanged();
        notifyObservers();
    }

    public int getLastMoveAmount() {
        return lastMoveAmount;
    }

    public Direction getLastMoveDirection() {
        return lastMoveDirection;
    }
}
