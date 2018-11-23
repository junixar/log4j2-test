package org.teamlogging.log4j2test;

import org.apache.logging.log4j.*;

import java.util.List;
import java.util.Random;

public class LogGenerator {

    private static final Logger logger = LogManager.getLogger(LogGenerator.class);

    private final int throughputLogsPerSec;

    private final float errorsPercentage;

    private final float warningsPercentage;

    private final int logsCount;

    private final List<Logger> loggers;

    private final List<String> loginIds;

    private final List<Marker> markers;

    public LogGenerator(int throughputLogsPerSec, float errorsPercentage, float warningsPercentage, int logsCount,
                        List<Logger> loggers, List<String> loginIds, List<Marker> markers) {
        this.throughputLogsPerSec = throughputLogsPerSec;
        this.errorsPercentage = errorsPercentage;
        this.warningsPercentage = warningsPercentage;
        this.logsCount = logsCount;
        this.loggers = loggers;
        this.loginIds = loginIds;
        this.markers = markers;
    }

    public void generate() {
        final Random randomForPercent = new Random();
        final Random randomForLogger = new Random();
        final Random randomForLoginId = new Random();
        final Random randomForMarker = new Random();
        for (int i = 0; i < logsCount; i++) {
            final float randomPercent = randomForPercent.nextFloat() * 100f;

            final int randomLoggerIndex = randomForLogger.nextInt(loggers.size());
            Logger randomLogger = loggers.get(randomLoggerIndex);

            final int randomLoginIdIndex = randomForLoginId.nextInt(loginIds.size());
            String randomLoginId = loginIds.get(randomLoginIdIndex);

            final int randomMarkerIndex = randomForMarker.nextInt(markers.size());
            Marker randomMarker = markers.get(randomMarkerIndex);

            ThreadContext.put("loginId", randomLoginId);

            if (randomPercent < errorsPercentage) randomLogger.error(randomMarker,
                    "Current business operation failed, parameters are {}, {}, {}",
                    randomLoggerIndex, randomLoginIdIndex, randomMarkerIndex);
            else if (randomPercent < warningsPercentage) randomLogger.warn(randomMarker, "Attention, this option must not be used, value={}", randomLoggerIndex);
            else randomLogger.info(randomMarker, "Current parameters of the call will be substituted");

            if (throughputLogsPerSec > 0) {
                try {
                    Thread.sleep(1000L/(long)throughputLogsPerSec);
                } catch (InterruptedException ex) {
                    logger.error("Error on Thread.sleep", ex);
                }
            }
        }

        ThreadContext.clearAll();
    }

}
