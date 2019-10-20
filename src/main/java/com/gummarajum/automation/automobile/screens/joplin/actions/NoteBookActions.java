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
import java.util.List;

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
        mobileTaskSvc.click(noteBookLocators.newNoteBook, 30);
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
                editLocator = formatterUtils.format(noteBookLocators.iEditNoteBookTitleLocator, existingName);
            } else {
                editLocator = formatterUtils.format(noteBookLocators.aEditNoteBookTitleLocator, existingName);
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

    public void addNote(final String notebookName, final String title, final String body) {
        reusableActions.selectNotebook(notebookName);
        mobileTaskSvc.click(noteBookLocators.add, 10);
        mobileTaskSvc.click(noteBookLocators.newNote, 10);
        mobileTaskSvc.sendKeys(noteBookLocators.addNoteTitle, title);
        mobileTaskSvc.sendKeys(noteBookLocators.addNoteBody, body);
        threadSvc.sleepMillis(1000);

        reusableActions.goBackFromNotes(notebookName);
    }


    public void verifyNoteIsAvailable(final String notebookName, final String title, final String body) {
        LOGGER.debug("Verify Note [{}] is available under [{}]", title, notebookName);
        try {
            reusableActions.selectNote(notebookName, title);

            final String titleLocator = mobileTaskSvc.isIos()
                    ? "WIP"
                    : formatterUtils.format(noteBookLocators.aEditNoteTitleLocator, title);

            final String bodyLocator = mobileTaskSvc.isIos()
                    ? "WIP"
                    : formatterUtils.format(noteBookLocators.aEditNoteBodyLocator, body);

            mobileTaskSvc.findElement(By.xpath(titleLocator));
            mobileTaskSvc.findElement(By.xpath(bodyLocator));
        } finally {
            reusableActions.goBackFromNotes(notebookName);
        }
    }


    public void verifyNoteIsNotAvailable(final String notebookName, final String title) {
        LOGGER.debug("Verify Note [{}] is NOT available under [{}]", title, notebookName);
        try {
            reusableActions.selectNotebook(notebookName);
            final String noteLocator = mobileTaskSvc.isIos()
                    ? "WIP"
                    : formatterUtils.format(noteBookLocators.aNoteLocator, title);

            List<MobileElement> elements = mobileTaskSvc.findElements(By.xpath(noteLocator));

            if (elements.size() != 0) {
                LOGGER.error("Note [{}] is available under notebook [{}]", title, notebookName);
                throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Note [{}] is available under notebook [{}]", title, notebookName);
            }
        } finally {
            reusableActions.goBackFromNotes(notebookName);
        }
    }

    public void moveNote(final String noteTitle, final String fromNoteBook, final String toNoteBook) {
        LOGGER.debug("Moving note [{}] to [{}]", noteTitle, toNoteBook);
        try {
            reusableActions.selectNote(fromNoteBook, noteTitle);
            mobileTaskSvc.click(noteBookLocators.moveNoteIcon);

            final String targetNoteBookLocator = mobileTaskSvc.isIos()
                    ? "WIP"
                    : formatterUtils.format(noteBookLocators.aMoveNoteNotebookLocator, toNoteBook);

            mobileTaskSvc.click(By.xpath(targetNoteBookLocator));
        } finally {
            reusableActions.goBackFromNotes(toNoteBook);
        }
    }

    public void deleteNote(final String noteBookName, final String title) {
        LOGGER.debug("Delete note [{}] from [{}]", title, noteBookName);
        try {
            reusableActions.selectNote(noteBookName, title);
            mobileTaskSvc.click(noteBookLocators.noteMenu);
            mobileTaskSvc.click(noteBookLocators.deleteNote);

            threadSvc.sleepMillis(1000);

            mobileTaskSvc.verifyElementIsVisible(noteBookLocators.deleteNoteMessage);
            mobileTaskSvc.click(noteBookLocators.deleteNoteOk);

        } finally {
            reusableActions.goBackFromNotes(noteBookName);
        }
    }


}
