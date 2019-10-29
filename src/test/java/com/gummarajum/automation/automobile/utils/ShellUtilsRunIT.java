package com.gummarajum.automation.automobile.utils;


import com.cfg.Config;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class ShellUtilsRunIT {

    @Autowired
    private ShellUtils shellUtils;

    @Test
    public void executeShellCommand() {
        String count = shellUtils.executeShellCommand("echo \"hello world\"|wc -c");
        Assert.assertEquals("12", count.trim());
    }
}
