package org.teamlogging.log4j2test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Util {

    private static final String PACKAGE_PREFIX = "org.application";

    public static final String[] BUSINESS_PREFIXES = {"repository", "batch", "security", "user", "role", "domain", "rules"};

    public static final String[] TECHNICAL_SUFFIXES = {"Service", "Manager", "Provider", "Resolver"};

    public static final String[] LOGIN_ID_PREFIXES = {"super", "main", "simple", "current", "special", "test"};

    public static final String[] LOGIN_ID_SUFFIXES = {"Customer", "User", "Admin", "Supporter", "Watcher"};

    public static List<Logger> generateLoggers() {
        final List<Logger> resultList = new ArrayList<>();
        for (String packageSuffix : BUSINESS_PREFIXES) {
            final String packageName = PACKAGE_PREFIX + "." + packageSuffix;
            for (String businessPrefix : BUSINESS_PREFIXES) {
                for (String technicalSuffix : TECHNICAL_SUFFIXES) {
                    resultList.add(LogManager.getLogger(packageName + "." + businessPrefix.substring(0, 1).toUpperCase() + businessPrefix.substring(1) + technicalSuffix));
                }
            }
        }
        return Collections.unmodifiableList(resultList);
    }

    public static List<String> generateLoginIds() {
        final List<String> resultList = new ArrayList<>();
        for (String prefix : LOGIN_ID_PREFIXES) {
            for (String suffix : LOGIN_ID_SUFFIXES) {
                resultList.add(prefix + suffix);
            }
        }
        return Collections.unmodifiableList(resultList);
    }

    public static List<Marker> generateMarkers() {
        return Collections.unmodifiableList(Arrays.asList(BUSINESS_PREFIXES).stream().map(s -> MarkerManager.getMarker(s.toUpperCase())).collect(Collectors.toList()));
    }

}
