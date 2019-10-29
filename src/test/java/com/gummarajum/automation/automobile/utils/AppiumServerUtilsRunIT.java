package com.gummarajum.automation.automobile.utils;

import com.cfg.Config;
import com.gummarajum.automation.automobile.TestConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class AppiumServerUtilsRunIT {

    @Autowired
    private AppiumServerUtils appiumServerUtils;

    @BeforeClass
    public static void beforeClass() {
        TestConfig.configureLogging(AppiumServerUtilsRunIT.class);
    }

    //@Ignore
    @Test
    public void testAppiumServer() {
        try {
            System.setProperty("appium.log.level", "debug");
            Assert.assertTrue(appiumServerUtils.startServer());
        } finally {
            Assert.assertTrue(appiumServerUtils.stopServer());
        }
    }
}
