package com.rr.server.engine.task;

/**
 * A {@link Task} that is executed once per {@code RS2Engine} cycle.
 *
 * @author Christian Tucker
 */
public abstract class TickTask extends AbstractTask {

    /**
     * The amount of ticks this task was set for.
     */
    private final int ticks;

    /**
     * The amount of ticks remaining until execution.
     */
    private int countdown;

    /**
     * Creates a new {@code TickTask} instance setting the
     * countdown and tick timer to the specified value.
     *
     * @param ticks The amount of ticks between execution.
     */
    public TickTask(int ticks) {
        this.ticks = countdown = ticks;
    }

    /**
     * Reduces the countdown timer by a single tick and executes
     * the task's function if the countdown timer reaches zero.
     * The countdown timer is then reset to equal the value of {@code ticks}.
     *
     * @return  If the task is pending execution.
     */
    @Override public final boolean finish() {
        if(--countdown < 1) {
            countdown = ticks;
            return super.finish();
        }
        return !pendingExecution;
    }
}
