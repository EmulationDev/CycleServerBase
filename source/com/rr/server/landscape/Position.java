package com.rr.server.landscape;

/**
 * Represents a position in the game world, also contains logical methods for
 * identifying regions.
 *
 * @author Christian Tucker
 */
public class Position {

    /**
     * The x coordnate.
     */
    private int x;

    /**
     * The y coordnate.
     */
    private int y;

    /**
     * The height
     */
    private int height;

    /**
     * Creates a new position at the specified x and y coordinates, on a height of 0.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     */
    public Position(int x, int y) {
        this(x, y, 0);
    }

    /**
     * Creates a new position at the specified x and y coordinate, and height.
     *
     * @param x The x coordinate.
     * @param y The y coordinate.
     * @param height    The height.
     */
    public Position(int x, int y, int height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }
}
