package com.gummarajum.automation.automobile.utils;

import com.cfg.Config;
import com.gummarajum.automation.automobile.MobileException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static com.gummarajum.automation.automobile.utils.StringVerifyUtils.ACTUAL_DOES_NOT_MATCH_WITH_EXPECTED;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class StringVerifyUtilsRunIT {

    @Autowired
    private StringVerifyUtils stringVerifyUtils;


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testAssertEquals_stringsMatch() {
        stringVerifyUtils.assertEquals("text1", "text1");
    }

    @Test
    public void testAssertEquals_stringsNotMatch() {
        thrown.expect(MobileException.class);
        thrown.expectMessage("Actual [text2] does not match with expected [text1]");
        stringVerifyUtils.assertEquals("text1", "text2");
    }
}
