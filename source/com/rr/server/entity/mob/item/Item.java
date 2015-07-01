package com.rr.server.entity.mob.item;

import com.rr.server.entity.Entity;

/**
 * Represents an item in the game world.
 *
 * @author Christian Tucker
 */
public final class Item extends Entity {

    /**
     * The amount of items in this stack.
     */
    private int amount;

    /**
     * Creates a new item with the specified id.
     *
     * @param id The id.
     */
    public Item(int id) {
        super(id);
    }

    /**
     * Creates a new item with the specified id and amount.
     *
     * @param id    The id.
     * @param amount    The amount.
     */
    public Item(int id, int amount) {
        super(id);
        this.amount = amount;
    }

    /**
     * Gets the amount of items in the stack.
     *
     * @return  The amount of items in the stack
     */
    public int getAmount() {
        return amount;
    }

}
