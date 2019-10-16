package com.gummarajum.automation.automobile.screens.joplin.locators;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.springframework.stereotype.Component;

@Component
public class NoteBookLocators {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[1]/android.view.ViewGroup")
    public MobileElement menu;
    
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]/android.view.ViewGroup")
    public MobileElement search;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[3]/android.view.ViewGroup")
    public MobileElement sortNotesIcon;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    public MobileElement add;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[2]/android.view.ViewGroup[4]/android.view.ViewGroup/android.view.ViewGroup")
    public MobileElement cancel;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'New note')]/../../android.view.ViewGroup[2]")
    public MobileElement newNote;

    @AndroidFindBy(xpath = "//android.widget.TextView[contains(@text,'New to-do')]/../../android.view.ViewGroup[2]")
    public MobileElement newTodo;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='New Notebook']")
    public MobileElement newNoteBook;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Tags']")
    public MobileElement tags;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Configuration']")
    public MobileElement configuration;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='All notes']")
    public MobileElement allNotes;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter notebook title']")
    public MobileElement enterNoteBookTitle;

    public String editNoteBookTitleLocator = "//android.widget.EditText[@text='%s']";

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit notebook']/../android.view.ViewGroup[2]")
    public MobileElement saveNoteBook;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='There are currently no notes. Create one by clicking on the (+) button.']")
    public MobileElement newNoteBookDefaultMessage;


    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Edit notebook']/../android.view.ViewGroup[1]")
    public MobileElement goBackFromEditNoteBook;

    public String notebookLocator = "//android.widget.ScrollView[@index='0']//android.widget.TextView[contains(@text,'%s')]";

    @AndroidFindBy(id = "android:id/message")
    public MobileElement notificationText;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='RENAME']")
    public MobileElement notificationRename;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='DELETE']")
    public MobileElement notificationDelete;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='CANCEL']")
    public MobileElement notificationCancel;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='OK']")
    public MobileElement notificationOk;



}
