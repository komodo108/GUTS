package com.guts.michael.game;

public class Entity implements IEntity {

    private int id;
    private EntityType type;
    int x;
    int y;
    Direction orientation;

    public Entity(int id, EntityType type, int x, int y, Direction orientation) {
        this.id = id;
        this.type = type;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public EntityType getType() {
        return type;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public Direction getOrientation() {
        return orientation;
    }

    @Override
    public void moveX(int amount) {
        x += amount;
    }

    @Override
    public void moveY(int amount) {
        y += amount;
    }

    @Override
    public void move(int amount, Direction direction) {
        switch (direction) {
            case UP:
                moveY(-amount);
                break;
            case DOWN:
                moveY(amount);
                break;
            case LEFT:
                moveX(-amount);
                break;
            case RIGHT:
                moveX(amount);
                break;
        }
    }
}
