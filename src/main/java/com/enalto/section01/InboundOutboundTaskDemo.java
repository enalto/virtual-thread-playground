package com.enalto.section01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;
import java.util.concurrent.CountDownLatch;

public class InboundOutboundTaskDemo {

    private static final int MAX_PLATFORM_THREAD = 10_000;
    private static final int MAX_VIRTUAL_THREAD = 10_000;
    private static final Logger LOGGER = LoggerFactory.getLogger(InboundOutboundTaskDemo.class);


    public static void main(String[] args) throws InterruptedException {
        Instant start = Instant.now();
        LOGGER.info("Starting platform thread {}", Instant.now().toString());

        //platformThreadDemo3();
        virtualThreadDemo();

        Instant end = Instant.now();
        LOGGER.info("Ending platform thread {}", Instant.now().toString());
        LOGGER.info("Duration {}", end.toEpochMilli() - start.toEpochMilli());

    }

    private static void platformThreadDemo1() {

        for (int i = 0; i < MAX_PLATFORM_THREAD; i++) {
            int j = i;
            Thread thread = new Thread(() -> Task.ioIntensive(j));
            thread.start();
        }

    }


    private static void platformThreadDemo2() {

        var builder = Thread.ofPlatform().name("platform-thread ", 1);

        for (int i = 0; i < MAX_PLATFORM_THREAD; i++) {
            int j = i;
            Thread thread = builder
                    .unstarted(() -> Task.ioIntensive(j));

            thread.start();
        }
    }

    private static void platformThreadDemo3() throws InterruptedException {

        var builder = Thread.ofPlatform().daemon().name("my-thread", 1);

        CountDownLatch countDownLatch = new CountDownLatch(MAX_PLATFORM_THREAD);

        for (int i = 0; i < MAX_PLATFORM_THREAD; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(j);
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
    }


    private static void virtualThreadDemo() throws InterruptedException {

        var builder = Thread.ofVirtual().name("virtual-thread ", 1);

        CountDownLatch countDownLatch = new CountDownLatch(MAX_VIRTUAL_THREAD);

        for (int i = 0; i < MAX_VIRTUAL_THREAD; i++) {
            int j = i;
            Thread thread = builder.unstarted(() -> {
                Task.ioIntensive(j);
                countDownLatch.countDown();
            });
            thread.start();
        }
        countDownLatch.await();
    }

}
