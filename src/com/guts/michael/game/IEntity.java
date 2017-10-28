package com.guts.michael.game;

public interface IEntity {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    EntityType getType();

    int getId();

    Direction getOrientation();

    void moveX(int amount);

    void moveY(int amount);

    void move(int amount, Direction direction);
}
