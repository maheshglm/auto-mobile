package com.gummarajum.automation.automobile.utils;

import com.google.common.base.Strings;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import static com.constants.SystemProperties.APPIUM_LOG_LEVEL;
import static com.constants.SystemProperties.APPIUM_SERVER_URL;

@Component
public class AppiumServerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumServerUtils.class);


    private AppiumDriverLocalService service;

    public AppiumDriverLocalService getAppiumDriverService() {
        return service;
    }

    public boolean isAppiumUrlProvided() {
        return !Strings.isNullOrEmpty(System.getProperty(APPIUM_SERVER_URL));
    }

    public URL getAppiumServerUrl() {
        URL url = null;
        try {
            if (isAppiumUrlProvided()) {
                url = new URL(System.getProperty(APPIUM_SERVER_URL));
                LOGGER.debug("Appium Url Reading from System property [{}] => [{}]", APPIUM_SERVER_URL, url);
            } else {
                url = getAppiumDriverService().getUrl();
                LOGGER.debug("Appium is started locally on [{}]", url);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    public boolean startServer() {

        if (isAppiumUrlProvided()) {
            return true;
        }

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, getAppiumLogLevel());

        service = AppiumDriverLocalService.buildService(builder);
        service.start();

        if (service.isRunning()) {
            LOGGER.debug("Appium server started on [{}]", service.getUrl().toString());
            return true;
        }
        return false;
    }


    public boolean stopServer() {
        if (isAppiumUrlProvided()) {
            return true;
        }
        try {
            LOGGER.debug("Stopping Appium server");
            if (service != null) {
                service.stop();
                return !service.isRunning();
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    private String getAppiumLogLevel() {
        String logLevel = System.getProperty(APPIUM_LOG_LEVEL);
        return Strings.isNullOrEmpty(logLevel) ? "error" : logLevel;
    }

}
