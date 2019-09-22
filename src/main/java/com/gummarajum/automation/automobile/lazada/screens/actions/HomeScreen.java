package com.gummarajum.automation.automobile.lazada.screens.actions;

import com.google.common.base.Strings;
import com.gummarajum.automation.automobile.lazada.screens.locators.HomeScreenObjects;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.svc.ThreadSvc;
import com.gummarajum.automation.automobile.utils.AdbUtils;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import static com.constants.Properties.AWS_EXECUTION;

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
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.searchView, 30);
    }


    public void isScanVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.scanIcon);
    }

    public void isWalletVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.walletIcon);
    }

    public void isSearchViewVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.searchView);
    }

    public void isFeedVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.feedIcon);
    }

    public void isMessagesVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.messagesIcon);
    }

    public void isCartVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.cartIcon);
    }

    public void isAccountVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.accountIcon);
    }

    public void isEasyLoginTipsVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.easyLoginText);
    }

    public void isLoginNowVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.loginNowLink);
    }

    public void isSearchInputVisible() {
        mobileTaskSvc.verifyElementIsVisible(homeScreenObjects.searchInput);
    }


    public void searchLazadaForAnItem(final String itemToSearch) {
        mobileTaskSvc.click(homeScreenObjects.searchView);
        mobileTaskSvc.sendKeys(homeScreenObjects.searchInput, itemToSearch);
        mobileTaskSvc.click(homeScreenObjects.goSearch);
    }

}
