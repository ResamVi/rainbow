package edu.kit.informatik;

/**
 * Represents a Board that can hold {@link Tiles}
 * 
 * @author Julien Midedji
 *
 */
public class Board {

    /**
     * Board can hold 12 Tiles total
     */
    private static final int BOARD_SIZE = 12;

    private Tile[] tiles;

    /**
     * Constructs an empty {@code Board}
     * 
     * @param lineTypes
     *            list of line positions
     * 
     */
    public Board() {

        tiles = new Tile[BOARD_SIZE];

        for (int i = 0; i < BOARD_SIZE; i++) {
            tiles[i] = new Tile();
        }

    }

    /**
     * Returns tile at given position
     * 
     * @param position
     *            of Tile
     * @return the Tile
     */
    public Tile getTile(int position) {
        return tiles[position];
    }

    /**
     * Changes the Tile at the given position
     * 
     * @param position
     *            which Tile to change
     * @param newTile
     *            replacement of old Tile
     */
    public void setTile(int position, Tile newTile) {
        tiles[position] = newTile;
    }

    /**
     * Replaces Tile with an empty Tile
     * 
     * @param position
     *            which Tile to change
     */
    public void removeTile(int position) {
        tiles[position] = new Tile();
    }

    /**
     * Returns whether all Tiles are empty
     * 
     * @return true if all Tiles are empty
     */
    public boolean isEmpty() {
        for (Tile tile : tiles) {
            if (!tile.isEmpty())
                return false;
        }
        return true;
    }

    /**
     * Rotates Tile at position clockwise
     * 
     * @param position
     *            what Tile to rotate
     */
    public void rotateTileClockwise(int position) {
        tiles[position].rotateClockwise();
    }

    /**
     * Rotates Tile at position counter-clockwise
     * 
     * @param position
     *            what Tile to rotate
     */
    public void rotateTileCounterClockwise(int position) {
        tiles[position].rotateCounterClockwise();
    }

    /**
     * Returns the amount of colors on the board
     * 
     * @return total number of colors
     */
    public int getNumberOfColors() {

        String allTiles = "";
        int amount = 0;

        for (Tile tile : tiles)
            allTiles += tile.toString();

        if (allTiles.contains("R"))
            amount++;

        if (allTiles.contains("G"))
            amount++;

        if (allTiles.contains("Y"))
            amount++;

        return amount;
    }

    /**
     * Returns whether all Tiles on the board fit correctly
     * 
     * @return true if board is valid
     */
    public boolean isValid() {

        // Iterate through all Tiles
        for (int tileId = 0; tileId < BOARD_SIZE; tileId++) {

            int[] neighbour = getNeighbours(tileId);

            // Iterate through all neighbors of tile
            for (int index = 0; index < neighbour.length; index++) {

                // If there exists a neighbor, check if it fits (-1 means
                // invalid neighbour)
                if (neighbour[index] != -1) {

                    if (!tiles[tileId].fitsTo(tiles[neighbour[index]], index))
                        return false;

                }
            }
        }

        return true;
    }

    /**
     * Calculates all valid neighbours and returns their position on the board
     * (tileId)
     * 
     * @param tileId
     * @return array of neighbour position, where -1 is an invalid neighbour
     */
    private int[] getNeighbours(int tileId) {

        // Notation for invalid tiles
        final int OUT_OF_BOUNDS = -1;

        // Store all neigbours of this Tile
        int[] neighbourPos = new int[6];

        // Then calculate all the valid neighbours
        neighbourPos[0] = tileId - 1; // top
        neighbourPos[1] = tileId + 2; // top right
        neighbourPos[2] = tileId + 3; // bottom right
        neighbourPos[3] = tileId + 1; // bottom
        neighbourPos[4] = tileId - 2; // bottom left
        neighbourPos[5] = tileId - 3; // top left

        // No upper neighbours for 0, 3, 6, 9
        if (tileId % 3 == 0) {
            neighbourPos[0] = OUT_OF_BOUNDS;
            neighbourPos[1] = OUT_OF_BOUNDS;
        }

        // No lower neighbours for 2, 5, 8, 11
        if ((tileId - 2) % 3 == 0) {
            neighbourPos[3] = OUT_OF_BOUNDS;
            neighbourPos[4] = OUT_OF_BOUNDS;
            ;
        }

        // No Tiles with tileID > 11 and < 0
        for (int i = 0; i < 6; i++) {
            if (neighbourPos[i] > 11 || neighbourPos[i] < 0)
                neighbourPos[i] = OUT_OF_BOUNDS;
        }

        return neighbourPos;
    }

    /**
     * Returns the color of the connected path or {@link LineType.NONE} if the
     * path does not connect
     * 
     * @param positions
     *            array of tileIDs to check
     * @return LineType of the path
     */
    public LineType getConnectedPathColor(int[] positions) {

        // Iterate through whole path
        for (int i = 0; i < positions.length - 1; i++) {

            Tile first = tiles[positions[i]];
            Tile neighbour = tiles[positions[i + 1]];

            // Check step by step if the tiles connect with the tile ahead in
            // path
            // Break iteration when paths do not connect, return NONE
            if (!connectsTo(first, neighbour, positionOfNeighbour(positions[i], positions[i + 1])))
                return LineType.NONE;

        }

        // If they do connect, simply take the color of any lines in the path
        // (the first here)
        return tiles[positions[0]].getLineTypeAtIndex(positionOfNeighbour(positions[0], positions[1]));
    }

    private boolean connectsTo(Tile first, Tile second, int position) {

        // Where this Tile is attached to from otherTile's point of reference
        int oppositePosition = (position + 3) % 6;

        return first.getLineTypeAtIndex(position) == second.getLineTypeAtIndex(oppositePosition);
    }

    /**
     * Calculates the position from two IDs
     * 
     * @param tileId
     *            position of the neighbour relative to this tile
     * @param neighbourId
     *            the neighbour
     * @return the position
     */
    private int positionOfNeighbour(int tileId, int neighbourId) {

        // The difference of the tileIDs have a special meaning
        final int TOP = -1, TOP_RIGHT = 2, BOTTOM_RIGHT = 3, BOTTOM = 1, BOTTOM_LEFT = -2, TOP_LEFT = -3;

        int relativePos = neighbourId - tileId;

        switch (relativePos) {

        case TOP:
            return 0;

        case TOP_RIGHT:
            return 1;

        case BOTTOM_RIGHT:
            return 2;

        case BOTTOM:
            return 3;

        case BOTTOM_LEFT:
            return 4;

        case TOP_LEFT:
            return 5;

        default:
            return -1;
        }
    }

    @Override
    public String toString() {

        String word = "";

        for (int tileId = 0; tileId < BOARD_SIZE; tileId++) {
            word += tiles[tileId].toString() + ";";

            if ((tileId + 1) % 3 == 0 && tileId != 0)
                word += "\n";
        }
        return word;
    }

}
