package com.guts.michael.connection;

import java.io.BufferedReader;
import java.io.IOException;

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
            case "MOVE":
                return MovePacket.fromData(data);
            case "MAP":
                return MapPacket.fromData(data);
            default:
                return null;
        }
    }

    /**
     * Read the next packet from a buffered reader.
     * @param read the buffered reader
     * @return the parsed packed
     * @throws IOException if there is an IO error
     */
    public static IPacket readNextPacket(BufferedReader read) throws IOException {
        StringBuffer packetString = new StringBuffer();
        String line;
        while ((line = read.readLine()) != null && !line.equals("END")) {
            packetString.append(line + '\n');
        }
        return Packet.fromString(packetString.toString());
    }
}
