package com.rr.server.packet;

import com.rr.server.RS2Server;

import java.util.HashMap;
import java.util.Map;

/**
 * A class for managing packets.
 *
 * @author Christian Tucker
 */
public class PacketManager {

    /**
     * The server instance.
     */
    private RS2Server server;

    /**
     * A collection of packet handlers mapped to the appropriate opcode.
     */
    private Map<Integer, PacketHandler> packetHandlers = new HashMap<>();

    /**
     * Creates a new instance of the {@code PacketManager}
     * @param server    The server instance.
     */
    public PacketManager(RS2Server server) {
        this.server = server;
    }


}
