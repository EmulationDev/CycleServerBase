package com.rr.server.engine;

import com.rr.server.engine.task.Task;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.*;

/**
 * Handles all of the logic for a {@code World}.
 */
public class RS2Engine implements Runnable {

    /**
     * The rate (in milliseconds) in which the engine cycles through queued tasks.
     */
    private static final int CYCLE_RATE = 600;

    /**
     * The executor used to execute the queued tasks.
     */
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    /**
     * The executor used to process the queued tasks once every {@code CYCLE_RATE} milliseconds.
     */
    private final ScheduledExecutorService scheduledExecutor = Executors.newSingleThreadScheduledExecutor();

    /**
     * A {@code BlockingQueue} implementation which holds the tasks needed to be executed.
     */
    private final LinkedBlockingQueue<Task> taskQueue = new LinkedBlockingQueue<>();

    /**
     * The collection of tasks that are being executed in the current cycle.
     */
    private final Queue<Task> taskPool = new LinkedList<>();

    /**
     * initializes the {@code RS2Engine}, and starts processing tasks.
     * <br>
     * <strong>Note:</strong> tasks can be queued before the engine is started.
     */
    public void start() {
        scheduledExecutor.scheduleAtFixedRate(this, CYCLE_RATE, CYCLE_RATE, TimeUnit.MILLISECONDS);
    }

    /**
     * Adds a {@code Task} to the {@code #taskQueue} to be processed each cycle.
     *
     * @param task  The task added to the queue.
     */
    public void addTask(Task task) {
        taskQueue.add(task);
    }

    /**
     * Called once every {@code CYCLE_RATE} milliseconds by the {@code scheduledExecutor}.
     * Iterates over each queued task and executes it, placing it back into the Queue if required.
     */
    @Override public void run() {
        taskQueue.drainTo(taskPool);
        while(!taskPool.isEmpty()) {
            Task task = taskPool.remove();
            if(Objects.isNull(task)) continue;
            if(!task.finish())
                addTask(task);
        }
    }
}
