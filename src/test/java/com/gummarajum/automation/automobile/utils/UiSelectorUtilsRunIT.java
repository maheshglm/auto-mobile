package com.gummarajum.automation.automobile.utils;

import com.cfg.Config;
import com.constants.UISELECTORS;
import com.gummarajum.automation.automobile.TestConfig;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedHashMap;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class UiSelectorUtilsRunIT {


    @Autowired
    private UiSelectorUtils uiSelectorUtils;

    @BeforeClass
    public static void beforeClass() {
        TestConfig.configureLogging(AppiumServerUtilsRunIT.class);
    }

    @Test
    public void testSelector() {
        String locator = uiSelectorUtils.constructUiSelector(UISELECTORS.RESOURCE_ID, "com.android:id/test").
                constructUiSelector(UISELECTORS.ENABLED, "true").
                constructUiSelector(UISELECTORS.INDEX, "1").
                getLocator();
        Assert.assertEquals("new UiSelector().resourceId(\"com.android:id/test\").enabled(true).index(1)", locator);
    }

    @Test
    public void testSelectorWithMap(){
        LinkedHashMap<UISELECTORS, String> map = new LinkedHashMap<>();
        map.put(UISELECTORS.RESOURCE_ID,"com.android:id/test");
        map.put(UISELECTORS.ENABLED,"true");
        map.put(UISELECTORS.INDEX,"1");

        String locator = uiSelectorUtils.constructUiSelector(map).getLocator();
        Assert.assertEquals("new UiSelector().resourceId(\"com.android:id/test\").enabled(true).index(1)", locator);

    }
}
