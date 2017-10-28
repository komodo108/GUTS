package com.guts.michael.connection;

import com.guts.michael.game.IEntity;
import com.guts.michael.game.IMap;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class Packet implements IPacket {

    @Override
    public String asPacketString() {
        return this.getType() + "\n" + this.asDataString() + "\n" + "END\n";
    }

    /**
     * Parse a packet.
     * @param input the string to parse
     * @return the packet
     * @throws CorruptedPacketException if the packet could not be parsed
     */
    public static IPacket fromDataString(String input) throws CorruptedPacketException {
        String firstLine = input.substring(0, input.indexOf('\n'));
        String data = input.substring(input.indexOf('\n') + 1, input.length() - 1);

        try {
            PacketType type = PacketType.valueOf(firstLine);
            Method method = type.getPacketClass().getMethod("fromDataString", String.class);

            return (Packet) method.invoke(null, data);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | IllegalArgumentException e) {
            throw new CorruptedPacketException();
        }
    }

    /**
     * Read the next packet from a buffered reader.
     * @param read the buffered reader
     * @return the parsed packed or null if there is no next packet
     * @throws IOException if there is an IO error
     */
    public static IPacket readNextPacket(BufferedReader read) throws IOException {
        StringBuilder packetString = new StringBuilder();
        String line;
        while ((line = read.readLine()) != null && !line.equals("END")) {
            if (line.equals("")) {
                continue;
            } else if (line.equals("QUIT")) {
                return null;
            }
            packetString.append(line);
            packetString.append('\n');
        }

        try {
            return Packet.fromDataString(packetString.toString());
        } catch (StringIndexOutOfBoundsException e) {
            return null;
        }
    }
}
