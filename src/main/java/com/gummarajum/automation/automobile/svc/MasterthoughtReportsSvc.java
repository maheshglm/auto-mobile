package com.gummarajum.automation.automobile.svc;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.constants.Properties.MOBILE_PLATFORM;

@Service
public class MasterthoughtReportsSvc {

    private static final Logger LOGGER = LoggerFactory.getLogger(MasterthoughtReportsSvc.class);

    private String targetDir;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private StatePropertiesSvc statePropertiesSvc;

    private void setDefaultProperties() {
        Map<String, String> reportProperties = statePropertiesSvc.getGlobalPropsMapFromPrefix("cucumber.reports", false);
        if (reportProperties.isEmpty()) {
            LOGGER.debug("setting default cucumber.reports properties");
            stateSvc.setStringVar("cucumber.reports.projectName", "AUTOMATION");
            stateSvc.setStringVar("cucumber.reports.buildnumber", "Default");
            stateSvc.setStringVar("cucumber.reports.runwithjenkins", "false");
            stateSvc.setStringVar("cucumber.reports.paralleltesting", "false");
            stateSvc.setStringVar("cucumber.reports.platform", "MacOSX");
            stateSvc.setStringVar("cucumber.reports.release", "1.0");
        }
    }

    public void setTargetDir(String targetDir) {
        this.targetDir = targetDir;
    }

    private String getTargetDir() {
        return targetDir;
    }

    private List<String> getAllJsonFilesUnderTarget(String folderLocation) {
        List<String> jsonFiles = new ArrayList<>();
        File directory = new File(folderLocation);
        File[] files = directory.listFiles((file, name) -> name.endsWith(".json"));
        if (files != null && files.length > 0) {
            for (File f : files) {
                jsonFiles.add(folderLocation + "/" + f.getName());
            }
        }
        return jsonFiles;
    }

    private void generateReports() {
        File reportOutputDirectory = new File(getTargetDir());
        List<String> jsonFiles = this.getAllJsonFilesUnderTarget(getTargetDir());

        this.setDefaultProperties();

        String projectName = stateSvc.getStringVar("cucumber.reports.projectName");
        String buildNumber = stateSvc.getStringVar("cucumber.reports.buildnumber");

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        configuration.setBuildNumber(buildNumber);
        configuration.addClassifications("Platform", stateSvc.getStringVar(MOBILE_PLATFORM));
        configuration.addClassifications("DeviceName", stateSvc.getStringVar("deviceName"));
        configuration.addClassifications("DeviceId", stateSvc.getStringVar("udid"));
        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        reportBuilder.generateReports();
    }

    public void generateReports(String targetDir) {
        setTargetDir(targetDir);
        generateReports();
    }


}
