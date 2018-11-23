package org.teamlogging.log4j2test;

import org.apache.logging.log4j.*;

import java.util.List;

public class LoggingTest {

    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    private static final int THREADS_COUNT = 4;

    private static final int ITERATIONS_COUNT = 100_000;

    public static void main(String[] args) {

        List<Logger> loggers = Util.generateLoggers();
        List<String> loginIds = Util.generateLoginIds();
        List<Marker> markers = Util.generateMarkers();

        LogGenerator logGenerator = new LogGenerator(loggers, loginIds, markers);

        logGenerator.setLogsCount(100);

        logGenerator.generate();

//        ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 4, 0L, TimeUnit.MINUTES, new SynchronousQueue());
//
//        for (int i = 0; i < THREADS_COUNT; i++) {
//            LoggingThread loggingThread = new LoggingThread(ITERATIONS_COUNT, "ThreadName_" + i, "login_" + i);
//
//            executor.execute(loggingThread);
//        }
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
