package com.guts.michael.game;

public interface IEntity {

    int getX();

    int getY();

    EntityType getType();

    int getId();

    Direction getOrientation();

    void moveX(int amount);

    void moveY(int amount);

    void move(int amount, Direction direction);
}
