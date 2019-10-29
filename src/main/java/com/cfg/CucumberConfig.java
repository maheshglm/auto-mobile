package com.cfg;

import com.google.common.base.Strings;

import static com.constants.Properties.FEATURES_PATH;
import static com.constants.Properties.REPORTS_PATH;

public class CucumberConfig {

    public static String features = "classpath:tests/features";
    public static String reports = "./testout/reports";

    public static String[] getCucumberOptions() {

        String featuresProperty = System.getProperty(FEATURES_PATH);
        String reportsPathProperty = System.getProperty(REPORTS_PATH);

        if (!Strings.isNullOrEmpty(featuresProperty)) {
            features = featuresProperty;
        }

        if (!Strings.isNullOrEmpty(reportsPathProperty)) {
            reports = reportsPathProperty;
        }

        return new String[]{
                "--strict",
                "--monochrome",
                "--glue",
                "glue",
                "--tags",
                "@test",
                //"--dry-run",
                "--plugin",
                "pretty",
                "--plugin",
                "json:" + reports + "/report.json",
                "--plugin",
                "html:" + reports,
                features
        };
    }
}
