package com.guts.michael.connection;


public class FinishedPacket extends Packet {

    @Override
    public PacketType getType() {
        return PacketType.FINISHED;
    }

    @Override
    public String asDataString() {
        return "";
    }

    public static FinishedPacket fromDataString(String data) {
        return new FinishedPacket();
    }
}
