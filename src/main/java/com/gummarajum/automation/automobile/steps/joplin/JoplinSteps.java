package com.gummarajum.automation.automobile.steps.joplin;

import com.gummarajum.automation.automobile.screens.joplin.actions.NoteBookActions;
import com.gummarajum.automation.automobile.screens.joplin.actions.ReusableActions;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.utils.AdbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class JoplinSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(JoplinSteps.class);

    @Lazy
    @Autowired
    private NoteBookActions noteBookActions;

    @Autowired
    private ReusableActions reusableActions;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private MobileTaskSvc mobileTaskSvc;


    public void launchJoplinApplication() {
        reusableActions.launchJoplinApp();
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


}
