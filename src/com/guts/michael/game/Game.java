package com.guts.michael.game;

import java.util.*;

public class Game extends Observable {

    private static int NUM_ENEMIES = 10;

    private static Game game;
    private IMap map;
    private IEntity player;
    private List<IEntity> enemies;

    private int lastMoveAmount;
    private Direction lastMoveDirection;
    private boolean isClientTurn = true;

    public static Game getInstance() {
        if (game == null) {
            game = new Game(Map.generateRandom(), new Entity(EntityType.PLAYER, 4, 4, Direction.RIGHT), NUM_ENEMIES);
        }
        return game;
    }

    public Game(IMap map, IEntity player) {
        this(map, player, new LinkedList<>());
    }

    public Game(IMap map, IEntity player, List<IEntity> enemies) {
        this.map = map;
        this.player = player;

        // Make sure that the player doesn't start on a wall
        player = makeSureEntityIsNotOnWall(player);
        List<IEntity> newEnemies = new LinkedList<>();
        for (IEntity enemy : enemies) {
            newEnemies.add(makeSureEntityIsNotOnWall(enemy));
        }
        this.enemies = newEnemies;
    }

    public Game(IMap map, IEntity player, int numberOfEnemies) {
        this(map, player, generateRandomEnemies(numberOfEnemies, map));
    }

    private static List<IEntity> generateRandomEnemies(int numberOfEnemies, IMap map) {
        List<IEntity> enemies = new LinkedList<>();
        for (int i = 0; i < numberOfEnemies; i++) {
            enemies.add(assignRandomPosition(new Entity(EntityType.ENEMY, 0, 0, Direction.RIGHT), map));
        }
        return enemies;
    }

    private IEntity makeSureEntityIsNotOnWall(IEntity entity) {
        if (map.getTileAt(entity.getX(), entity.getY()).getType() == TileType.WALL) {
            entity = assignRandomPosition(entity, map);
        }
        return entity;
    }

    private static IEntity assignRandomPosition(IEntity entity, IMap map) {
        while (true) {
            Random random = new Random();
            int x = random.nextInt(map.getTiles().length);
            int y = random.nextInt(map.getTiles()[x].length);
            if (map.getTileAt(x, y).getType() != TileType.WALL) {
                entity = new Entity(entity.getType(), x, y, entity.getOrientation());
                break;
            }
        }
        return entity;
    }

    public void movePlayer(int amount, Direction direction) throws IllegalMoveException {
        setLastMoveAmount(0);
        if (!isClientTurn()) {
            return;
        }
        moveEnemies();
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
        //Move enemies if neccessay
        for(IEntity e: enemies) {
            if (map.getTileAt(e.getX(), e.getY()).getType() == TileType.WALL) {
                e.setY((e.getY() + amount) % map.getTiles().length);
            }
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
        //Move enemies if neccessay
        for(IEntity e:enemies) {
            if (map.getTileAt(e.getX(), e.getY()).getType() == TileType.WALL) {
                e.setX((e.getX() + amount) % map.getTiles().length);
            }
        }
        // Set changed
        setChanged();
        notifyObservers("player2finished");
    }

    public void setGame(IMap map, IEntity player, List<IEntity> enemies) {
        this.map = map;
        this.player = player;
        this.enemies = enemies;
        setChanged();
        notifyObservers();
    }

    public List<IEntity> getEnemies() {
        return enemies;
    }

    public void moveEnemies() {
        for (IEntity e : enemies) {
            int x = e.getX();
            int y = e.getY();
            int xDiffToPlayer = player.getX() - x;
            int yDiffToPlayer = player.getY() - y;
            HashMap<Direction, Integer> directions = new HashMap<>();
            directions.put(Direction.UP, 0);
            directions.put(Direction.DOWN, 0);
            directions.put(Direction.LEFT, 0);
            directions.put(Direction.RIGHT, 0);
            if (x < map.getTiles().length - 1 && map.getTileAt(x + 1, y).getType() != TileType.WALL) {
                directions.put(Direction.RIGHT, 1);
            }
            if (x > 0 && map.getTileAt(x - 1, y).getType() != TileType.WALL) {
                directions.put(Direction.LEFT, 1);
            }
            if (y < map.getTiles().length - 1 && map.getTileAt(x, y - 1).getType() != TileType.WALL) {
                directions.put(Direction.UP, 1);
            }
            if (y > 0 && map.getTileAt(x, y + 1).getType() != TileType.WALL) {
                directions.put(Direction.DOWN, 1);
            }
            if (xDiffToPlayer > 0) {
                directions.put(Direction.RIGHT, directions.get(Direction.RIGHT) * 2);
            } else if (xDiffToPlayer < 0) {
                directions.put(Direction.LEFT, directions.get(Direction.LEFT) * 2);
            }
            if (yDiffToPlayer < 0) {
                directions.put(Direction.UP, directions.get(Direction.UP) * 2);
            } else if (yDiffToPlayer > 0) {
                directions.put(Direction.DOWN, directions.get(Direction.DOWN) * 2);
            }
            if (Math.abs(xDiffToPlayer) < Math.abs(yDiffToPlayer)) {
                if (xDiffToPlayer > 0) {
                    directions.put(Direction.RIGHT, directions.get(Direction.RIGHT) * 2);
                } else if (xDiffToPlayer < 0) {
                    directions.put(Direction.LEFT, directions.get(Direction.LEFT) * 2);
                }
            } else if (Math.abs(xDiffToPlayer) > Math.abs(yDiffToPlayer)) {
                if (yDiffToPlayer > 0) {
                    directions.put(Direction.UP, directions.get(Direction.UP) * 2);
                } else if (yDiffToPlayer < 0) {
                    directions.put(Direction.DOWN, directions.get(Direction.DOWN) * 2);
                }
            }
            List<Direction> cumulativeDirections = new LinkedList<>();
            for (java.util.Map.Entry<Direction, Integer> entry : directions.entrySet()) {
                // System.out.println("enemy " + entry.getKey().name() + " " + entry.getValue());
                for (int i = 0; i < entry.getValue(); i++) {
                    cumulativeDirections.add(entry.getKey());
                }
            }
            if (cumulativeDirections.size() == 0) {
                continue;
            }
            Random random = new Random();
            Direction direction = cumulativeDirections.get(random.nextInt(cumulativeDirections.size()));
            e.move(1, direction);
        }
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

    public synchronized boolean isLose() {
        for(IEntity e: enemies) {
            if(e.getX() == getPlayer().getX() && e.getY() == getPlayer().getY()) {
                return true;
            }
        } return false;
    }

    public synchronized boolean isClientTurn() {
        if(!isVictory() && !isLose()) {
            return isClientTurn;
        } else {
            return true;
        }
    }

    public synchronized void setClientTurn(boolean clientTurn) {
        isClientTurn = clientTurn;
    }
}
