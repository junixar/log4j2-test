package org.teamlogging.log4j2test;

import org.apache.logging.log4j.*;

import java.util.List;

public class LoggingTest {

    private static final Logger logger = LogManager.getLogger(LoggingTest.class);

    private static int throughputLogsPerSec = -1;

    private static float errorsPercentage = 1f;

    private static float warningsPercentage = 5f;

    private static int logsCount = 100_000;

    private static int threadsCount = 1;

    public static void main(String[] args) {
        parseArgs(args);

        List<Logger> loggers = Util.generateLoggers();
        List<String> loginIds = Util.generateLoginIds();
        List<Marker> markers = Util.generateMarkers();

        logger.info("Start log generating in {} threads, {} log messages per thread", threadsCount, logsCount);

        for (int threadIndex = 0; threadIndex < threadsCount; threadIndex++) {
            LogGenerator logGenerator = new LogGenerator(throughputLogsPerSec, errorsPercentage, warningsPercentage, logsCount, loggers, loginIds, markers);
            new Thread(logGenerator::generate, "Executor-" + threadIndex).start();
        }

        logger.info("Log generating finished. {} log messages has been generated.", threadsCount * logsCount);
    }

    private static void parseArgs(String[] args) {
        try {
            parseThroughput(args);
            parseErrorsPercentage(args);
            parseWarningsPercentage(args);
            parseLogsCount(args);
            parseThreadsCount(args);
        } catch (Exception ex) {
            writeHelp();
            ex.printStackTrace();
            System.exit(-1);
        }
    }

    private static void parseThroughput(String[] args) {
        int argIndex = findArgIndex("-t", args);
        if (argIndex > -1) {
            throughputLogsPerSec = Integer.parseInt(args[argIndex + 1]);
        }
    }

    private static void parseErrorsPercentage(String[] args) {
        int argIndex = findArgIndex("-e", args);
        if (argIndex > -1) {
            errorsPercentage = Float.parseFloat(args[argIndex + 1]);
        }
    }

    private static void parseWarningsPercentage(String[] args) {
        int argIndex = findArgIndex("-w", args);
        if (argIndex > -1) {
            warningsPercentage = Float.parseFloat(args[argIndex + 1]);
        }
    }

    private static void parseLogsCount(String[] args) {
        int argIndex = findArgIndex("-c", args);
        if (argIndex > -1) {
            logsCount = Integer.parseInt(args[argIndex + 1]);
        }
    }

    private static void parseThreadsCount(String[] args) {
        int argIndex = findArgIndex("-p", args);
        if (argIndex > -1) {
            threadsCount = Integer.parseInt(args[argIndex + 1]);
        }
    }

    private static int findArgIndex(String argName, String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equalsIgnoreCase(argName)) {
                return i;
            }
        }
        return -1;
    }

    private static void writeHelp() {
        System.out.println("usage: java -jar loggenerator.jar [-t <throughputMsgPerSec>] [-e <errorsPercentage>] " +
                "[-w <warningsPercentage>] [-c <logsCountPerThread>] [-p <threadsCount>]");
    }

}
