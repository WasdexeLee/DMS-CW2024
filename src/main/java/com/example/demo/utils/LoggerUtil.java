package com.example.demo.utils;

import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Utility class for creating and configuring a logger for the application.
 * This class provides a static logger instance that is configured to log
 * messages to both the console and a file. The logger is pre-configured with
 * handlers for console and file logging, and it captures messages at all
 * logging levels.
 * 
 * @author WasdexeLee (Lee Jia Zhe)
 */
public class LoggerUtil {

    /**
     * Static logger instance for the application.
     * This logger is configured to log messages to both the console (built-in) and a
     * file.
     */
    public static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    /**
     * Static initializer block to configure the logger.
     * This block sets up the logger to log messages to a file.
     * Logging to the console is built-in by the Logger class.
     * It also sets the logging level to capture all messages.
     */
    static {
        try {
            // Configure the logger to log to a file
            FileHandler fileHandler = new FileHandler("SkyBattle.log");
            fileHandler.setLevel(Level.ALL);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);

            // Set the logger level
            logger.setLevel(Level.ALL);
        }
        // Catch exception where initialization of the logger fails
        catch (Exception e) {
            logger.log(Level.SEVERE, "Error configuring logger", e);
        }
    }
}