package com.gummarajum.automation.automobile.screens.joplin.locators;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.springframework.stereotype.Component;

import io.appium.java_client.pagefactory.iOSFindBy;

@Component
public class NoteBookConfigurationLocators {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Language']/../android.view.ViewGroup/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Language\"]/../XCUIElementTypeOther")
    public MobileElement language;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Date format']/../android.view.ViewGroup/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Date format\"]/../XCUIElementTypeOther")
    public MobileElement dateFormat;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Time format']/../android.view.ViewGroup/android.widget.TextView[1]")
    @iOSFindBy(xpath = "//XCUIElementTypeStaticText[@name=\"Time format\"]/../XCUIElementTypeOther")
    public MobileElement timeFormat;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Max concurrent connections']/../android.widget.TextView[2]")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,\"Max concurrent connections\")]/XCUIElementTypeOther")
    public MobileElement maxConcurrentConnections_currentValue;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Max concurrent connections']/../android.widget.SeekBar")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[starts-with(@name,\"Max concurrent connections\")]//XCUIElementTypeSlider")
    public MobileElement maxConcurrentConnections_setValue;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Save geo-location with notes']/../android.widget.Switch")
    @iOSFindBy(xpath = "(//XCUIElementTypeOther[@name=\"Save geo-location with notes\"])[2]/XCUIElementTypeSwitch")
    public MobileElement saveGeoLocationWithNotes;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Enable soft breaks']/../android.widget.Switch")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"Enable soft breaks\"]/XCUIElementTypeSwitch")
    public MobileElement enableSoftBreaks;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='SYNC STATUS']")
    @iOSFindBy(xpath = "//XCUIElementTypeButton[@name=\"Sync Status\"]")
    public MobileElement syncStatus;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='\uF27D']")
    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"\uF27D\"]")
    public MobileElement backFromSyncStatus;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Sync status (synced items / total items)']")
    public MobileElement syncStatusLabel;


}
