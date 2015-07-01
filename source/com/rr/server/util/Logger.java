package com.rr.server.util;

import com.rr.World;

/**
 * A utility for logging information.
 *
 * @author Christian Tucker
 */
public class Logger {

    /**
     * The {@code Class} this logger prints for.
     */
    protected Class<?> clazz;

    /**
     * The {@code World} this logger is running on.
     */
    private World world;

    /**
     * Creates a new logger instance for the provided class and world.
     *
     * @param clazz The class.
     * @param world The world.
     */
    public Logger(Class<?> clazz, World world) {
        this.clazz = clazz;
        this.world = world;
    }

    /**
     * Prints out the message provided, with an INFO prefix.
     *
     * @param message   The message
     */
    public void log(String message) {
        print(false, "[INFO]", message);
    }

    /**
     * Prints out the message provided, with an WARNING prefix.
     *
     * @param message   The message
     */
    public void warning(String message) {
        print(false, "[WARNING]", message);
    }

    /**
     * Prints out the message provided, with an ERROR prefix.
     *
     * @param message   The message
     */
    public void error(String message) {
        print(true, "[ERROR]", message);
    }

    /**
     * If the world {@link World#isDebugging()}. prints out the message provided,
     * with an DEBUG prefix.
     *
     * @param message   The message
     */
    public void debug(String message) {
        if(world.isDebugging()) {
            print(false, "[DEBUG]", message);
        }
    }


    /**
     * Builds and prints out the provided message on the correct stream based on the error parameter.
     *
     * @param error If this should be printed on the System.err stream
     * @param tag   The prefix between the world and class name.
     * @param message   The message to be printed.
     */
    private void print(boolean error, String tag, String message) {
        if(error) {
            System.err.println("[" + world + "]"+tag+"["+clazz.getSimpleName()+"]: " +message);
        } else {
            System.out.println("[" + world + "]"+tag+"["+clazz.getSimpleName()+"]: " +message);
        }
    }

}
