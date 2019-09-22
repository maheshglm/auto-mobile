package com.gummarajum.automation.automobile;

import org.junit.Test;

import static com.constants.Properties.APPIUM_SERVER_URL;
import static com.constants.Properties.CAPABILITIES_IDENTIFIER;

public class CucumberRunner extends BaseRunner {
    @Test
    public void testCucumber() {
        System.setProperty(APPIUM_SERVER_URL, "http://127.0.0.1:4723/wd/hub");
        System.setProperty(CAPABILITIES_IDENTIFIER,"android_web");
        cucumber.api.cli.Main.run(cucumberOptions, Thread.currentThread().getContextClassLoader());
    }


}
