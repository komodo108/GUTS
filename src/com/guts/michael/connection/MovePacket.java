package com.guts.michael.connection;

import com.guts.michael.game.Direction;

public class MovePacket extends Packet {

    private Direction direction;
    private int amount;

    public MovePacket(Direction direction, int amount) {
        this.direction = direction;
        this.amount = amount;
    }

    public static MovePacket fromDataString(String data) {
        String[] split = data.split(" ");
        return new MovePacket(Direction.valueOf(split[0]), Integer.valueOf(split[1]));
    }

    public Direction getDirection() {
        return direction;
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public PacketType getType() {
        return PacketType.MOVE;
    }

    @Override
    public String asDataString() {
        return direction.toString() + " " + amount;
    }
}
