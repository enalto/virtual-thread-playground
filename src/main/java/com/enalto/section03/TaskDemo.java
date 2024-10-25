package com.enalto.section03;

import com.enalto.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;


public class TaskDemo {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskDemo.class);
    private static final int TASK_MAX = 5;
    //private static final int TASK_MAX = 3*Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) {
        //ThreadExecutor(Thread.ofPlatform());
        //ThreadExecutor(Thread.ofVirtual());

        for (int i = 0; i < 3; i++) {
            var timeTaken = CommonUtils.timer(() -> ThreadExecutor(Thread.ofPlatform()));
            LOGGER.info("platformThread {} ms", timeTaken);
            timeTaken = CommonUtils.timer(() -> ThreadExecutor(Thread.ofVirtual()));
            LOGGER.info("virtualThread {} ms", timeTaken);

        }

    }

    private static void ThreadExecutor(Thread.Builder builder) {
        var latch = new CountDownLatch(TASK_MAX);

        LOGGER.info("Task count= {}", TASK_MAX);

        for (int i = 0; i < TASK_MAX; i++) {
            CommonUtils.timer(builder.start(() -> {
                Task.cpuIntensive(48);
                latch.countDown();
            }));
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
