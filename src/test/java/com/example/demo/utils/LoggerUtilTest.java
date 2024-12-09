package com.example.demo.utils;

import java.io.File;
import java.util.logging.Level;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoggerUtilTest {

    // Test logic: Ensure the LoggerUtil initializes and configures the logger
    // correctly.
    @Test
    public void testLoggerInitialization() {
        assertNotNull(LoggerUtil.logger);
        assertEquals(Level.ALL, LoggerUtil.logger.getLevel());
    }

    // Test logic: Ensure the logger has at least one handler (console handler is
    // built-in)
    @Test
    public void testLoggerHandler() {
        assertTrue(LoggerUtil.logger.getHandlers().length != 0);
    }

    // Test logic: Ensure the log file is created
    @Test
    public void testLoggerFileExists() {
        LoggerUtil.logger.info("Testing Logger");
        File logFile = new File("SkyBattle.log");

        assertTrue(logFile.exists());
    }
}