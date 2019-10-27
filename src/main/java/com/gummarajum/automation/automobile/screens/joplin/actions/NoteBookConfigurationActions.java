package com.gummarajum.automation.automobile.screens.joplin.actions;

import com.gummarajum.automation.automobile.screens.joplin.locators.NoteBookConfigurationLocators;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.utils.StringVerifyUtils;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Lazy
@Component
public class NoteBookConfigurationActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteBookConfigurationActions.class);

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private NoteBookConfigurationLocators noteBookConfigurationLocators;

    @Autowired
    private StringVerifyUtils stringVerifyUtils;

    @PostConstruct
    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileTaskSvc.getDriver()), noteBookConfigurationLocators);
    }

    public void verifyConfigurationValue(final String configurationElement, final String expectedText) {
        MobileElement element = mobileTaskSvc.getElementByReflection("NoteBookConfigurationLocators", configurationElement);

        if (!mobileTaskSvc.isElementVisible(element)) {
            LOGGER.debug("Scrolling element [{}] into view", element.toString());
            element = mobileTaskSvc.scrollElementIntoView(element);
        }

        String actualText = mobileTaskSvc.isIos()
                ? ""
                : mobileTaskSvc.getAttribute(element, "text");

        stringVerifyUtils.assertEquals(expectedText, actualText);
    }

}
