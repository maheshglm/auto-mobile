package com.gummarajum.automation.automobile.utils;

import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.steps.joplin.JoplinSteps;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StringVerifyUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(StringVerifyUtils.class);

    public static final String ACTUAL_DOES_NOT_MATCH_WITH_EXPECTED = "Actual [{}] does not match with expected [{}]";

    public void assertEquals(final String expected, final String actual) {
        try {
            Assert.assertEquals(expected, actual);
        } catch (java.lang.AssertionError e) {
            LOGGER.error(ACTUAL_DOES_NOT_MATCH_WITH_EXPECTED, actual, expected);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, ACTUAL_DOES_NOT_MATCH_WITH_EXPECTED, actual, expected);
        }
    }
}
