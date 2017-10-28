package com.guts.michael.game;

public enum TileType {
    SPACE(".");

    private String representation;

    TileType(String representation) {
        this.representation = representation;
    }

    public String getStringRepresentation() {
        return representation;
    }

    public static TileType fromStringRepresentation(String representation) {
        for (TileType t : TileType.values()) {
            if (t.getStringRepresentation().equals(representation)) {
                return t;
            }
        }
        return null;
    }
}
