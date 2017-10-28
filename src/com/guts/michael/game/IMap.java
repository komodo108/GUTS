package com.guts.michael.game;

public interface IMap {

    /**
     * Get all tiles in the map.
     * @return the tiles
     */
    ITile[][] getTiles();

    /**
     * Get the tile in a specific position.
     * @param x the x-coordinate
     * @param y the y-coordinate
     * @return the tile at that position
     */
    ITile getTileAt(int x, int y);

    /**
     * Shift all tiles in a row to the right by a specific amount.
     * @param row the row
     * @param amount the amount
     */
    void shiftRow(int row, int amount);

    /**
     * Shift all tiles in a column down by a specific amount.
     * @param column the column
     * @param amount the amount
     */
    void shiftColumn(int column, int amount);
}
