package com.rr.server.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jire
 */
public final class BufferPool {

    private static final int DEFAULT_POTS = 32;

    private static final int PARENT_BUFFER_SIZE = 1024 * 1024;

    private final List<List<ByteBuffer>> pots;

    private ByteBuffer parentBuffer = malloc(PARENT_BUFFER_SIZE);

    public BufferPool(int numberOfPots) {
        pots = new ArrayList<>(numberOfPots);
        for (int i = 0; i < numberOfPots; i++)
            pots.add(new ArrayList<>());
    }

    public BufferPool() {
        this(DEFAULT_POTS);
    }

    public ByteBuffer get(int bytes) {
        int size = allocSize(bytes);
        int index = Integer.numberOfTrailingZeros(size);
        List<ByteBuffer> pot = pots.get(index);

        ByteBuffer buffer = pot.isEmpty() ? alloc(size) : pot.remove(pot.size() - 1);
        buffer.position(0).limit(bytes);
        for (int i = 0, n = buffer.remaining(); i < n; i++)
            buffer.put(i, (byte) 0);

        return buffer;
    }

    public void add(ByteBuffer buffer) {
        int alloc = allocSize(buffer.capacity());
        if (buffer.capacity() != alloc)
            throw new IllegalArgumentException("buffer capacity not a power of two");

        int index = Integer.numberOfTrailingZeros(alloc);
        pots.get(index).add(buffer);
    }

    private ByteBuffer alloc(int bytes) {
        if (bytes > PARENT_BUFFER_SIZE)
            return malloc(bytes);

        if (bytes > parentBuffer.remaining())
            parentBuffer = malloc(PARENT_BUFFER_SIZE);

        parentBuffer.limit(parentBuffer.position() + bytes);
        ByteBuffer slice = parentBuffer.slice();
        parentBuffer.position(parentBuffer.limit());
        return slice;
    }

    public void flush() {
        for (List<ByteBuffer> list : pots)
            list.clear();
    }

    private static int allocSize(int bytes) {
        if (bytes <= 0)
            throw new IllegalArgumentException();
        return (bytes > 1) ? Integer.highestOneBit(bytes - 1) << 1 : 1;
    }

    private static ByteBuffer malloc(int bytes) {
        return ByteBuffer.allocateDirect(bytes).order(ByteOrder.nativeOrder());
    }

}