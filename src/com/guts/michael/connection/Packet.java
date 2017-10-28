package com.guts.michael.connection;

public abstract class Packet implements IPacket {

    /**
     * Parse a packet.
     * @param input the string to parse
     * @return the packet
     */
    public static IPacket fromString(String input) {
        String firstLine = input.substring(0, input.indexOf('\n'));
        String data = input.substring(input.indexOf('\n') + 1, input.length() - 1);

        switch (firstLine) {
            case "MAP":
                return MapPacket.fromData(data);
            default:
                return null;
        }
    }
}
