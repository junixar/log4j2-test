package org.teamlogging.log4j2test;

import org.apache.logging.log4j.*;

import java.util.Date;

public class LoggingThread implements Runnable {

    private static final Marker BUSINESS_MARKER = MarkerManager.getMarker("BUSINESS_LOGIC");

    private static final int SAMPLES_COUNT = 15;

    private static final Logger logger = LogManager.getLogger(LoggingThread.class);

    private int iterations;

    private String threadName;

    private String loginId;

    public LoggingThread(int iterations, String threadName, String loginId) {
        this.iterations = iterations;
        this.threadName = threadName;
        this.loginId = loginId;
    }

    public void run() {
        Thread.currentThread().setName(threadName);
        System.out.println("-------------" + (System.currentTimeMillis()) + "--------------");

        ThreadContext.put("loginId", loginId);

        for (int sample = 0; sample < SAMPLES_COUNT; sample++) {
            logInfos();
            logger.info(sample + "_###########################################################");
            System.out.println(new Date().toString() + "_" + + sample + "_##############################################################");
        }


        ThreadContext.clearAll();
        System.out.println("-------------" + (System.currentTimeMillis()) + "--------------");
    }

    private void logInfos() {
        for (int i = 0; i < iterations; i++) {
            logger.info(BUSINESS_MARKER, "Test logging info");
        }
    }


}
