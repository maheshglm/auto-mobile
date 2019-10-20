package com.gummarajum.automation.automobile.svc;

import com.cfg.Config;
import com.constants.ELEMENT_DIRECTION;
import com.constants.SCREEN_DIRECTION;
import com.gummarajum.automation.automobile.TestConfig;
import com.gummarajum.automation.automobile.screens.joplin.actions.NoteBookActions;
import com.gummarajum.automation.automobile.screens.joplin.actions.ReusableActions;
import com.gummarajum.automation.automobile.screens.joplin.locators.NoteBookLocators;
import com.gummarajum.automation.automobile.utils.AdbUtils;
import com.gummarajum.automation.automobile.utils.AppiumServerUtils;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static com.constants.Properties.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Config.class)
public class MobileDriverSvcRunIT {

    @Autowired
    private AppiumServerUtils appiumServerUtils;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Lazy
    @Autowired
    private NoteBookActions noteBookActions;

    @Autowired
    private ReusableActions reusableActions;

    @Autowired
    private NoteBookLocators noteBookLocators;

    @BeforeClass
    public static void setUp() {
        TestConfig.configureLogging(MobileDriverSvcRunIT.class);
    }

    @Before
    public void setVariables() {
        System.setProperty(APPIUM_LOG_LEVEL, "debug");
        System.setProperty(APPIUM_SERVER_URL, "http://127.0.0.1:4723/wd/hub");
    }


    @Autowired
    private ThreadSvc threadSvc;


    @Test
    public void testJoplin() {
        System.setProperty(CAPABILITIES_IDENTIFIER, "ios_joplin");
        AppiumDriver driver = mobileTaskSvc.getDriver();
        reusableActions.launchJoplinApp();
        noteBookActions.createNewNoteBook("Test1");
        noteBookActions.verifyNoteBookVisible("Test1");
    }


    @Ignore
    @Test
    public void testSafari() {
        System.setProperty(CAPABILITIES_IDENTIFIER, "ios_web");
        mobileTaskSvc.getDriver();
        mobileTaskSvc.launchBrowser("http://www.google.com");
        mobileTaskSvc.sendKeys(mobileTaskSvc.findElement(By.name("Search")), "Hello Appium!!!");

        threadSvc.sleepSeconds(2);

        mobileTaskSvc.quitDriver();
    }


    @Ignore
    @Test
    public void testDragNDrop() {
        try {
            System.setProperty(CAPABILITIES_IDENTIFIER, "android_test");
            mobileTaskSvc.getDriver();
            //mobileTaskSvc.click(By.id("com.mobeta.android.demodslv:id/activity_title"));

            threadSvc.sleepSeconds(1);

            /*mobileTaskSvc.pressAndroidKey(AndroidKey.HOME);

            mobileTaskSvc.swipeScreen(SCREEN_DIRECTION.RIGHT,1000);
            mobileTaskSvc.swipeScreen(SCREEN_DIRECTION.RIGHT,1000);*/

            /*mobileTaskSvc.findElement(By.id("com.samsung.android.contacts:id/menu_search")).click();
            mobileTaskSvc.findElement(By.id("com.samsung.android.contacts:id/search_src_text")).sendKeys("AMyIndia");

            MobileElement element = mobileTaskSvc.findElement(By.xpath("//android.widget.TextView[@content-desc='AMyIndia']"));*/


            MobileElement element = mobileTaskSvc.getElementByReference(By.xpath("//android.widget.TextView[@content-desc='AMyIndia']"));
            while (element == null) {
                mobileTaskSvc.swipeScreen(SCREEN_DIRECTION.DOWN, 1000);
                threadSvc.sleepSeconds(1);
                element = mobileTaskSvc.getElementByReference(By.xpath("//android.widget.TextView[@content-desc='AMyIndia']"));
            }

            mobileTaskSvc.swipeElement(element, ELEMENT_DIRECTION.RIGHT, 1000);

            threadSvc.sleepSeconds(2);


        } finally {
            mobileTaskSvc.quitDriver();
        }
    }


    @Ignore
    @Test
    public void testAndroidWeb() {
        try {
            System.setProperty(CAPABILITIES_IDENTIFIER, "android_web");
            AppiumDriver driver = mobileTaskSvc.getDriver();
            driver.get("https://www.google.com");
            driver.findElement(By.name("q")).sendKeys("Appium Automation");
        } finally {
            mobileTaskSvc.quitDriver();
        }

    }


    @Test
    @Ignore
    public void testE2E() {
        try {
            appiumServerUtils.startServer();

            AppiumDriver driver = mobileTaskSvc.getDriver();

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
            mobileTaskSvc.quitDriver();
            appiumServerUtils.stopServer();
        }

    }
}
