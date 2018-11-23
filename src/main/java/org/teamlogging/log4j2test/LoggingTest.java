package org.teamlogging.log4j2test;

import org.apache.logging.log4j.*;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class LoggingTest {

    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    private static final int THREADS_COUNT = 8;

    private static final int ITERATIONS_COUNT = 100_000;

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 8, 0L, TimeUnit.MINUTES, new SynchronousQueue());

        for (int i = 0; i < THREADS_COUNT; i++) {
            LoggingThread loggingThread = new LoggingThread(ITERATIONS_COUNT, "ThreadName_" + i, "login_" + i);

            executor.execute(loggingThread);
        }
    }

    private static final void generateErrorLevel0() {
        generateErrorLevel1();
    }

    private static final void generateErrorLevel1() {
        generateErrorLevel2();
    }

    private static final void generateErrorLevel2() {
        generateErrorLevel3();
    }

    private static final void generateErrorLevel3() {
        generateErrorLevel4();
    }

    private static final void generateErrorLevel4() {
        generateErrorLevel5();
    }

    private static final void generateErrorLevel5() {
        generateErrorLevel6();
    }

    private static final void generateErrorLevel6() {
        generateErrorLevel7();
    }

    private static final void generateErrorLevel7() {
        generateErrorLevel8();
    }

    private static final void generateErrorLevel8() {
        generateErrorLevel9();
    }

    private static final void generateErrorLevel9() {
        logger.error("Test logging error");
    }

}
