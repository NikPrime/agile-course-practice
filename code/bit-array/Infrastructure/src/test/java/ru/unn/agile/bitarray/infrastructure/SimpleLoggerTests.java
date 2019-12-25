package ru.unn.agile.bitarray.infrastructure;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import static org.junit.Assert.*;

public class SimpleLoggerTests {
    private static final String LOG_FILE = "./simple_logger_test.log";
    private SimpleLogger logger;

    @Before
    public void setUp() {
        logger = new SimpleLogger(LOG_FILE);
    }

    @Test
    public void canConstruct() {
        assertNotNull(logger);
    }

    @Test
    public void loggerCreatesRealLogFile() {
        try {
            new BufferedReader(new FileReader(LOG_FILE));
        } catch (FileNotFoundException e) {
            fail(LOG_FILE + " must be created by the logger in ctor.");
        }
    }

}
