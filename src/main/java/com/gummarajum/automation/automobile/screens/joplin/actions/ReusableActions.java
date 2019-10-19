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
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReusableActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReusableActions.class);

    private static final String BUNDLE_AND_PACKAGE_ID = "net.cozic.joplin";

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private NoteBookLocators noteBookLocators;

    @Autowired
    private FormatterUtils formatterUtils;

    @Autowired
    private ThreadSvc threadSvc;

    @Autowired
    private StateSvc stateSvc;

    public void launchJoplinApp() {
        threadSvc.sleepSeconds(1);

        if (!mobileTaskSvc.waitTillApplicationIsOpened(BUNDLE_AND_PACKAGE_ID, 30)) {
            LOGGER.error("Application with bundle or Package id [{}] is not opened!!!", BUNDLE_AND_PACKAGE_ID);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Application with activity id [{}] is not opened!!!", BUNDLE_AND_PACKAGE_ID);
        } else {
            LOGGER.debug("Application with bundle or Package id [{}] is opened.", BUNDLE_AND_PACKAGE_ID);
        }
    }

    void escapeMenuScreen() {
        mobileTaskSvc.swipeScreen(SCREEN_DIRECTION.LEFT, 1000);
    }

    void verifyNotificationMessageText(final String expectedMessage) {
        LOGGER.debug("Verification of Notification message [{}]", expectedMessage);


        String notificationMessage = mobileTaskSvc.getAttribute(noteBookLocators.notificationText, mobileTaskSvc.isIos() ? "value" : "text")
                .replace("\n\n", " ");

        if (!expectedMessage.equals(notificationMessage)) {
            LOGGER.error("Notification Message [{}] is not as Expected [{}]", notificationMessage, expectedMessage);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Notification Message [{}] is not as Expected [{}]", notificationMessage, expectedMessage);
        }
    }

    MobileElement getNoteBookElement(final String notebookName) {
        if (!noteBookLocators.menu.isDisplayed()) {
            this.escapeMenuScreen();
        }
        mobileTaskSvc.click(noteBookLocators.menu);
        String notebookLocator;

        if (mobileTaskSvc.isIos()) {
            notebookLocator = formatterUtils.format(noteBookLocators.iosNotebookLocator, notebookName);
        } else {
            notebookLocator = formatterUtils.format(noteBookLocators.androidNotebookLocator, notebookName);
        }


        LOGGER.debug("Get notebook element with locator [{}]", notebookLocator);
        return mobileTaskSvc.getElementByReference(By.xpath(notebookLocator));
    }

}
