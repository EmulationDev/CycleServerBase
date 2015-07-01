package com.rr.server.entity.mob;

import com.rr.server.entity.Entity;
import com.rr.server.landscape.Position;

/**
 * Represents an object that is alive in the game world.
 *
 * @author Christian Tucker
 */
public abstract class Mob extends Entity {

    /**
     * The position of this mob.
     */
    private Position position;

    /**
     * The index of this mob, used for updating.
     */
    protected int index;

    /**
     * Creates a new mob with the specified id and position.
     *
     * @param id    The id of the mob.
     * @param position  The position of the mob.
     */
    public Mob(int id, Position position) {
        super(id);
        this.position = position;
    }

    /**
     * Processed once every 600ms.
     */
    public void pulse() {
    }

    /**
     * Returns the position of the mob.
     *
     * @return  The position.
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Returns the index of the mob.
     *
     * @return  The index.
     */
    public int getIndex() {
        return index;
    }


}
