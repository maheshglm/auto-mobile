package com.gummarajum.automation.automobile.screens.joplin.actions;

import com.constants.SCREEN_DIRECTION;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.screens.joplin.locators.NoteBookLocators;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
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

    private static final String NET_COZIC_JOPLIN_MAIN_ACTIVITY = "net.cozic.joplin.MainActivity";

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private NoteBookLocators noteBookLocators;

    @Autowired
    private FormatterUtils formatterUtils;

    @Autowired
    private ThreadSvc threadSvc;

    public void launchJoplinApp() {
        threadSvc.sleepSeconds(1);
        if (!mobileTaskSvc.waitTillApplicationIsOpened(NET_COZIC_JOPLIN_MAIN_ACTIVITY, 30)) {
            LOGGER.error("Application with activity id [{}] is not opened!!!", NET_COZIC_JOPLIN_MAIN_ACTIVITY);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Application with activity id [{}] is not opened!!!", NET_COZIC_JOPLIN_MAIN_ACTIVITY);
        } else {
            LOGGER.debug("Application with Activity id [{}] is opened.", NET_COZIC_JOPLIN_MAIN_ACTIVITY);
        }
    }

    void escapeMenuScreen() {
        mobileTaskSvc.swipeScreen(SCREEN_DIRECTION.LEFT, 1000);
    }

    void verifyNotificationMessageText(final String expectedMessage) {
        LOGGER.debug("Verification of Notification message [{}]", expectedMessage);
        final String notificationMessage = mobileTaskSvc.getAttribute(noteBookLocators.notificationText, "text")
                .replace("\n\n", " ");

        if (!expectedMessage.equals(notificationMessage)) {
            LOGGER.error("Notification Message [{}] is not as Expected [{}]", notificationMessage, expectedMessage);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Notification Message [{}] is not as Expected [{}]", notificationMessage, expectedMessage);
        }
    }

    MobileElement getNoteBookElement(final String notebookName) {
        mobileTaskSvc.click(noteBookLocators.menu);
        final String notebookLocator = formatterUtils.format(noteBookLocators.notebookLocator, notebookName);
        LOGGER.debug("Get notebook element with locator [{}]", notebookLocator);
        return mobileTaskSvc.getElementByReference(By.xpath(notebookLocator));
    }

}
