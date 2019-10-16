package com.gummarajum.automation.automobile.screens.joplin.actions;

import com.constants.SCREEN_DIRECTION;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.screens.joplin.locators.NoteBookLocators;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.svc.ThreadSvc;
import com.gummarajum.automation.automobile.utils.FormatterUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Lazy
@Component
public class NoteBookActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteBookActions.class);

    private static final String NET_COZIC_JOPLIN_MAIN_ACTIVITY = "net.cozic.joplin.MainActivity";

    private static final String CONFIRM_DELETE_TEXT = "Delete notebook \"%s\"? All notes and sub-notebooks within this notebook will also be deleted.";

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private ThreadSvc threadSvc;

    @Autowired
    private NoteBookLocators noteBookLocators;

    @Autowired
    private FormatterUtils formatterUtils;

    @PostConstruct
    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileTaskSvc.getDriver()), noteBookLocators);
    }

    public void launchJoplinApp() {
        if (!mobileTaskSvc.waitTillApplicationIsOpened(NET_COZIC_JOPLIN_MAIN_ACTIVITY, 30)) {
            LOGGER.error("Application with activity id [{}] is not opened!!!", NET_COZIC_JOPLIN_MAIN_ACTIVITY);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Application with activity id [{}] is not opened!!!", NET_COZIC_JOPLIN_MAIN_ACTIVITY);
        } else {
            LOGGER.debug("Application with Activity id [{}] is opened.", NET_COZIC_JOPLIN_MAIN_ACTIVITY);
        }
    }


    public void createNewNoteBook(final String notebookName) {
        mobileTaskSvc.click(noteBookLocators.menu);
        mobileTaskSvc.click(noteBookLocators.newNoteBook);
        mobileTaskSvc.sendKeys(noteBookLocators.enterNoteBookTitle, notebookName);
        mobileTaskSvc.click(noteBookLocators.saveNoteBook);
        mobileTaskSvc.verifyElementIsVisible(noteBookLocators.newNoteBookDefaultMessage, 10);
    }

    public void verifyNoteBookCreated(final String notebookName) {
        mobileTaskSvc.click(noteBookLocators.menu);
        final String notebookLocator = formatterUtils.format(noteBookLocators.notebookLocator, notebookName);
        mobileTaskSvc.verifyElementIsVisible(By.xpath(notebookLocator));
        mobileTaskSvc.swipeScreen(SCREEN_DIRECTION.LEFT, 1000);
    }


    public void verifyNotificationMessageText(final String expectedMessage) {
        String notificationMessage = mobileTaskSvc.getAttribute(noteBookLocators.notificationText, "text");
        if (!notificationMessage.equals(expectedMessage)) {
            LOGGER.error("Notification Message [{}] is not as Expected [{}]", notificationMessage, expectedMessage);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Notification Message [{}] is not as Expected [{}]", notificationMessage, expectedMessage);
        }
    }


    public void renameNoteBook(final String existingName, final String newName) {
        mobileTaskSvc.click(noteBookLocators.menu);
        final String existingLocator = formatterUtils.format(noteBookLocators.notebookLocator, existingName);
        MobileElement element = mobileTaskSvc.findElement(By.xpath(existingLocator));
        mobileTaskSvc.longPress(element, 1000);

        this.verifyNotificationMessageText("Notebook: " + existingName);

        mobileTaskSvc.click(noteBookLocators.notificationRename);

        final String editLocator = formatterUtils.format(noteBookLocators.editNoteBookTitleLocator, existingName);
        MobileElement editTextElement = mobileTaskSvc.findElement(By.xpath(editLocator));

        mobileTaskSvc.sendKeys(editTextElement, newName);
        mobileTaskSvc.click(noteBookLocators.saveNoteBook);
    }


    public void deleteNoteBook(final String notebookName) {
        mobileTaskSvc.click(noteBookLocators.menu);
        final String notebookLocator = formatterUtils.format(noteBookLocators.notebookLocator, notebookName);
        MobileElement element = mobileTaskSvc.findElement(By.xpath(notebookLocator));
        mobileTaskSvc.longPress(element, 1000);

        this.verifyNotificationMessageText("Notebook: " + notebookName);

        mobileTaskSvc.click(noteBookLocators.notificationDelete);

        verifyNotificationMessageText(formatterUtils.format(CONFIRM_DELETE_TEXT, notebookName));

        mobileTaskSvc.click(noteBookLocators.notificationOk);
    }


    public void verifyNoteBookNotAvailable(final String notebookName) {
        mobileTaskSvc.click(noteBookLocators.menu);

        final String notebookLocator = formatterUtils.format(noteBookLocators.notebookLocator, notebookName);

        MobileElement element = mobileTaskSvc.getElementByReference(By.xpath(notebookLocator));

        if (element != null) {
            LOGGER.error("Notebook [{}] is available", notebookName);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Notebook [{}] is available", notebookName);
        }
    }

}
