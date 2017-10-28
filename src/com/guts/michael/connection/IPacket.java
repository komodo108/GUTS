package com.guts.michael.connection;

public interface IPacket {

    PacketType getType();

    /**
     * Get a string representation of the packet that could be sent over the socket.
     * @return
     */
    String asDataString();

    String asPacketString();
}
