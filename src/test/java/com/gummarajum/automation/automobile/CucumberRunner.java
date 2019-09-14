package com.gummarajum.automation.automobile;

import org.junit.Test;

import static com.constants.SystemProperties.APPIUM_SERVER_URL;
import static com.constants.SystemProperties.MOBILE_PLATFORM;

public class CucumberRunner extends BaseRunner {
    @Test
    public void testCucumber() {
        System.setProperty(APPIUM_SERVER_URL, "http://127.0.0.1:4723/wd/hub");
        System.setProperty(MOBILE_PLATFORM,"android_web");
        cucumber.api.cli.Main.run(cucumberOptions, Thread.currentThread().getContextClassLoader());
    }


}
