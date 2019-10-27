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


    //New Note Locators

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Add title']")
    public MobileElement addNoteTitle;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Add body']")
    public MobileElement addNoteBody;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='\uF1C9']")
    public MobileElement noteMenu;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Tags']")
    public MobileElement tagNote;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Convert to todo']")
    public MobileElement convertNoteToTodo;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete']")
    public MobileElement deleteNote;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='▼']")
    public MobileElement moveNoteIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Delete note?']")
    public MobileElement deleteNoteMessage;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='OK']")
    public MobileElement deleteNoteOk;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Set alarm']")
    public MobileElement setAlarm;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Convert to note']")
    public MobileElement convertToNote;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Select date']/../../android.widget.ImageView")
    public MobileElement datePickerImage;

    @AndroidFindBy(id = "android:id/date_picker_header_year")
    public MobileElement YearPickerIcon;

    public String yearPicker = "//android.widget.TextView[contains(@resource-id,'text1')][@text='%s']";
    public String datePicker = "//android.view.View[@text='%s']";

    

    //Dynamic Locators
    public String aNotebookLocator = "//android.widget.ScrollView[@index='0']//android.widget.TextView[contains(@text,'%s')]";
    public String aEditNoteBookTitleLocator = "//android.widget.EditText[@text='%s']";

    public String aCreateNoteBackBtnLocator = "//android.widget.TextView[@text='%s'][@index='0']/../../android.view.ViewGroup[1]";
    public String aNoteLocator = "//android.widget.TextView[@text='%s']";

    public String aEditNoteTitleLocator = "//android.widget.EditText[@text='%s']";
    public String aEditNoteBodyLocator = "//android.view.View[@text='%s']";

    public String aMoveNoteNotebookLocator = "//android.widget.TextView[contains(@text,'%s')]";

    public String iNotebookLocator = "(//XCUIElementTypeOther[@name=\"%s\"])[1]";
    public String iEditNoteBookTitleLocator = "//XCUIElementTypeTextField[@value='%s']";


}
