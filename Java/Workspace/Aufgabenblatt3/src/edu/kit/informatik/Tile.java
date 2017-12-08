package edu.kit.informatik;

/**
 * Represents a Tile that can hold lines of {@link LineType}
 * 
 * @author Julien Midedji
 *
 */
public class Tile {

    /**
     * Tiles are hexagons and have 6 edges
     */
    private static final int MAX_TILE_EDGES = 6; // TODO: Is this be
                                                 // allowed?

    /**
     * Holds the {@link LineType} at the position, where 0 is the top, rotating
     * clockwise to 5
     */
    private LineType[] lineTypes;

    /**
     * Constructs a {@code Tile} with the specified arguments
     * 
     * @param lineTypes
     *            list of line positions
     * 
     */
    public Tile(LineType[] lineTypes) {
        this.lineTypes = lineTypes;
    }

    /**
     * Constructs an empty {@code Tile}
     * 
     */
    public Tile() {

        // Initialize empty Tile
        LineType[] lineTypes = new LineType[MAX_TILE_EDGES];

        for (int i = 0; i < MAX_TILE_EDGES; i++)
            lineTypes[i] = LineType.NONE;

        this.lineTypes = lineTypes;
    }

    /**
     * Returns the {@code LineType} at index, 0 is the top edge, goes clockwise
     * up to 5
     * 
     * @param index
     *            the index
     * @return lineType at index
     */
    public LineType getLineTypeAtIndex(int index) {
        return lineTypes[index];
    }

    /**
     * Returns the total amount of colors in {@code Tile}
     * 
     * @return total colors
     */
    public int getNumberOfColors() {

        int amount = 0;

        if (this.toString().contains("R"))
            amount++;

        if (this.toString().contains("G"))
            amount++;

        if (this.toString().contains("Y"))
            amount++;

        return amount;

    }

    /**
     * 
     * Returns whether Tile is identical in colors and orientation
     * 
     * @param otherTile
     *            Tile to compare
     * @return true if identical
     */
    public boolean isExactlyEqualTo(Tile otherTile) {

        // Go through both arrays and compare
        for (int i = 0; i < MAX_TILE_EDGES; i++) {
            if (lineTypes[i].getAbbreviation() != otherTile.lineTypes[i].getAbbreviation())
                return false;
        }

        return true;
    }

    /**
     * Returns an identical copy of this Tile
     * 
     * @return copy of Tile
     */
    public Tile copy() {

        LineType[] copy = new LineType[MAX_TILE_EDGES];

        for (int i = 0; i < MAX_TILE_EDGES; i++)
            copy[i] = lineTypes[i];

        return new Tile(copy);
    }

    /**
     * Rotates Tile 60° degrees clockwise
     * 
     */
    public void rotateClockwise() {

        LineType last = lineTypes[MAX_TILE_EDGES - 1];

        for (int i = MAX_TILE_EDGES - 2; i >= 0; i--)
            lineTypes[i + 1] = lineTypes[i];

        lineTypes[0] = last;
    }

    /**
     * Rotates Tile 60° degrees counter-clockwise
     * 
     */
    public void rotateCounterClockwise() {

        LineType first = lineTypes[0];

        for (int i = 1; i < MAX_TILE_EDGES; i++)
            lineTypes[i - 1] = lineTypes[i];

        lineTypes[MAX_TILE_EDGES - 1] = first;
    }

    /**
     * Returns whether this Tile is empty (String representation: "------")
     * 
     * @return true if empty
     * 
     */
    public boolean isEmpty() {
        return this.toString().equals("------");
    }

    /**
     * Returns whether this Tile is equal when rotated accordingly
     * 
     * @param otherTile
     *            other Tile
     * @return true if Tile is equal in orientation
     * 
     */
    public boolean isRotationEqualTo(Tile otherTile) {

        Tile otherTileCopy = otherTile.copy();

        // Rotate the copy once completely and check each permutation
        for (int i = 0; i < MAX_TILE_EDGES; i++) {

            if (otherTileCopy.isExactlyEqualTo(this))
                return true;

            otherTileCopy.rotateClockwise();
        }

        return false;
    }

    /**
     * Returns whether this Tile can be made equal through recoloring
     * 
     * @param otherTile
     *            other Tile
     * @return true if Tile is color-equal
     * 
     */
    public boolean canBeRecoloredTo(Tile otherTile) {

        LineType[] colors = new LineType[] { LineType.RED, LineType.GREEN, LineType.YELLOW };

        // Iterate through all possibilites of recoloring
        Tile[] permutations = new Tile[27];

        // Create all possibilites
        /*
         * Warning: These do not only create duplicates but they generate
         * invalid linetype arrangements however these are harmless, as we can
         * expect only valid inputs.
         */
        for (int i = 0; i < colors.length; i++) {
            for (int j = 0; j < colors.length; j++) {
                for (int k = 0; k < colors.length; k++) {

                    Tile recolor = this.copy();

                    // Switch colors
                    for (int position = 0; position < MAX_TILE_EDGES; position++) {

                        // When indices are 0,1,2 then change to
                        // red,green,yellow accordingly
                        switch (recolor.lineTypes[position]) {

                        case RED:
                            recolor.lineTypes[position] = colors[i];
                            break;

                        case GREEN:
                            recolor.lineTypes[position] = colors[j];
                            break;

                        case YELLOW:
                            recolor.lineTypes[position] = colors[k];
                            break;

                        default:
                            break;
                        }
                    }

                    permutations[9 * i + 3 * j + k] = recolor;
                }
            }
        }
        // Check if there is a possible recolor
        for (Tile recolor : permutations) {
            if (recolor.isExactlyEqualTo(otherTile))
                return true;
        }

        return false;
    }

    /**
     * Returns whether this Tile could be made equal by adding lines. A Tile
     * never dominates itself.
     * 
     * @param otherTile
     * @return true if it dominates
     */
    public boolean dominates(Tile otherTile) {

        // A Tile never dominates itself
        if (isRotationEqualTo(otherTile))
            return false;

        // Watch for potential "free spots"
        for (int i = 0; i < MAX_TILE_EDGES; i++) {

            // If the dominating Tile has a line at this position
            if (lineTypes[i].isColor()) {

                // the dominated Tile should either have a free spot, or be the
                // same
                // line type, else return false
                if (otherTile.lineTypes[i] != LineType.NONE && lineTypes[i] != otherTile.lineTypes[i])
                    return false;

            } else {

                // If the dominating Tile has a free spot, the dominated tile
                // should
                // also, else return false
                if (otherTile.lineTypes[i] != LineType.NONE)
                    return false;

            }
        }

        return true;
    }

    /**
     * Returns whether this Tile has the same color as the other. Orientation
     * and position of connections do not matter in this case.
     * 
     * @param otherTile
     *            other Tile
     * @return true if Tile has same colors
     * 
     */
    public boolean hasSameColorsAs(Tile otherTile) {

        boolean[] thisColors = getColors(this);
        boolean[] otherColors = getColors(otherTile);

        for (int i = 0; i < thisColors.length; i++) {
            if (thisColors[i] != otherColors[i])
                return false;
        }
        return true;
    }

    /**
     * Returns the available colors in Tile LineType at index 0 represents red
     * LineType at index 1 represents green LineType at index 2 represents
     * yellow
     * 
     * @param tile
     * @return boolean array of available colors
     */
    private boolean[] getColors(Tile tile) {

        // First element holds whether or not it's red,
        // second element green, third yellow
        boolean[] colors = new boolean[3];

        colors[0] = tile.toString().contains("R");
        colors[1] = tile.toString().contains("G");
        colors[2] = tile.toString().contains("Y");

        return colors;
    }

    /**
     * Returns a string representation of a Tile.
     * 
     * <p>
     * Example format: <blockquote>
     * 
     * <pre>
     * -Y---Y
     * </pre>
     * 
     * </blockquote>
     */
    @Override
    public String toString() {
        String string = "";

        for (int i = 0; i < MAX_TILE_EDGES; i++) {
            string += lineTypes[i].getAbbreviation();
        }

        return string;
    }

    /**
     * Returns whether Tiles fit together. Tiles do not fit together when the
     * connected lines are of different color
     * 
     * @param otherTile
     *            Tile that is connected
     * @param position
     *            position where it's connected
     * @return true if Tiles fit
     */
    public boolean fitsTo(Tile otherTile, int position) {

        // Where this Tile is attached to from otherTile's point of reference
        int oppositePosition = getOppositePosition(position);

        // If both Tiles have a connection
        if (lineTypes[position].isColor() && otherTile.lineTypes[oppositePosition].isColor()) {

            // They need to be of the same color
            return lineTypes[position] == otherTile.lineTypes[oppositePosition];
        }

        return true;

    }

    /**
     * Returns Integer of where this Tile is attached to from its neighbour's
     * point of view
     * 
     * @param position
     *            neighbour's position
     * @return position in view of neighbor
     */
    private static int getOppositePosition(int position) {
        return (position + 3) % 6;
    }
}
