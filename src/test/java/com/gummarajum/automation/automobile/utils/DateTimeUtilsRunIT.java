package com.gummarajum.automation.automobile.utils;

import com.cfg.Config;
import com.google.common.base.Strings;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.TestConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class DateTimeUtilsRunIT {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtilsRunIT.class);


    @Autowired
    private DateTimeUtils dateTimeUtils;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @BeforeClass
    public static void beforeClass() {
        TestConfig.configureLogging(DateTimeUtilsRunIT.class);
    }


    @Test
    public void testCurrentTimeMillis() {
        long millis = dateTimeUtils.currentTimeMillis();
        Assert.assertFalse(Strings.isNullOrEmpty(Long.toString(millis)));
    }

    @Test
    public void testGetTimeStamp() {
        String timeStamp = dateTimeUtils.getTimeStamp();
        Assert.assertFalse(Strings.isNullOrEmpty(timeStamp));
    }

    @Test
    public void testGetTimestamp_givenFormat1() {
        String timeStamp = dateTimeUtils.getTimeStamp("dd-MMM-yyyy");
        LOGGER.debug(timeStamp);
    }

    @Test
    public void testGetTimestamp_givenFormat2() {
        String timeStamp = dateTimeUtils.getTimeStamp("dd-MM-yyyy HH:mm:ss");
        LOGGER.debug(timeStamp);
    }

    @Test
    public void testGetTimestamp_givenFormat_Exception() {
        thrown.expect(MobileException.class);
        thrown.expectMessage("Exception in formatting LocalDateTime with given format [dd-b-yyyy]");
        dateTimeUtils.getTimeStamp("dd-b-yyyy");
    }


}
