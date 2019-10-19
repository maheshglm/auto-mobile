package com.gummarajum.automation.automobile.screens.joplin.actions;

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

    @Autowired
    private ReusableActions reusableActions;

    @PostConstruct
    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileTaskSvc.getDriver()), noteBookLocators);
    }

    public void createNewNoteBook(final String notebookName) {
        LOGGER.debug("Creation of Notebook [{}]", notebookName);
        mobileTaskSvc.click(noteBookLocators.menu);
        mobileTaskSvc.click(noteBookLocators.newNoteBook);
        mobileTaskSvc.sendKeys(noteBookLocators.enterNoteBookTitle, notebookName);
        mobileTaskSvc.click(noteBookLocators.saveNoteBook);
    }

    public void verifyNoteBookVisible(final String notebookName) {
        LOGGER.debug("Verify Notebook created [{}]", notebookName);
        MobileElement element = reusableActions.getNoteBookElement(notebookName);
        try {
            mobileTaskSvc.verifyElementIsVisible(element);
        } finally {
            reusableActions.escapeMenuScreen();
        }
    }

    public void renameNoteBook(final String existingName, final String newName) {
        MobileElement element = reusableActions.getNoteBookElement(existingName);

        if (element != null) {
            LOGGER.debug("Renaming Notebook [{}] to [{}]", existingName, newName);
            mobileTaskSvc.longPress(element, 1000);

            reusableActions.verifyNotificationMessageText("Notebook: " + existingName);

            mobileTaskSvc.click(noteBookLocators.notificationRename);

            String editLocator;
            if (mobileTaskSvc.isIos()) {
                editLocator = formatterUtils.format(noteBookLocators.iosEditNoteBookTitleLocator, existingName);
            } else {
                editLocator = formatterUtils.format(noteBookLocators.androidEditNoteBookTitleLocator, existingName);
            }

            MobileElement editTextElement = mobileTaskSvc.findElement(By.xpath(editLocator));

            mobileTaskSvc.sendKeys(editTextElement, newName);
            mobileTaskSvc.click(noteBookLocators.saveNoteBook);
        } else {
            reusableActions.escapeMenuScreen();
            LOGGER.warn("Notebook [{}] not available to rename!!!", existingName);
        }
    }


    public void deleteNoteBook(final String notebookName) {
        MobileElement element = reusableActions.getNoteBookElement(notebookName);

        if (element != null) {
            LOGGER.debug("Deleting Notebook [{}]", notebookName);
            mobileTaskSvc.longPress(element, 1000);

            reusableActions.verifyNotificationMessageText("Notebook: " + notebookName);

            mobileTaskSvc.click(noteBookLocators.notificationDelete);

            reusableActions.verifyNotificationMessageText(formatterUtils.format(CONFIRM_DELETE_TEXT, notebookName));

            mobileTaskSvc.click(noteBookLocators.notificationOk);
        } else {
            reusableActions.escapeMenuScreen();
            LOGGER.warn("Notebook [{}] not available to delete!!!", notebookName);
        }
    }

    public void verifyNoteBookNotAvailable(final String notebookName) {
        LOGGER.debug("Verify Notebook [{}] not available", notebookName);
        try {
            if (reusableActions.getNoteBookElement(notebookName) != null) {
                LOGGER.error("Notebook [{}] is available", notebookName);
                throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Notebook [{}] is available", notebookName);
            }
        } finally {
            reusableActions.escapeMenuScreen();
        }
    }

}
