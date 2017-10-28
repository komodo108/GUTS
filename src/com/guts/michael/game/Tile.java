package com.guts.michael.game;

public class Tile implements ITile {

    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    @Override
    public TileType getType() {
        return type;
    }
}
