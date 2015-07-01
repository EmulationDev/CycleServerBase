package com.rr.server.io.network;

import com.rr.server.RS2Server;
import com.rr.server.entity.mob.player.Player;
import com.rr.server.packet.Packet;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Used as a persistant state for the {@code AsynchronousSocketChannel} to handle network IO functions.
 *
 * @author Christian Tucker
 */
public class NetworkSession {

    /**
     * The default size for a incoming packet.
     */
    private static final int DEFAULT_READ_BUFFER_SIZE = 2096;

    /**
     * The default size for writing grouped packet.
     */
    private static final int DEFAULT_WRITE_BUFFER_SIZE = 5_000;

    /**
     * The server this session is relative to.
     */
    private RS2Server server;

    /**
     * The channel handling network IO for this session.
     */
    private AsynchronousSocketChannel channel;

    /**
     * The player belonging to this NetworkSession.
     */
    private Optional<Player> player = Optional.empty();

    /**
     * A queue of packets to be sent to the client.
     */
    private LinkedBlockingQueue<Packet> packetQueue = new LinkedBlockingQueue<>();

    /**
     * The current state of the network.
     */
    private NetworkState state = NetworkState.HANDSHAKING;

    /**
     * Creates a new NetworkSession instance for the {@code AsynchronousSocketChannel} relative to
     * the specified server.
     *
     * @param server    The server handling this connection.
     * @param channel   The channel processing IO for this connection.
     */
    public NetworkSession(RS2Server server, AsynchronousSocketChannel channel) {
        this.server = server;
        this.channel = channel;
        server.getLogger().log("New session created");
        server.getSessions().add(this);
    }

    public void readStream() {
        if(!channel.isOpen()) {
            disconnect();
            return;
        }

        if(state == NetworkState.DISCONNECTED)
            return;

        ByteBuffer buffer = server.getBufferPool().get(DEFAULT_READ_BUFFER_SIZE);
        channel.read(buffer, this, new CompletionHandler<Integer, NetworkSession>() {
            @Override
            public void completed(Integer result, NetworkSession session) {
                try {

                } finally {
                    server.getBufferPool().add(buffer);
                    readStream();
                }
            }

            @Override
            public void failed(Throwable exc, NetworkSession attachment) {
                exc.printStackTrace();
                disconnect();
            }
        });
    }

    /**
     * Writes the specified packet to the client.
     *
     * @param packet    The packet.
     */
    public void queue(Packet packet) {
        packetQueue.add(packet);
    }

    /**
     * Called whenever the {@code AsynchronousSocketChannel} loses connection.
     */
    public void disconnect() {
        System.out.println("Called");
        setState(NetworkState.DISCONNECTED);
        try {
            channel.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        player = Optional.empty();
        server.getSessions().remove(this);
    }


    /**
     * Gets a buffer with the specified
     * @param size  The size of the buffer to obtain from the {@code BufferPool}
     * @return  The {@code ByteBuffer}
     */
    public ByteBuffer getBuffer(int size) {
        return server.getBufferPool().get(size);
    }

    /**
     * Puts the specified buffer back into the buffer pool.
     *
     * @param buffer    The buffer.
     */
    public void putBuffer(ByteBuffer buffer) {
        server.getBufferPool().add(buffer);
    }

    /**
     * Returns the server relative to this session.
     *
     * @return  The relative server.
     */
    public RS2Server getServer() {
        return server;
    }

    /**
     * Returns the channel performing network IO for this session.
     *
     * @return  The channel.
     */
    public AsynchronousSocketChannel getChannel() {
        return channel;
    }

    /**
     * Sets the current network state to the specified state.
     *
     * @param state The state.
     */
    public void setState(NetworkState state) {
        this.state = state;
    }

    /**
     * Returns the current state of the network for this session.
     *
     * @return  The current state of the network.
     */
    public NetworkState getState() {
        return state;
    }

    /**
     * Sets this sessions player.
     *
     * @param player    The player.
     * @return  This session.
     */
    public NetworkSession setPlayer(Player player) {
        this.player = Optional.ofNullable(player);
        return this;
    }

    /**
     * Returns an {@code Optional} value of the player attached to this session.
     *
     * @return  an Optional player.
     */
    public Optional<Player> getPlayer() {
        return player;
    }
}
