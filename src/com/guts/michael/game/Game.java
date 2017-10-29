package com.guts.michael.game;

import java.util.Observable;
import java.util.Random;

public class Game extends Observable {

    private static Game game;
    private IMap map;
    private IEntity player;
    private int lastMoveAmount;
    private Direction lastMoveDirection;

    private boolean isClientTurn = true;

    public static Game getInstance() {
        if (game == null) {
            game = new Game(Map.generateRandom(), new Entity(0, EntityType.PLAYER, 4, 4, Direction.RIGHT));
        }
        return game;
    }

    public Game(IMap map, IEntity player) {
        // Make sure that the player doesn't start on a wall
        if (map.getTileAt(player.getX(), player.getY()).getType() == TileType.WALL) {
            while (true) {
                Random random = new Random();
                int x = random.nextInt(map.getTiles().length);
                int y = random.nextInt(map.getTiles()[x].length);
                if (map.getTileAt(x, y).getType() != TileType.WALL) {
                    player = new Entity(player.getId(), player.getType(), x, y, player.getOrientation());
                    break;
                }
            }
        }

        this.map = map;
        this.player = player;
    }

    public void movePlayer(int amount, Direction direction) throws IllegalMoveException {
        setLastMoveAmount(0);
        if (!isClientTurn()) {
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
        setLastMoveAmount(amount);
        setLastMoveDirection(direction);
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
        if (isClientTurn()) {
            return;
        }
        // Shift row
        map.shiftRow(row, amount);
        // Move player if necessary
        if (map.getTileAt(player.getX(), player.getY()).getType() == TileType.WALL) {
            player.setY((player.getY() + amount) % map.getTiles().length);
        }
        // Set changed
        setChanged();
        notifyObservers("player2finished");
    }

    public void shiftColumn(int column, int amount) {
        if (isClientTurn()) {
            return;
        }
        // Shift column
        map.shiftColumn(column, amount);
        // Move player if necessary
        if (map.getTileAt(player.getX(), player.getY()).getType() == TileType.WALL) {
            player.setX((player.getX() + amount) % map.getTiles().length);
        }
        // Set changed
        setChanged();
        notifyObservers("player2finished");
    }

    public void setGame(IMap map, IEntity player) {
        this.map = map;
        this.player = player;
        setChanged();
        notifyObservers();
    }

    public synchronized int getLastMoveAmount() {
        return lastMoveAmount;
    }

    public synchronized Direction getLastMoveDirection() {
        return lastMoveDirection;
    }

    private synchronized void setLastMoveAmount(int lastMoveAmount) {
        this.lastMoveAmount = lastMoveAmount;
    }

    private synchronized void setLastMoveDirection(Direction lastMoveDirection) {
        this.lastMoveDirection = lastMoveDirection;
    }

    public synchronized boolean isVictory() {
        if(this.getMap().getTileAt(getPlayer().getX(), getPlayer().getY()).getType().getRepresentation() == 'V') return true;
        else return false;
    }

    public synchronized boolean isClientTurn() {
        return isClientTurn;
    }

    public synchronized void setClientTurn(boolean clientTurn) {
        isClientTurn = clientTurn;
    }
}
