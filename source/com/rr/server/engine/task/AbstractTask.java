package com.rr.server.engine.task;

/**
 * A {@code AbstractTask} is an abstract Implementation of the {@code Task} interface.
 *
 * @author Christian Tucker
 */
public abstract class AbstractTask implements Task {

    /**
     * Rather or not this task is currently pending execution.
     */
    protected boolean pendingExecution;

    /**
     * Called whenever the task needs to stop being executed.
     */
    public void stop() {
        if(!pendingExecution)
            throw new IllegalStateException("The task is already stopped.");
        pendingExecution = false;
    }

    /**
     * Called by {@code finish}, executes the logic for this task.
     */
    protected abstract void execute();

    /**
     * Called by the {@code RS2Engine}, invokes the tasks {@code execute} method and
     * returns the value of {@link #pendingExecution}.
     *
     * @return  Rather or not the task is pending execution.
     */
    @Override public boolean finish() {
        execute();
        return pendingExecution;
    }

}
