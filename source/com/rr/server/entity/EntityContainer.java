package com.rr.server.entity;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * A collection used to store and manage {@code Entity}'s
 *
 * @param <T>   The Entity subclass being collected.
 */
public class EntityContainer<T extends Entity> {

    /**
     * The backing array of {@code Entity}'s
     */
    protected T[] array;

    /**
     * Creates a new container for the specified class, with the set capacity.
     *
     * @param c The class being stored in this container.
     * @param capacity  The capacity of this container.
     */
    @SuppressWarnings("unchecked")
    public EntityContainer(Class<?> c, int capacity) {
        array = (T[]) Array.newInstance(c, capacity);
    }

    /**
     * Adds an entity to the container and returns the index of the entity in the container.
     *
     * @param t    The entity to be added.
     * @return  The index of the entity that was added.
     */
    public int add(T t) {
        int slot = findFirstSlot();
        if(slot < 0) throw new AssertionError("No free slots available.");
        array[slot] = t;
        return slot;
    }

    /**
     * Removes the specified entity from the container, has O(n) speed, use {@code #remove(int)} when possible.
     *
     * @param t    The entity to be removed from the container.
     * @return  True if the entity was removed, false if it was not in the container.
     */
    public boolean remove(T t) {
        for(int i = 0; i < array.length; i++) {
            if (t.equals(array[i])) {
                array[i] = null;
                return true;
            }
        }
        return false;
    }

    /**
     * Removes the entity with the specified index from the cotnainer.
     *
     * @param index The index of the entity to remove.
     * @return true.
     */
    public boolean remove(int index) {
        array[index] = null;
        return true;
    }

    /**
     * Returns true or false depending on if the specified entity was found in the container.
     *
     * @param t    The entity to search for.
     * @return  true or false depending on if the specified entity was found in the container.
     */
    public boolean contains(T t) {
        for(int i = 0; i < array.length; i++) {
            if(t.equals(array[i])) return true;
        }
        return false;
    }

    /**
     * Returns true or false depending on if the specified entities were found in the container.
     *
     * @param entities  The entities to search for.
     * @return  true or false depending on if the specified entities were found in the container.
     */
    public boolean contains(T... entities) {
        int index = 0;
        boolean[] b = new boolean[entities.length];
        for(int i = 0; i < array.length; i++) {
            if(!Objects.isNull(array[i])) {
                for(T t : entities) {
                    if(t.equals(array[i])) {
                        b[index++] = true;
                    }
                }
            }
        }
        for(boolean bool : b) {
            if(!bool) return false;
        }
        return true;
    }

    /**
     * Moves an entity from one slot to another, if an entity is in the destination slot the entity index's will
     * be switched.
     *
     * @param original  The original slot.
     * @param destination   The destination slot.
     */
    public void swapIndex(int original, int destination) {
        T originalEntity = array[original];
        T otherEntity = array[destination];
        if(Objects.isNull(originalEntity)) return;
        if(Objects.isNull(otherEntity)) {
            array[original] = null;
            array[destination] = originalEntity;
        } else {
            array[original] = otherEntity;
            array[destination] = originalEntity;
        }
    }

    /**
     * Locates the first free index in the container.
     *
     * @return  The first index.
     */
    private int findFirstSlot() {
        for(int i = 0; i < array.length; i++)
            if(Objects.isNull(array[i]))
                return i;
        return -1;
    }

    /**
     * Allows lambdas iteration.
     *
     * @param action    The action to perform.
     */
    public void forEach(Consumer<? super T> action) {
        for(T t : array)
            if(!Objects.isNull(t))
                action.accept(t);
    }

    /**
     * Returns the amount of entities in this container.
     *
     * @return  The amount of entities in this container.
     */
    public int size() {
        int size = 0;
        for(T t : array)
            size++;
        return size;
    }

    /**
     * Returns the maximum size of this container.
     *
     * @return  The maximum size.
     */
    public int capacity() {
        return array.length;
    }

    /**
     * Returns the backing array of this container.
     *
     * @return  The backing array.
     */
    public T[] array() {
        return array;
    }

}
