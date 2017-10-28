package com.guts.michael.game;

import java.util.ArrayList;
import java.util.List;

public enum TileType {

    WALL('X', 2),
    SPACE(' ', 2),
    GRASS('G', 1),
    SAND('S', 1);

    /*
    SPACE(' '),
    HORIZONTAL_LINE('─'),
    VERTICAL_LINE('│'),
    TOP_LEFT_CORNER('┌'),
    TOP_RIGHT_CORNER('┐'),
    BOTTOM_LEFT_CORNER('└'),
    BOTTOM_RIGHT_CORNER('┘'),
    VERTICAL_AND_RIGHT('├'),
    VERTICAL_AND_LEFT('┤'),
    HORIZONTAL_AND_UP('┴'),
    HORIZONTAL_AND_DOWN('┬'),
    VERTICAL_AND_HORIZONTAL('┼');
    */

    private char representation;
    private int probabilityWeight;

    TileType(char representation, int probabilityWeight) {
        this.representation = representation;
        this.probabilityWeight = probabilityWeight;
    }

    public char getRepresentation() {
        return representation;
    }

    public int getProbabilityWeight() {
        return probabilityWeight;
    }

    public static TileType fromRepresentation(char representation) {
        for (TileType t : TileType.values()) {
            if (t.getRepresentation() == representation) {
                return t;
            }
        }
        return null;
    }

    public static TileType[] getCumulativeArray() {
        List<TileType> types = new ArrayList<>();
        for (TileType type : values()) {
            for (int i = 0; i < type.probabilityWeight; i++) {
                types.add(type);
            }
        }
        return types.toArray(new TileType[0]);
    }
}
