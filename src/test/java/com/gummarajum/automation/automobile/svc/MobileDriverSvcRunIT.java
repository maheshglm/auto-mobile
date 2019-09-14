package com.gummarajum.automation.automobile.svc;

import com.cfg.Config;
import com.gummarajum.automation.automobile.TestConfig;
import com.gummarajum.automation.automobile.lazada.screens.actions.HomeScreen;
import com.gummarajum.automation.automobile.lazada.screens.actions.SearchResultsScreen;
import com.gummarajum.automation.automobile.utils.AdbUtils;
import com.gummarajum.automation.automobile.utils.AppiumServerUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static com.constants.SystemProperties.APPIUM_LOG_LEVEL;
import static com.constants.SystemProperties.APPIUM_SERVER_URL;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class MobileDriverSvcRunIT {

    @Autowired
    private AppiumServerUtils appiumServerUtils;

    @Autowired
    private MobileDriverSvc mobileDriverSvc;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Lazy @Autowired
    private HomeScreen homeScreen;

    @Lazy @Autowired
    private SearchResultsScreen searchResultsScreen;

    @BeforeClass
    public static void setUp() {
        TestConfig.configureLogging(MobileDriverSvcRunIT.class);
    }

    @Before
    public void setVariables() {
        System.setProperty(APPIUM_LOG_LEVEL, "info");
        System.setProperty(APPIUM_SERVER_URL, "http://127.0.0.1:4723/wd/hub");
    }


    @Autowired
    private ThreadSvc threadSvc;

    @Ignore
    @Test
    public void test(){
        try {
            //appiumServerUtils.startServer();
            AppiumDriver driver = mobileTaskSvc.getDriver();


            mobileTaskSvc.tapByCoordinates(800, 2000); //It is

            homeScreen.searchLazadaForAnItem("apple laptop");

        }
        finally {
            mobileTaskSvc.quitDriver();
            //appiumServerUtils.stopServer();
        }
    }


    @Test
    @Ignore
    public void testE2E() {
        try {
            appiumServerUtils.startServer();

            AppiumDriver driver = mobileDriverSvc.driver();

            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);


            threadSvc.sleepSeconds(2);

            MobileElement el1 = (MobileElement) driver.findElementById("com.lazada.android:id/laz_homepage_search_view");
            el1.click();

            threadSvc.sleepSeconds(2);

            MobileElement el2 = (MobileElement) driver.findElementById("com.lazada.android:id/search_input_box");
            el2.sendKeys("laptop");

            threadSvc.sleepSeconds(2);
            MobileElement el3 = (MobileElement) driver.findElementById("com.lazada.android:id/search_button");
            el3.click();


            int debug = 1;


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mobileDriverSvc.quit(mobileTaskSvc.getDriver());
            appiumServerUtils.stopServer();
        }

    }
}
