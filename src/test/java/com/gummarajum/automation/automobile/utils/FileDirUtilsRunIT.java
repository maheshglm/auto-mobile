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

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class FileDirUtilsRunIT {

    @Autowired
    private FileDirUtils fileDirUtils;

    @BeforeClass
    public static void beforeClass() {
        TestConfig.configureLogging(AppiumServerUtilsRunIT.class);
    }

    @Test
    public void testGetFileFromResources_testResources() {
        File file = fileDirUtils.getFileFromResources("capabilities/android.json");
        Assert.assertTrue(file.getAbsolutePath().contains("target/test-classes/capabilities/android.json"));
    }

    @Test
    public void testGetFileFromResources_mainResources() {
        File file = fileDirUtils.getFileFromResources("log4j.xml");
        Assert.assertTrue(file.getAbsolutePath().contains("target/classes/log4j.xml"));
    }
}
