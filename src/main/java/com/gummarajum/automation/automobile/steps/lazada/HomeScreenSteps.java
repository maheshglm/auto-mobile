package com.gummarajum.automation.automobile.steps.lazada;

import com.gummarajum.automation.automobile.lazada.screens.actions.HomeScreen;
import com.gummarajum.automation.automobile.lazada.screens.locators.HomeScreenObjects;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.utils.AdbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class HomeScreenSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomeScreenSteps.class);

    @Lazy
    @Autowired
    private HomeScreen homeScreen;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private MobileTaskSvc mobileTaskSvc;


    public void launchApp() {
        homeScreen.waitTillLazadaAppIsOpened();
    }

    public void verifyScanIsVisible() {
        homeScreen.isScanVisible();
    }

    public void verifyWalletIsVisible() {
        homeScreen.isWalletVisible();
    }

    public void verifySearchViewIsVisible() {
        homeScreen.isSearchViewVisible();
    }

    public void verifyLoginNowIsVisible() {
        homeScreen.isLoginNowVisible();
    }

    public void verifySmsFaceBookGoogleIsVisible() {
        homeScreen.isEasyLoginTipsVisible();
    }

    public void verifyFeedIsVisible() {
        homeScreen.isFeedVisible();
    }

    public void verifyMessagesIsVisible() {
        homeScreen.isMessagesVisible();
    }

    public void verifyCartIsVisible() {
        homeScreen.isCartVisible();
    }

    public void verifyAccountIsVisible() {
        homeScreen.isAccountVisible();
    }

    public void verifySearchInputIsVisible() {
        homeScreen.isSearchInputVisible();
    }

    public void searchForAnItem(String itemToSearch) {
        homeScreen.searchLazadaForAnItem(itemToSearch);
    }


}
