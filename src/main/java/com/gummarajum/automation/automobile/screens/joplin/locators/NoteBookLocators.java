package com.gummarajum.automation.automobile.screens.joplin.locators;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.springframework.stereotype.Component;

@Component
public class NoteBookLocators {

    @iOSFindBy(id = "\uF32A")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup")
    public MobileElement menu;

    @iOSFindBy(id = "\uF375")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup")
    public MobileElement search;

    @iOSFindBy(id = "\uF2F7")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[3]/android.view.ViewGroup")
    public MobileElement sortNotesIcon;

    @iOSFindBy(id = "\uF2F4 New Notebook")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='New Notebook']")
    public MobileElement newNoteBook;

    @iOSFindBy(id = "\uF35A Tags")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Tags']")
    public MobileElement tags;

    @iOSFindBy(id = "\uF377 Configuration")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Configuration']")
    public MobileElement configuration;

    @iOSFindBy(id = "\uF2E1 All notes")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='All notes']")
    public MobileElement allNotes;

    @iOSFindBy(xpath = "//XCUIElementTypeTextField[@value='Enter notebook title']")
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter notebook title']")
    public MobileElement enterNoteBookTitle;

    @iOSFindBy(xpath = "(//XCUIElementTypeOther[@name=\"\uF27D Edit notebook\"])[3]/XCUIElementTypeOther[2]")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit notebook']/../android.view.ViewGroup[2]")
    public MobileElement saveNoteBook;

    @iOSFindBy(id = "There are currently no notes. Create one by clicking on the (+) button.")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='There are currently no notes. Create one by clicking on the (+) button.']")
    public MobileElement newNoteBookDefaultMessage;

    @iOSFindBy(xpath = "(//XCUIElementTypeOther[@name=\"\uF273\"])[3]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    public MobileElement add;

    @iOSFindBy(xpath = "(//XCUIElementTypeOther[@name=\"\uF273\"])[1]")
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup")
    public MobileElement cancel;

    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"New note\"]/../XCUIElementTypeOther[1]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'New note')]/../../android.view.ViewGroup[2]")
    public MobileElement newNote;

    @iOSFindBy(xpath = "//XCUIElementTypeOther[@name=\"New to-do\"]/../XCUIElementTypeOther[1]")
    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'New to-do')]/../../android.view.ViewGroup[2]")
    public MobileElement newTodo;


    @iOSFindBy(id = "\uF27D")
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit notebook']/../android.view.ViewGroup[1]")
    public MobileElement goBackFromEditNoteBook;

    @iOSFindBy(xpath = "//XCUIElementTypeAlert//XCUIElementTypeStaticText")
    @AndroidFindBy(id = "android:id/message")
    public MobileElement notificationText;

    @iOSFindBy(id = "Rename")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='RENAME']")
    public MobileElement notificationRename;

    @iOSFindBy(id = "Delete")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
    public MobileElement notificationDelete;

    @iOSFindBy(id = "Cancel")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
    public MobileElement notificationCancel;

    @iOSFindBy(id = "OK")
    @AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
    public MobileElement notificationOk;


    //Dynamic Locators
    public String androidNotebookLocator = "//android.widget.ScrollView[@index='0']//android.widget.TextView[contains(@text,'%s')]";
    public String androidEditNoteBookTitleLocator = "//android.widget.EditText[@text='%s']";

    public String iosNotebookLocator = "(//XCUIElementTypeOther[@name=\"%s\"])[1]";
    public String iosEditNoteBookTitleLocator = "//XCUIElementTypeTextField[@value='%s']";


}
