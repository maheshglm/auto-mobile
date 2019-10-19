package com.gummarajum.automation.automobile.svc;


import com.constants.PLATFORM;
import com.google.common.base.Strings;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.utils.*;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Map;

import static com.constants.Properties.*;

@Service
public class MobileDriverSvc {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileDriverSvc.class);

    private static final String UDID = "udid";
    private static final String MOBILE_PLATFORM_SHOULD_BE_IOS_OR_ANDROID_BUT_NOT = "Mobile platform should be 'ios' or 'android', but not [{}]";
    private static final String EXCEPTION_WHILE_QUITTING_APPIUM_DRIVER = "MobileException while quitting appium driver";

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private JsonUtils jsonUtils;

    @Autowired
    private AppiumServerUtils appiumServerUtils;

    @Autowired
    private FileDirUtils fileDirUtils;

    @Autowired
    private FormatterUtils formatterUtils;


    private String getCapabilitiesIdentifier() {
        String platform = System.getProperty(CAPABILITIES_IDENTIFIER);
        if (Strings.isNullOrEmpty(platform)) {
            return PLATFORM.ANDROID.toString();
        }
        return platform;
    }

    private Map<String, Object> readDesiredCapabilitiesFromJson() {
        String filename;
        if (Strings.isNullOrEmpty(System.getProperty(CAPABILITIES_PATH))) {
            filename = formatterUtils.format("capabilities/%s.json", getCapabilitiesIdentifier());
        } else {
            filename = formatterUtils.format(System.getProperty(CAPABILITIES_PATH) + "/capabilities/%s.json", getCapabilitiesIdentifier());
        }
        File filepath = fileDirUtils.getFileFromResources(filename);
        LOGGER.debug("Reading Desired capabilities from [{}]", filepath.getAbsolutePath());
        String jsonData = fileDirUtils.readFileToString(filepath.getAbsolutePath());
        return jsonUtils.readJsonStringIntoMap(jsonData);
    }

    private void setCapability(DesiredCapabilities capabilities, String key, String value) {
        LOGGER.debug("Setting Desired Capability [{}] to [{}]", key, value);
        stateSvc.setStringVar(key, value);
        capabilities.setCapability(key, value);
    }

    private DesiredCapabilities getDesiredCapabilities() {

        Map<String, Object> desiredCapabilitiesMap = this.readDesiredCapabilitiesFromJson();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        for (Map.Entry<String, Object> entry : desiredCapabilitiesMap.entrySet()) {
            this.setCapability(capabilities, entry.getKey(), entry.getValue().toString());
        }

        return capabilities;
    }

    synchronized AppiumDriver driver() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();

        if (Strings.isNullOrEmpty(System.getProperty(AWS_EXECUTION))) {
            desiredCapabilities = this.getDesiredCapabilities();
            adbUtils.setDeviceId(stateSvc.getStringVar(UDID));
        } else {
            adbUtils.setDeviceId(System.getProperty(AWS_DEVICE_ID));
        }

        AppiumDriver<MobileElement> appiumDriver;
        if (getCapabilitiesIdentifier().toUpperCase().startsWith(PLATFORM.ANDROID.toString())) {
            stateSvc.setStringVar(MOBILE_PLATFORM, PLATFORM.ANDROID.toString());
            appiumDriver = new AndroidDriver<>(appiumServerUtils.getAppiumServerUrl(), desiredCapabilities);
        } else if (getCapabilitiesIdentifier().toUpperCase().startsWith(PLATFORM.IOS.toString())) {
            stateSvc.setStringVar(MOBILE_PLATFORM, PLATFORM.IOS.toString());
            appiumDriver = new IOSDriver<>(appiumServerUtils.getAppiumServerUrl(), desiredCapabilities);
        } else {
            LOGGER.error(MOBILE_PLATFORM_SHOULD_BE_IOS_OR_ANDROID_BUT_NOT, getCapabilitiesIdentifier());
            throw new MobileException(MobileExceptionType.UNDEFINED, MOBILE_PLATFORM_SHOULD_BE_IOS_OR_ANDROID_BUT_NOT, getCapabilitiesIdentifier());
        }
        LOGGER.debug("Driver initiated!!!");
        return appiumDriver;
    }

    synchronized void quit(AppiumDriver driver) {
        try {
            if (driver != null) {
                driver.quit();
                LOGGER.debug("Driver Stopped!!!");
            }
        } catch (WebDriverException e) {
            LOGGER.error(EXCEPTION_WHILE_QUITTING_APPIUM_DRIVER);
            throw new MobileException(MobileExceptionType.IO_ERROR, EXCEPTION_WHILE_QUITTING_APPIUM_DRIVER);
        }
    }

}
