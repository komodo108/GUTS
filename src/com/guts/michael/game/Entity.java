package com.guts.michael.game;

public class Entity implements IEntity {

    private EntityType type;
    int x;
    int y;
    Direction orientation;

    public Entity(EntityType type, int x, int y, Direction orientation) {
        this.type = type;
        this.x = x;
        this.y = y;
        this.orientation = orientation;
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
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Direction getOrientation() {
        return orientation;
    }

    @Override
    public void moveX(int amount) {
        x += amount;
        orientation = amount > 0 ? Direction.RIGHT : Direction.LEFT;
    }

    @Override
    public void moveY(int amount) {
        y += amount;
        orientation = amount > 0 ? Direction.UP : Direction.DOWN;
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
