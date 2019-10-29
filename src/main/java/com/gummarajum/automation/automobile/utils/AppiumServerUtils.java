package com.gummarajum.automation.automobile.utils;

import com.google.common.base.Strings;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;

import static com.constants.Properties.APPIUM_LOG_LEVEL;
import static com.constants.Properties.APPIUM_SERVER_URL;
import static com.constants.Properties.CHROME_DRIVER_PATH;

@Component
public class AppiumServerUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppiumServerUtils.class);

    @Autowired
    private FileDirUtils fileDirUtils;

    @Autowired
    private ShellUtils shellUtils;

    private AppiumDriverLocalService service;

    public AppiumDriverLocalService getAppiumDriverService() {
        return service;
    }

    public boolean isAppiumUrlProvided() {
        return !Strings.isNullOrEmpty(System.getProperty(APPIUM_SERVER_URL));
    }

    public synchronized URL getAppiumServerUrl() {
        URL url = null;
        try {
            if (isAppiumUrlProvided()) {
                url = new URL(System.getProperty(APPIUM_SERVER_URL));
                LOGGER.debug("Appium Url Reading from System property [{}] => [{}]", APPIUM_SERVER_URL, url);
            } else {
                if (getAppiumDriverService() == null) {
                    this.startServer();
                }
                url = getAppiumDriverService().getUrl();
                LOGGER.debug("Appium is started locally on [{}]", url);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }


    private int getAvailablePort() {
        int port = 4723;
        while (true) {
            String count = shellUtils.executeShellCommand("lsof -i -P -n | grep " + port + " | wc -l").trim();
            if (Integer.valueOf(count) == 0) {
                return port;
            }
            port++;
        }
    }

    public synchronized boolean startServer() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("chromedriverExecutable",
                fileDirUtils.getFileFromResources(CHROME_DRIVER_PATH).getAbsolutePath());

        if (isAppiumUrlProvided()) {
            return true;
        }

        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(getAvailablePort());
        builder.withCapabilities(capabilities);
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
