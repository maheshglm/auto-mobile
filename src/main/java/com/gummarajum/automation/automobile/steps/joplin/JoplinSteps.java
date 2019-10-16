package com.gummarajum.automation.automobile.steps.joplin;

import com.gummarajum.automation.automobile.screens.joplin.actions.NoteBookActions;
import com.gummarajum.automation.automobile.screens.lazada.actions.HomeScreen;
import com.gummarajum.automation.automobile.steps.lazada.HomeScreenSteps;
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
    private AdbUtils adbUtils;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private MobileTaskSvc mobileTaskSvc;


    public void launchJoplinApplication(){
        noteBookActions.launchJoplinApp();
    }

    public void deleteNoteBook(final String notebookName){
        noteBookActions.deleteNoteBook(stateSvc.expandExpression(notebookName));
    }

    public void createNewNoteBook(final String notebookName){
        noteBookActions.createNewNoteBook(stateSvc.expandExpression(notebookName));
    }

    public void verifyNoteBookCreated(final String notebookName){
        noteBookActions.verifyNoteBookCreated(stateSvc.expandExpression(notebookName));
    }






}
