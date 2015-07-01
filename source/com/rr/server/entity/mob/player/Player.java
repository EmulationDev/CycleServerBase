package com.rr.server.entity.mob.player;

import com.rr.server.RS2Server;
import com.rr.server.entity.mob.Mob;
import com.rr.server.entity.mob.item.ItemContainer;
import com.rr.server.io.network.NetworkSession;
import com.rr.server.io.network.NetworkState;
import com.rr.server.landscape.Position;
import com.rr.server.landscape.Region;
import com.rr.server.packet.Packet;
import com.rr.server.util.AttributeType;
import com.rr.server.util.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a {@code Mob} in the game world controlled by a network client.
 *
 * @author Christian Tucker
 */
public final class Player extends Mob {

    /**
     * The server that this player is logged into.
     */
    private final RS2Server server;

    /**
     * The session handling Network IO for this player.
     */
    private final NetworkSession session;

    /**
     * The username of this player.
     */
    private final String username;

    /**
     * The username hash of this player.
     */
    private final long usernameHash;

    /**
     * The password of this player.
     */
    private final String password;

    /**
     * Rather or not the player has finished the initialization process.
     */
    private boolean initialized;

    /**
     * The players current region.
     */
    private Region region;

    /**
     * The logger instance for this class.
     */
    private Logger logger;

    /**
     * A container for the player's inventory.
     */
    private ItemContainer inventory = new ItemContainer(28);

    /**
     * A collection of attributes stored by their name.
     */
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * Creates a new player with the server, name, and position.
     *
     * @param server    The server this player is connected to.
     * @param username  The username of the player.
     * @param usernameHash  The hash of the username.
     * @param password  The password of the player.
     * @param position The position of the player.
     */
    public Player(RS2Server server, NetworkSession session, String username, long usernameHash, String password, Position position) {
        super(-1, position);
        this.server = server;
        this.session = session;
        this.username = username;
        this.usernameHash = usernameHash;
        this.password = password;
        this.index = server.getPlayers().add(this);
        if(index == -1) throw new AssertionError("World is full.");
        this.logger = new Logger(getClass(), server.getWorld());
        session.setPlayer(this);
        onInitComplete();
    }

    /**
     * Called whenever an external plugin has determined that the player's initialization
     * has been completed. This will allow the player the begin the update process.
     */
    public void onInitComplete() {
        session.setState(NetworkState.CONNECTED);
        initialized = true;
    }

    /**
     * Processed once every 600ms after the player has completed the initialization process.
     */
    @Override public void pulse() {
        if(initialized) {
            super.pulse();
        }
    }

    /**
     * Queues a packet to be sent to the client next cycle.
     *
     * @param packet    The packet.
     */
    public Player write(Packet packet) {
        System.out.println("called");
        session.queue(packet);
        return this;
    }

    /**
     * Returns the server that this player is connected to.
     *
     * @return  The server.
     */
    public RS2Server getServer() {
        return server;
    }

    /**
     * Returns the username of this player.
     *
     * @return  The username.
     */
    public final String getUsername() {
        return username;
    }

    /**
     * Returns the username hash of this player.
     *
     * @return  The username.
     */
    public final long getUsernameHash() {
        return usernameHash;
    }

    /**
     * Returns the player's current region.
     *
     * @return  The players current region.
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Returns a collection of regions calculated based on the players current
     * position relative to the surrounding regions.
     *
     * @return  The collection of visible regions.
     */
    public List<Region> getVisibleRegions() {
        return null;
    }

    /**
     * Returns the player's inventory container.
     *
     * @return  The inventory container.
     */
    public ItemContainer getInventory() {
        return inventory;
    }

    /**
     * Sets the attribute for the specified key.
     *
     * @param key   The key.
     * @param attribute The attribute.
     */
    public void setAttribute(String key, Object attribute) {
        attributes.put(key, attribute);
    }

    /**
     * Returns the requested attribute as an Object. If the attribute is null
     * the default value for the {@code AttributeType} is returned.
     *
     * @param type  The AttributeType
     * @param key   The requested attribute.
     * @return  The requested attribute.
     */
    public Object getAttribute(AttributeType type, String key) {
        Object attribute = attributes.get(key);
        if(Objects.isNull(attribute)) {
            logger.warning("Attribute for key \"" + key + "\" has not been set yet, returning null or equivalent.");
            if(type == AttributeType.OBJECT) return null;
            if(type == AttributeType.BOOLEAN) return Boolean.FALSE;
            if(type == AttributeType.NUMBER) return 0;
        }
        return attribute;
    }

    @Override public String toString() {
        return "Player[Username: " + username + "]";
    }
}
