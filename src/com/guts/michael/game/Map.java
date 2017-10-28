package com.guts.michael.game;

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
}
