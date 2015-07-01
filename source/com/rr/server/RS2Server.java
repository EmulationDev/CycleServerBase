package com.rr.server;

import com.rr.World;
import com.rr.server.engine.RS2Engine;
import com.rr.server.engine.task.Task;
import com.rr.server.entity.EntityContainer;
import com.rr.server.entity.mob.player.Player;
import com.rr.server.io.network.NetworkAcceptHandler;
import com.rr.server.io.network.NetworkSession;
import com.rr.server.landscape.Region;
import com.rr.server.landscape.Regions;
import com.rr.server.util.BufferPool;
import com.rr.server.util.Logger;

import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A game-server instance.
 *
 * @author Christian Tucker
 */
public class RS2Server {

    /**
     * The engine processing logic for this server.
     */
    private RS2Engine engine;

    /**
     * The world.
     */
    private World world;

    /**
     * The executor used to handle incoming network IO.
     */
    private final ExecutorService readExecutor = Executors.newSingleThreadExecutor();

    /**
     * A buffer pool for networking IO.
     */
    private BufferPool bufferPool = new BufferPool();

    /**
     * A collection of {@code NetworkSession}'s currently being handled.
     */
    private Set<NetworkSession> sessions = new HashSet<>();

    /**
     * A collection of {@code Player}'s that are inside the game world.
     */
    private EntityContainer<Player> players = new EntityContainer<>(Player.class, 2000);

    /**
     * The {@code AsynchronousServerSocketChannel} handling network IO for this server.
     */
    private AsynchronousServerSocketChannel channel;

    /**
     * The {@code AsynchronousChannelGroup} for the server channel.
     */
    private AsynchronousChannelGroup group;

    /**
     *  The handler that accepts connections for the server.
     */
    private NetworkAcceptHandler handler;

    /**
     * A handler for all {@code Region}'s handled for the server.
     */
    private Regions regions;

    /**
     * The {@code Logger} for printing data.
     */
    private Logger logger;

    /**
     * Creates a new server with the specified identification number.
     *
     * @param world    The World.
     */
    public RS2Server(World world) {
        try {
            this.world = world;
            this.logger = new Logger(getClass(), world);
            this.regions = new Regions(this);
            handler = new NetworkAcceptHandler(this);
            engine = new RS2Engine();
            group = AsynchronousChannelGroup.withThreadPool(readExecutor);
            channel = AsynchronousServerSocketChannel.open(group);
            channel.bind(new InetSocketAddress(43593 + world.getId()));
            channel.accept(channel, handler);
            engine.start();
            engine.addTask(Task.repeated(1, () -> players.forEach(Player::pulse)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the logger instance for this class.
     *
     * @return  The logger instance.
     */
    public Logger getLogger() {
        return logger;
    }

    /**
     * Returns the {@code BufferPool} for this server.
     *
     * @return  The BufferPool.
     */
    public BufferPool getBufferPool() {
        return bufferPool;
    }

    /**
     * Returns the {@code World} for this server.
     *
     * @return  The world.
     */
    public World getWorld() {
        return world;
    }

    /**
     * Returns a collection of sessions being handled by the serverion.
     *
     * @return  The session collection.
     */
    public Set<NetworkSession> getSessions() {
        return sessions;
    }

    /**
     * Returns a collection of players inside the game world.
     *
     * @return  The player collection.
     */
    public EntityContainer<Player> getPlayers() {
        return players;
    }

    /**
     * Returns the {@code Region} for the specified id.
     *
     * @param regionId  The id of the region.
     * @return  The region with the specified id.
     */
    public Region get(int regionId) {
        return regions.get(regionId);
    }

}
