package com.guts.michael.connection;

public enum PacketType {

    MOVE(MovePacket.class),
    MAP(MapPacket.class);

    private Class<? extends Packet> type;

    PacketType(Class<? extends Packet> type) {
        this.type = type;
    }

    public Class<? extends Packet> getPacketClass() {
        return type;
    }
}
