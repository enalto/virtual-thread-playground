package com.enalto.util;

import java.time.Duration;

public class CommonUtils {

    public static void threadSleep(Duration duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
