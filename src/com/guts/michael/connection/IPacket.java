package com.guts.michael.connection;

public interface IPacket {

    /**
     * Get a string representation of the packet that could be sent over the socket.
     * @return
     */
    String asString();
}
