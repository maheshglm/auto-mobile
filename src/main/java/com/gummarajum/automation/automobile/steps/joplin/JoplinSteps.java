package com.gummarajum.automation.automobile.steps.joplin;

import com.gummarajum.automation.automobile.screens.joplin.actions.NoteBookActions;
import com.gummarajum.automation.automobile.screens.joplin.actions.NoteBookConfigurationActions;
import com.gummarajum.automation.automobile.screens.joplin.actions.ReusableActions;
import com.gummarajum.automation.automobile.screens.joplin.locators.NoteBookConfigurationLocators;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.utils.AdbUtils;
import io.cucumber.datatable.DataTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JoplinSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoplinSteps.class);

    @Lazy
    @Autowired
    private NoteBookActions noteBookActions;

    @Lazy
    @Autowired
    private NoteBookConfigurationActions noteBookConfigurationActions;

    @Autowired
    private ReusableActions reusableActions;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private MobileTaskSvc mobileTaskSvc;


    public void launchJoplinApplication() {
        noteBookActions.launchJoplinApp();
    }

    public void navigateToConfiguration() {
        noteBookActions.navigateToConfiguration();
    }

    public void createNewNoteBook(final String notebookName) {
        noteBookActions.createNewNoteBook(stateSvc.expandExpression(notebookName));
    }

    public void deleteNoteBook(final String notebookName) {
        noteBookActions.deleteNoteBook(stateSvc.expandExpression(notebookName));
    }

    public void renameNoteBook(final String existingName, final String newName) {
        noteBookActions.renameNoteBook(existingName, newName);
    }

    public void verifyNoteBookAvailable(final String notebookName) {
        noteBookActions.verifyNoteBookVisible(stateSvc.expandExpression(notebookName));
    }

    public void verifyNoteBookNotAvailable(final String notebookName) {
        noteBookActions.verifyNoteBookNotAvailable(stateSvc.expandExpression(notebookName));
    }

    public void addNewNote(final String notebookName, List<String> list) {
        final String expandNoteBook = stateSvc.expandExpression(notebookName);
        final String title = stateSvc.expandExpression(list.get(0));
        final String body = stateSvc.expandExpression(list.get(1));
        noteBookActions.addNote(expandNoteBook, title, body);
    }

    public void verifyNoteAvailable(final String notebookName, List<String> list) {
        final String expandNoteBook = stateSvc.expandExpression(notebookName);
        final String title = stateSvc.expandExpression(list.get(0));
        final String body = stateSvc.expandExpression(list.get(1));
        noteBookActions.verifyNoteIsAvailable(expandNoteBook, title, body);
    }

    public void moveNote(final String noteTitle, final String fromNoteBook, final String toNoteBook) {
        final String expandTitle = stateSvc.expandExpression(noteTitle);
        final String expandFromNoteBook = stateSvc.expandExpression(fromNoteBook);
        final String expandToNoteBook = stateSvc.expandExpression(toNoteBook);
        noteBookActions.moveNote(expandTitle, expandFromNoteBook, expandToNoteBook);
    }

    public void verifyNoteNotAvailable(final String notebookName, final String title) {
        final String expandNoteBook = stateSvc.expandExpression(notebookName);
        final String expandTitle = stateSvc.expandExpression(title);
        noteBookActions.verifyNoteIsNotAvailable(expandNoteBook, expandTitle);
    }

    public void deleteNote(final String notebookName, final String title) {
        final String expandNoteBook = stateSvc.expandExpression(notebookName);
        final String expandTitle = stateSvc.expandExpression(title);

        noteBookActions.deleteNote(expandNoteBook, expandTitle);
    }

    public void verifyConfigurationValue(final String configurationElement, final String expectedValue) {
        final String expandExpectedVal = stateSvc.expandExpression(expectedValue);
        noteBookConfigurationActions.verifyConfigurationValue(configurationElement, expandExpectedVal);
    }

}
