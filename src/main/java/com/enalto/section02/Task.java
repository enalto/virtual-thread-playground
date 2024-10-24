package com.enalto.section02;

import com.enalto.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);


    public static void main(String[] args) {
    }

    public static void run(int i) {
        LOGGER.info("Starting Task #{}", i);
        try {
            method1(i);
        } catch (Exception e) {
            LOGGER.error("Error {} in {}", i, e.getMessage());
        }
        LOGGER.info("Ending Task #{}", i);
    }

    private static void method1(int i) {
        CommonUtils.threadSleep(Duration.ofMillis(300));
        try {
            method2(i);
        } catch (Exception e) {
            // LOGGER.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void method2(int i) {
        CommonUtils.threadSleep(Duration.ofMillis(100));
        method3(i);
    }

    private static void method3(int i) {

        CommonUtils.threadSleep(Duration.ofMillis(500));

        if (i == 4) {
            throw new IllegalArgumentException("error");
        }
    }
}
