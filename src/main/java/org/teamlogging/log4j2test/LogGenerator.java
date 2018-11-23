package org.teamlogging.log4j2test;

import org.apache.logging.log4j.*;

import java.util.List;
import java.util.Random;

public class LogGenerator {

    private static final Logger logger = LogManager.getLogger(LogGenerator.class);

    private static final Marker BUSINESS_MARKER = MarkerManager.getMarker("BUSINESS_LOGIC");

    private int throughputLogsPerSec = -1;

    private float errorsPercentage = 1f;

    private float warningsPercentage = 5f;

    private int logsCount = 100_000;

    private String loginId = "defaultLoginId";

    private List<Logger> loggers;

    private List<String> loginIds;

    private List<Marker> markers;

    public LogGenerator(List<Logger> loggers, List<String> loginIds, List<Marker> markers) {
        this.loggers = loggers;
        this.loginIds = loginIds;
        this.markers = markers;
    }

    public void generate() {
        Random randomForPercent = new Random();
        Random randomForLogger = new Random();
        Random randomForLoginId = new Random();
        Random randomForMarker = new Random();
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

    public void setThroughputLogsPerSec(int throughputLogsPerSec) {
        this.throughputLogsPerSec = throughputLogsPerSec;
    }

    public void setErrorsPercentage(float errorsPercentage) {
        this.errorsPercentage = errorsPercentage;
    }

    public void setWarningsPercentage(float warningsPercentage) {
        this.warningsPercentage = warningsPercentage;
    }

    public void setLogsCount(int logsCount) {
        this.logsCount = logsCount;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }
}
