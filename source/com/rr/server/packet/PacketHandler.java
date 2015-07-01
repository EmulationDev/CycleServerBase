package com.rr.server.packet;

import com.rr.server.entity.mob.player.Player;

/**
 * Interface for handling incoming packets.
 *
 * @author Christian Tucker
 */
public interface PacketHandler {

    /**
     * Handles the incoming packet for the specified player.
     *
     * @param player    The player.
     * @param packet    The packet.
     */
    void handle(Player player, Packet packet);
}
