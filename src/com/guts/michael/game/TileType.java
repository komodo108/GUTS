package com.guts.michael.game;

public enum TileType {
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

    private char representation;

    TileType(char representation) {
        this.representation = representation;
    }

    public char getRepresentation() {
        return representation;
    }

    public static TileType fromRepresentation(char representation) {
        for (TileType t : TileType.values()) {
            if (t.getRepresentation() == representation) {
                return t;
            }
        }
        return null;
    }
}
