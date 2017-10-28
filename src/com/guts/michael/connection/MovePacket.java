package com.guts.michael.connection;

import com.guts.michael.game.Direction;

public class MovePacket extends Packet {

    private Direction direction;
    private int amount;

    public MovePacket(Direction direction, int amount) {
        this.direction = direction;
        this.amount = amount;
    }

    public static MovePacket fromData(String data) {
        String[] split = data.split(" ");
        return new MovePacket(Direction.valueOf(split[0]), Integer.valueOf(split[1]));
    }

    @Override
    public String asString() {
        return direction.toString() + " " + amount;
    }
}
