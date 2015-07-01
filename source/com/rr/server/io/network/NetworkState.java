package com.rr.server.io.network;

/**
 * Different states as used by the {@code NetworkSession} to handle how data is handled.
 */
public enum NetworkState {

    /**
     * Whenever the client has been disconnected either by losing connection, or removed
     * forcefully from the server. This state prevents data from being transmitted.
     */
    DISCONNECTED,

    /**
     * The initial state upon receiving a connection.
     */
    HANDSHAKING,

    /**
     * The state represents the session is waiting to process an update request.
     */
    UPDATING,

    /**
     * This state represents the session is waiting to process a login request.
     */
    LOGGING_IN,

    /**
     * This state represents a client that is connected to the server and receiving |
     * transmitting packets normally.
     */
    CONNECTED

}
