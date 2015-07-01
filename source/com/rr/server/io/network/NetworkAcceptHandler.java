package com.rr.server.io.network;

import com.rr.server.RS2Server;

import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class NetworkAcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

    private RS2Server server;

    public NetworkAcceptHandler(RS2Server server) {
        this.server = server;
    }

    @Override
    public void completed(AsynchronousSocketChannel client, AsynchronousServerSocketChannel serverChannel) {
        NetworkSession session = new NetworkSession(server, client);
        session.readStream();
        serverChannel.accept(serverChannel, this);
    }

    @Override
    public void failed(Throwable exc, AsynchronousServerSocketChannel server) {
        exc.printStackTrace();
    }
}
