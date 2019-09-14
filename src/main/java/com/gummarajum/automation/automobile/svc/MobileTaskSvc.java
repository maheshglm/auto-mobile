package com.gummarajum.automation.automobile.svc;

import com.gummarajum.automation.automobile.Exception;
import com.gummarajum.automation.automobile.ExceptionType;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

@Service
public class MobileTaskSvc {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileTaskSvc.class);
    public static final String ELEMENT_IS_NOT_VISIBLE = "Element [{}] is not visible";

    public static final Integer DEFAULT_IMPLICIT_TIMEOUT = 10;

    @Autowired
    private MobileDriverSvc mobileDriverSvc;

    @Autowired
    private StateSvc stateSvc;

    private AppiumDriver driver;

    private boolean sessionEstablished = false;

    public AppiumDriver getDriver() {
        if (!sessionEstablished) {
            driver = mobileDriverSvc.driver();
            sessionEstablished = true;
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
        return driver;
    }

    public void launchBrowser(final String url){
        getDriver().get(url);
    }

    public void quitDriver() {
        sessionEstablished = false;
        mobileDriverSvc.quit(getDriver());
    }

    public void closeApp() {
        try {
            getDriver().closeApp();
        }catch (Exception e){
            LOGGER.error("Exception occurred while closing app");
        }
    }

    public void launchApp() {
        getDriver().launchApp();
    }


    public void setImplicitWaitTimeOutSeconds(final Integer seconds) {
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public WebDriverWait getWebDriverWait(final Integer timeoutInSeconds) {
        return new WebDriverWait(getDriver(), timeoutInSeconds);
    }


    public void clickIfVisible(MobileElement element, Integer timeOutInSeconds) {
        if (this.isElementVisible(element, timeOutInSeconds, false))
            this.click(element);
    }


    public void click(MobileElement element) {
        try {
            getWebDriverWait(30)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));

            element.click();
        } catch (Exception e) {
            LOGGER.error("Element [{}] is not clickable", element.toString(), e);
            throw new Exception(ExceptionType.PROCESSING_FAILED, "Element [{}] is not clickable", element.toString());
        }
    }

    public void sendKeys(MobileElement element, String textToEnter) {
        try {
            getWebDriverWait(30)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));

            element.clear();
            element.sendKeys(textToEnter);
        } catch (Exception e) {
            LOGGER.error("Element [{}] is not clickable", element.toString(), e);
            throw new Exception(ExceptionType.PROCESSING_FAILED, "Element [{}] is not clickable", element.toString());
        }
    }


    public void isElementVisible(MobileElement element) {
        try {
            if (element != null && !element.isDisplayed()) {
                LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString());
                throw new Exception(ExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
            }
        } catch (NoSuchElementException e) {
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString(), e);
            throw new Exception(ExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
        }
    }

    public boolean isElementVisible(MobileElement element, Integer timeOutInSeconds, boolean throwException) {
        if (throwException) {
            this.isElementVisible(element);
        }
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void isElementVisible(MobileElement element, Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));

            if (element != null && !element.isDisplayed()) {
                LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString());
                throw new Exception(ExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
            }
        } catch (NoSuchElementException e) {
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString(), e);
            throw new Exception(ExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
        }
    }

    public TouchAction getTouchAction() {
        return new TouchAction(getDriver());
    }


    //Tap by coordinates
    public void tapByCoordinates(int x, int y) {
        this.getTouchAction()
                .tap(point(x, y))
                .waitAction(waitOptions(ofMillis(250))).perform();
    }


    public void horizontalSwipeByPercentage(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.height * anchorPercentage);
        int startPoint = (int) (size.width * startPercentage);
        int endPoint = (int) (size.width * endPercentage);

        new TouchAction(driver)
                .press(point(startPoint, anchor))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endPoint, anchor))
                .release().perform();
    }

    //Vertical Swipe by percentages
    public void verticalSwipeByPercentages(double startPercentage, double endPercentage, double anchorPercentage) {
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width * anchorPercentage);
        int startPoint = (int) (size.height * startPercentage);
        int endPoint = (int) (size.height * endPercentage);

        new TouchAction(driver)
                .press(point(anchor, startPoint))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(anchor, endPoint))
                .release().perform();
    }


}
