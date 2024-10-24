package com.enalto.section02;

import com.enalto.util.CommonUtils;

import java.time.Duration;

public class StackTraceDemo {

    public static void main(String[] args) {

       stackTrace(builder(ThreadType.PLATFORM));
        //stackTrace(builder(ThreadType.VIRTUAL));
        CommonUtils.threadSleep(Duration.ofMillis(2000));

    }

    private static Thread.Builder builder(ThreadType type) {
        return switch (type) {
            case VIRTUAL -> Thread.ofVirtual().name("virtual ", 1);
            case PLATFORM -> Thread.ofPlatform();
        };
    }

    private static void stackTrace(Thread.Builder builder) {

        for (int i = 0; i < 20; i++) {
            int j = i;
            builder.start(() -> {
                Task.run(j);
            });
        }
    }

    enum ThreadType {
        VIRTUAL,
        PLATFORM
    }
}
