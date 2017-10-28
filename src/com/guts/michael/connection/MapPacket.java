package com.guts.michael.connection;

import com.guts.michael.game.*;

import java.util.ArrayList;
import java.util.List;

public class MapPacket extends Packet {

    private IMap map;

    public MapPacket(IMap map) {
        this.map = map;
    }

    public static MapPacket fromDataString(String data) {
        List<ITile[]> tiles = new ArrayList<>();
        for (String row : data.split("\n")) {
            List<ITile> tileRow = new ArrayList<>();
            for (String tile : row.split(" ")) {
                tileRow.add(new Tile(TileType.fromStringRepresentation(tile)));
            }
            tiles.add(tileRow.toArray(new ITile[tileRow.size()]));
        }
        return new MapPacket(new Map(tiles.toArray(new ITile[tiles.size()][])));
    }

    @Override
    public PacketType getType() {
        return PacketType.MAP;
    }

    @Override
    public String asDataString() {
        StringBuilder data = new StringBuilder();
        for (ITile[] row : map.getTiles()) {
            StringBuilder rowString = new StringBuilder();
            for (ITile tile : row) {
                rowString.append(tile.getType().name());
                rowString.append(" ");
            }
            data.append(rowString.toString().trim());
            data.append("\n");
        }
        return data.toString();
    }
}
