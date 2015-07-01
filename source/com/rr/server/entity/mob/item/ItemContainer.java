package com.rr.server.entity.mob.item;

import com.rr.server.entity.EntityContainer;

/**
 * A special {@code EntityContainer} implementation to store {@code Item}'s.
 *
 * @author Christian Tucker
 */
public class ItemContainer extends EntityContainer<Item> {

    /**
     * Creates a new container with the provided capacity.
     *
     * @param capacity The capacity of this container.
     */
    public ItemContainer(int capacity) {
        super(Item.class, capacity);
    }

    /**
     * Returns true or false depending on if the specified item was found in the container.
     *
     * @param item    The item to search for.
     * @return  true or false depending on if the specified item was found in the container.
     */
    @Override
    public boolean contains(Item item) {
        return super.contains(item);
    }

    /**
     * Returns true or false depending on if the specified items were found in the container.
     *
     * @param items  The items to search for.
     * @return  true or false depending on if the specified items were found in the container.
     */
    @Override
    public boolean contains(Item... items) {
        return super.contains(items);
    }

    /**
     * Adds an item to the container and returns the index of the item in the container.
     *
     * @param item    The item to be added.
     * @return  The index of the entity that was added.
     */
    @Override
    public int add(Item item) {
        return super.add(item);
    }

    /**
     * Removes the specified item from the container, has O(n) speed, use {@code #remove(int)} when possible.
     *
     * @param item    The entity to be removed from the container.
     * @return  True if the entity was removed, false if it was not in the container.
     */
    @Override public boolean remove(Item item) {
        return super.remove(item);
    }

    /**
     * Removes the item with the specified index from the cotnainer.
     *
     * @param index The index of the item to remove.
     * @return true.
     */
    @Override
    public boolean remove(int index) {
        return super.remove(index);
    }

}
