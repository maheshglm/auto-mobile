package com.gummarajum.automation.automobile;

import org.junit.Test;

import static com.constants.Properties.APPIUM_SERVER_URL;
import static com.constants.Properties.CAPABILITIES_IDENTIFIER;
import static com.constants.Properties.DEVICE_ID;

public class CucumberRunner extends BaseRunner {
    @Test
    public void testCucumber() {
        //System.setProperty(APPIUM_SERVER_URL, "http://127.0.0.1:4723/wd/hub");
        //System.setProperty(CAPABILITIES_IDENTIFIER, "android_joplin");
        //System.setProperty(DEVICE_ID, "192.168.56.103:5555");
        //System.setProperty(DEVICE_ID, "8D310B1A-6081-4DD9-9FA6-94C7A2AB99B0");
        //System.setProperty(CAPABILITIES_IDENTIFIER,"ios_joplin");
        cucumber.api.cli.Main.run(cucumberOptions, Thread.currentThread().getContextClassLoader());
    }


}
