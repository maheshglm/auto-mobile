package com.gummarajum.automation.automobile.lazada.screens.locators;


import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;
import org.springframework.stereotype.Component;

@Component
public class HomeScreenObjects {

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Singapore']")
    public MobileElement countrySelection;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/intro_skip_btn")
    public MobileElement skipBtn;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/laz_homepage_search_view")
    public MobileElement searchView;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/laz_homepage_scan_icon")
    public MobileElement scanIcon;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/laz_homepage_right_wallet_icon")
    public MobileElement walletIcon;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Feed']")
    public MobileElement feedIcon;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Messages']")
    public MobileElement messagesIcon;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Cart']")
    public MobileElement cartIcon;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Account']")
    public MobileElement accountIcon;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Easy login with SMS/Facebook/Google!']")
    public MobileElement easyLoginText;

    @CacheLookup
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='LOGIN NOW']")
    public MobileElement loginNowLink;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/search_input_box")
    public MobileElement searchInput;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/search_button")
    public MobileElement goSearch;





}

