package com.rr.server.landscape;

import com.rr.server.entity.mob.player.Player;

import java.util.HashSet;
import java.util.Set;

/**
 * A region is a X by X tile area in the game-world in which the client
 * processes visual data.
 *
 * @author Christian Tucker
 */
public class Region {

    /**
     * A collection of players that are inside this region.
     */
    private Set<Player> players = new HashSet<>(16);

    /**
     * Attempts to add a player to the region.
     *
     * @param player    The player to add.
     * @return  If the player was added successfully.
     */
    public boolean addPlayer(Player player) {
        return players.add(player);
    }

    /**
     * Attempts to remove a player from the region.
     *
     * @param player    The player to remove.
     * @return  If the player was removed successfully.
     */
    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    /**
     * Checks to see if the player is inside the region.
     *
     * @param player    The player to check for.
     * @return  If the player is inside the region.
     */
    public boolean contains(Player player) {
        return players.contains(player);
    }

    /**
     * Returns the collection of players inside the region.
     *
     * @return  The collection of players.
     */
    public Set<Player> getPlayers() {
        return players;
    }

}
