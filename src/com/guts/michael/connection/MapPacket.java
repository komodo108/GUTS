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
            for (char tile : row.toCharArray()) {
                tileRow.add(new Tile(TileType.fromRepresentation(tile)));
            }
            tiles.add(tileRow.toArray(new ITile[tileRow.size()]));
        }
        return new MapPacket(new Map(tiles.toArray(new ITile[tiles.size()][])));
    }

    public IMap getMap() {
        return map;
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
                rowString.append(tile.getType().getRepresentation());
            }
            data.append(rowString.toString().trim());
            data.append("\n");
        }
        data.deleteCharAt(data.length() - 1);
        return data.toString();
    }
}
