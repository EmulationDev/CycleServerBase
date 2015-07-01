package com.rr.server.engine.task;

/**
 * A {@code Task} is a set of instructions executed from within the
 * {@code RS2Engine} cycle.
 *
 * <br>
 *
 * Also contains helper methods for creating Tasks.
 *
 * @author Christian Tucker
 */
public interface Task {

    /**
     * Creates a task that is immediately executed and sets the
     * {@link AbstractTask#pendingExecution} value to true.
     *
     * @param runnable  The set of instructions to execute.
     * @return  The task created.
     */
    static Task immediate(Runnable runnable) {
        return () -> {
            runnable.run();
            return true;
        };
    }

    /**
     * Creates a {code TickTask} with the specified delay, which
     * is executed once before stopped automatically.
     *
     * @param delay The amount of cycles to wait before execution.
     * @param runnable  The set of instructions to be executed.
     * @return  The task created.
     */
    static Task delayed(int delay, Runnable runnable) {
        return new TickTask(delay) {
            @Override protected void execute() {
                runnable.run();
                stop();
            }
        };
    }

    /**
     * Creates a new {@code TickTask} using the specified task.
     *
     * @param delay The amount of cycles to wait before each execution.
     * @param task  The task to execute.
     * @return  The task created.
     */
    static Task repeated(int delay, Task task) {
        return new TickTask(delay) {
            @Override protected void execute() {
                if(task.finish())
                    stop();
            }
        };
    }

    /**
     * Creates a new {@code TickTask} uusing the specified set of instructions.
     *
     * @param delay The amount of cycles to wait before each execution.
     * @param runnable  The set of instructions to execute.
     * @return  The task created.
     */
    static Task repeated(int delay, Runnable runnable) {
        return repeated(delay, () -> {
            runnable.run();
            return false;
        });
    }

    /**
     * Returns true or false depending on if the Task is done executing.
     * If the task is not done executing it will be placed back into the execution pool.
     *
     * @return  If the task is done executing.
     */
    boolean finish();

}
