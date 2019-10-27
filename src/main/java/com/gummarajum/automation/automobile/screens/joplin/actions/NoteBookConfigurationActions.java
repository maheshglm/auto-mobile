package com.gummarajum.automation.automobile.screens.joplin.actions;

import com.gummarajum.automation.automobile.screens.joplin.locators.NoteBookConfigurationLocators;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.ThreadSvc;
import com.gummarajum.automation.automobile.utils.FormatterUtils;
import com.gummarajum.automation.automobile.utils.StringVerifyUtils;
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
public class NoteBookConfigurationActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteBookConfigurationActions.class);

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private NoteBookConfigurationLocators noteBookConfigurationLocators;

    @Autowired
    private StringVerifyUtils stringVerifyUtils;

    @Autowired
    private FormatterUtils formatterUtils;

    @Autowired
    private ThreadSvc threadSvc;

    @PostConstruct
    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileTaskSvc.getDriver()), noteBookConfigurationLocators);
    }


    private MobileElement getConfigElement(final String elementName) {
        return mobileTaskSvc.getElementByReflection("NoteBookConfigurationLocators", elementName);
    }

    public void verifyConfigurationValue(final String configurationElement, final String expectedText) {
        MobileElement element = this.getConfigElement(configurationElement);

        if (!mobileTaskSvc.isElementVisible(element)) {
            LOGGER.debug("Scrolling element [{}] into view", element.toString());
            element = mobileTaskSvc.scrollElementIntoView(element);
        }

        String actualText = mobileTaskSvc.isIos()
                ? ""
                : mobileTaskSvc.getAttribute(element, "text");

        stringVerifyUtils.assertEquals(expectedText, actualText);
    }


    public void setConfigDropdownValue(final String configurationElement, final String value) {
        MobileElement element = this.getConfigElement(configurationElement);

        if (!mobileTaskSvc.isElementVisible(element)) {
            LOGGER.debug("Scrolling element [{}] into view", element.toString());
            element = mobileTaskSvc.scrollElementIntoView(element);
        }

        mobileTaskSvc.click(element);
        String locator = formatterUtils.format(noteBookConfigurationLocators.dropDownValue, value);
        MobileElement dropdownValElement = mobileTaskSvc.scrollElementIntoView(By.xpath(locator), 0.75, 1000);
        mobileTaskSvc.click(dropdownValElement);
    }


    public void setConfigSeekBar(final String configurationElement, final String value) {
        MobileElement element = this.getConfigElement(configurationElement);

        if (!mobileTaskSvc.isIos()) {
            mobileTaskSvc.setSliderValue(element, Double.valueOf(value) - 1);
        } else {
            //WIP
        }
    }


    public void setConfigSwitch(final String configurationElement, final String newValue) {
        MobileElement element = this.getConfigElement(configurationElement);

        if (!mobileTaskSvc.isElementVisible(element)) {
            LOGGER.debug("Scrolling element [{}] into view", element.toString());
            element = mobileTaskSvc.scrollElementIntoView(element);
        }

        String currentValue = mobileTaskSvc.isIos()
                ? ""
                : mobileTaskSvc.getAttribute(element, "text");

        if (!newValue.equalsIgnoreCase(currentValue)){
            mobileTaskSvc.tap(element);
        }
    }


}
