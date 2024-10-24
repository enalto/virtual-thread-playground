package com.enalto.section01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    public static void ioIntensive(int i) {

        try {
            LOGGER.info("Starting io task {}. Thread information: {}", i, Thread.currentThread());
            Thread.sleep(Duration.ofSeconds(5));
            LOGGER.info("Finished io task {} Thread information: {}", i, Thread.currentThread());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
