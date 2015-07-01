package com.rr.server.landscape;

import com.rr.server.RS2Server;

import java.util.HashMap;
import java.util.Map;

/**
 * A handler class for all of the {@code Region}'s being handled by the server.
 *
 * @author Christian Tucker
 */
public class Regions {

    /**
     * The server the regions belong to.
     */
    private RS2Server server;

    /**
     * A collection of regions mapped by their id.
     */
    private Map<Integer, Region> regions = new HashMap<>();

    /**
     * Creates a new instance of the Regions class for the specified server.
     *
     * @param server    The server to handle regions for.
     */
    public Regions(RS2Server server) {
        this.server = server;
    }

    /**
     * Gets a specific region based on its id, if the region does not exist it gets created.
     *
     * @param regionId  The region id.
     * @return  The region.
     */
    public Region get(int regionId) {
        return regions.getOrDefault(regionId, regions.put(regionId, new Region()));
    }

    /**
     * Returns the server this manager is working for.
     *
     * @return  The server.
     */
    public RS2Server getServer() {
        return server;
    }

}
