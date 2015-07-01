package com.rr;

import com.rr.server.RS2Server;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a Individual Game-Server known by the client as a World.
 *
 * @author Christian Tucker
 */
public class World {

    /**
     * A map containing all of the active worlds referenced by their id.
     */
    private static Map<Integer, World> worldList = new HashMap<>();

    /**
     * Creates and returns a new instance of the {@code World} class.
     *
     * @param id    The id of the world created.
     * @param members   If the world is members only.
     * @param debugging Rather or not the server is debugging.
     * @return  The world.
     */
    public static World create(final int id, final boolean members, final boolean debugging) {
        try {
            return new World(id, members, debugging);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * The id of the world.
     */
    private final int id;

    /**
     * Rather or not this world requires membership to login.
     */
    private final boolean members;

    /**
     * The server that is handling all source.networking for this world.
     */
    private final RS2Server server;

    /**
     * Rather or not debug messages will print for this world.
     */
    private boolean debugging;

    /**
     * Creates a new instance of the {@code World} class with the specified configurations.
     *
     * @param id    The id of the world, used by the client for connections.
     * @param members   Rather or not this server is reserved for members only.
     * @param debugging Rather or not this server is printing debug messages.
     */
    private World(final int id, final boolean members, final boolean debugging) {
        this.id = id;
        this.members = members;
        this.debugging = debugging;
        this.server = new RS2Server(this);
    }

    /**
     * Returns the {@link #id} of this world.
     *
     * @return  The id.
     */
    public final int getId() {
        return id;
    }

    /**
     * Returns the {@link #members} setting of this world.
     *
     * @return  If the world is members only.
     */
    public final boolean isMembersOnly() {
        return members;
    }

    /**
     * Returns the {@link #server} for this world.
     *
     * @return  Returns this worlds server processor.
     */
    public final RS2Server getServer() {
        return server;
    }

    /**
     * Returns rather or not this world is in debug mode.
     *
     * @return  true if the server is in debug mode.
     */
    public boolean isDebugging() {
        return debugging;
    }


    @Override public String toString() {
        return "World " + id;
    }
}
