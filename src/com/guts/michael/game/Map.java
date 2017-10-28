package com.guts.michael.game;

import java.util.Random;

public class Map implements IMap {

    private ITile[][] tiles;

    public Map(ITile[][] tiles) {
        this.tiles = tiles;
    }

    @Override
    public ITile[][] getTiles() {
        return tiles;
    }

    @Override
    public ITile getTileAt(int x, int y) {
        return tiles[x][y];
    }

    @Override
    public void shiftRow(int row, int amount) {
        ITile[] rowTiles = new ITile[tiles[row].length];
        for (int i = 0; i < tiles[row].length; i++) {
            rowTiles[(amount + i) % tiles[row].length] = tiles[row][i];
        }
        tiles[row] = rowTiles;
    }

    @Override
    public void shiftColumn(int column, int amount) {
        ITile[] columnTiles = new ITile[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            columnTiles[(amount + i) % tiles.length] = tiles[i][column];
        }
        for (int i = 0; i < columnTiles.length; i++) {
            tiles[i][column] = columnTiles[i];
        }
    }

    public static Map generateRandom() {
        ITile[][] tiles = new ITile[50][50];
        Random random = new Random();
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                TileType type;
                if (x == 0 || y == 0 || x == tiles.length - 1 || y == tiles[x].length - 1) {
                    type = TileType.WALL;
                } else {
                    int rnd = random.nextInt(TileType.values().length + 3);
                    if (rnd < TileType.values().length) {
                        type = TileType.values()[rnd];
                    } else {
                        type = TileType.SPACE;
                    }
                }
                tiles[x][y] = new Tile(type);
            }
        }
        return new Map(tiles);
    }
}
