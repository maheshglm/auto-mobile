package com.gummarajum.automation.automobile.lazada.screens.actions;

import com.google.common.base.Strings;
import com.gummarajum.automation.automobile.lazada.screens.locators.HomeScreenObjects;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.svc.ThreadSvc;
import com.gummarajum.automation.automobile.utils.AdbUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.constants.SystemProperties.AWS_EXECUTION;

@Lazy
@Component
public class HomeScreen {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeScreen.class);

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private HomeScreenObjects homeScreenObjects;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private ThreadSvc threadSvc;

    @PostConstruct
    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileTaskSvc.getDriver()), homeScreenObjects);
    }

    //com.sec.android.app.launcher/com.sec.android.app.launcher.activities.LauncherActivity
    //com.lazada.android/com.lazada.activities.EnterActivity
    public void waitTillLazadaAppIsOpened() {
        String foregroundActivity = adbUtils.getForegroundActivity();
        if (!foregroundActivity.contains("com.lazada.android/com.lazada.android.maintab.MainTabActivity")) {
            LOGGER.warn("Lazada app is not opened Yet, Launching App");
            mobileTaskSvc.launchApp();
        }
        if (!Strings.isNullOrEmpty(System.getProperty(AWS_EXECUTION))) {
            mobileTaskSvc.clickIfVisible(homeScreenObjects.countrySelection, 10);
            mobileTaskSvc.clickIfVisible(homeScreenObjects.skipBtn, 10);
        }
        mobileTaskSvc.isElementVisible(homeScreenObjects.searchView, 30);
    }


    public void isScanVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.scanIcon);
    }

    public void isWalletVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.walletIcon);
    }

    public void isSearchViewVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.searchView);
    }

    public void isFeedVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.feedIcon);
    }

    public void isMessagesVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.messagesIcon);
    }

    public void isCartVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.cartIcon);
    }

    public void isAccountVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.accountIcon);
    }

    public void isEasyLoginTipsVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.easyLoginText);
    }

    public void isLoginNowVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.loginNowLink);
    }

    public void isSearchInputVisible() {
        mobileTaskSvc.isElementVisible(homeScreenObjects.searchInput);
    }


    public void searchLazadaForAnItem(final String itemToSearch) {
        mobileTaskSvc.click(homeScreenObjects.searchView);
        mobileTaskSvc.sendKeys(homeScreenObjects.searchInput, itemToSearch);
        mobileTaskSvc.click(homeScreenObjects.goSearch);
    }

}
