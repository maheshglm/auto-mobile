package com.gummarajum.automation.automobile.steps;

import com.constants.LOCATORS;
import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.screens.joplin.locators.NoteBookLocators;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.utils.ScenarioUtils;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.beans.Introspector;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import static com.gummarajum.automation.automobile.svc.MobileTaskSvc.DEFAULT_IMPLICIT_TIMEOUT;

@Component
public class WebSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSteps.class);

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private ScenarioUtils scenarioUtils;

    public void launchUrl(final String url) {
        String expandedUrl = stateSvc.expandExpression(url);
        mobileTaskSvc.launchBrowser(expandedUrl);
    }

    public void verifyTextMatched(final LOCATORS locators, final String identifier, final String expectedText) {
        By by = mobileTaskSvc.constructByObject(locators, identifier);
        MobileElement element = mobileTaskSvc.getElementByReference(by, 10);
        String actualText = mobileTaskSvc.getAttribute(element, "text");

        if (!expectedText.equals(actualText)) {
            LOGGER.error("Text Attribute Match verification failed, expected [{}], actual [{}]", expectedText, actualText);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Text Attribute Match verification failed, expected [{}], actual [{}]", expectedText, actualText);
        }
    }

    public void verifyTextNotMatched(final LOCATORS locators, final String identifier, final String expectedText) {
        By by = mobileTaskSvc.constructByObject(locators, identifier);
        MobileElement element = mobileTaskSvc.getElementByReference(by, 10);
        String actualText = mobileTaskSvc.getAttribute(element, "text");

        if (expectedText.equals(actualText)) {
            LOGGER.error("Text Attribute Not Match verification failed, expected [{}], actual [{}]", expectedText, actualText);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Text Attribute Not Match verification failed, expected [{}], actual [{}]", expectedText, actualText);
        }
    }

    public void verifyValueMatched(final LOCATORS locators, final String identifier, final String expectedValue) {
        By by = mobileTaskSvc.constructByObject(locators, identifier);
        MobileElement element = mobileTaskSvc.getElementByReference(by, 10);
        String actualValue = mobileTaskSvc.getAttribute(element, "value");

        if (!expectedValue.equals(actualValue)) {
            LOGGER.error("Value Attribute Match verification failed, expected [{}], actual [{}]", expectedValue, actualValue);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Value Attribute Match verification failed, expected [{}], actual [{}]", expectedValue, actualValue);
        }
    }

    public void verifyValueNotMatched(final LOCATORS locators, final String identifier, final String expectedValue) {
        By by = mobileTaskSvc.constructByObject(locators, identifier);
        MobileElement element = mobileTaskSvc.getElementByReference(by, 10);
        String actualValue = mobileTaskSvc.getAttribute(element, "value");

        if (expectedValue.equals(actualValue)) {
            LOGGER.error("Value Attribute Not Match verification failed, expected [{}], actual [{}]", expectedValue, actualValue);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Value Attribute Not Match verification failed, expected [{}], actual [{}]", expectedValue, actualValue);
        }
    }

    public void verifyElementPresent(final LOCATORS locators, final String identifier) {
        By by = mobileTaskSvc.constructByObject(locators, identifier);
        mobileTaskSvc.getElementByReference(by, 0);
    }

    public void verifyElementNotPresent(final LOCATORS locators, final String identifier) {
        By by = mobileTaskSvc.constructByObject(locators, identifier);
        if (mobileTaskSvc.getElementByReference(by) != null) {
            LOGGER.error("Element [{}] Not present verification failed", by.toString());
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Element [{}] Not present verification failed", by.toString());
        }
    }


    public void verifyElementPresent(final String pageObjectElement) {
        String expandPageObjectElement = stateSvc.expandExpression(pageObjectElement);
        String[] split = expandPageObjectElement.split("\\.");
        try {
            MobileElement element = mobileTaskSvc.getElementByReflection(split[0], split[1]);
            mobileTaskSvc.setImplicitWaitTimeOutSeconds(1);
            element.isDisplayed();
            mobileTaskSvc.setImplicitWaitTimeOutSeconds(DEFAULT_IMPLICIT_TIMEOUT);
        } catch (NoSuchElementException e) {
            LOGGER.error("Page Object Element [{}] does not exist", expandPageObjectElement);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Page Object Element [{}] does not exist", expandPageObjectElement);
        }
        scenarioUtils.write(expandPageObjectElement + " is present");

    }

    public void verifyElementNotPresent(final String pageObjectElement) {
        String expandPageObjectElement = stateSvc.expandExpression(pageObjectElement);
        String[] split = expandPageObjectElement.split("\\.");
        boolean isElementPresent = true;
        try {
            MobileElement element = mobileTaskSvc.getElementByReflection(split[0], split[1]);
            mobileTaskSvc.setImplicitWaitTimeOutSeconds(1);
            element.isDisplayed();
            mobileTaskSvc.setImplicitWaitTimeOutSeconds(DEFAULT_IMPLICIT_TIMEOUT);
        } catch (NoSuchElementException e) {
            isElementPresent = false;
        }

        if(isElementPresent) {
            LOGGER.error("Page Object Element [{}] is exist", expandPageObjectElement);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Page Object Element [{}] is exist", expandPageObjectElement);
        }
        scenarioUtils.write(expandPageObjectElement + " is not present");

    }


}
