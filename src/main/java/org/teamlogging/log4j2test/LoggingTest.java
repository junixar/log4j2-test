package org.teamlogging.log4j2test;

import org.apache.logging.log4j.*;

public class LoggingTest {

    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    private static final Marker BUSINESS_MARKER = MarkerManager.getMarker("BUS_LOG");

    public static void main(String[] args) {
        Thread.currentThread().setName("Main Logging Thread");

        ThreadContext.put("loginId", "userName.testName");

        for (int i = 0; i < 100; i++) {
            logInfo();
        }
        generateErrorLevel0();

        ThreadContext.clearAll();
    }

    private static void logInfo() {
        logger.info(BUSINESS_MARKER,"Test logging info");
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
