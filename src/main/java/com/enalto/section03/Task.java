package com.enalto.section03;

import com.enalto.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task {

    private static final Logger LOGGER = LoggerFactory.getLogger(Task.class);

    public static void cpuIntensive(int i) {
        //LOGGER.info("Starting CPU intensive task, Thread info: {}", Thread.currentThread());
        var timeTaken = CommonUtils.timer(() -> findFibonacci(i));
        //LOGGER.info("CPU intensive task finished, Time taken: {}", timeTaken);
    }

    private static long findFibonacci(long n) {
        if (n < 2) {
            return n;
        }
        return findFibonacci(n - 1) + findFibonacci(n - 2);

    }
}
