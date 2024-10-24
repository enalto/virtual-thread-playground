package com.enalto.section01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.Duration;

public class Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    public static void ioIntensive(int i) {

        try {
            LOGGER.info("Starting io task {}", i);
            Thread.sleep(Duration.ofSeconds(10));
            LOGGER.info("Finished io task {}", i);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
