package com.rr.server.entity;

/**
 * Represents an object in the game world.
 *
 * @author Christian Tucker
 */
public abstract class Entity {

    /**
     * The id of the entity.
     */
    private int id;

    /**
     * Creates a new entity with the specified id.
     *
     * @param id    The id.
     */
    public Entity(int id) {
        this.id = id;
    }

    /**
     * Returns the id of the entity.
     *
     * @return  The id.
     */
    public int getId() {
        return id;
    }

}
