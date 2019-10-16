package com.gummarajum.automation.automobile.svc;

import com.constants.*;
import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.utils.*;
import com.mdl.GetXY;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.MultiTouchAction;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.time.Duration;
import java.util.Hashtable;
import java.util.concurrent.TimeUnit;

import static com.constants.Properties.MOBILE_PLATFORM;
import static com.constants.Properties.SCREENSHOT_PATH;
import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

@Service
public class MobileTaskSvc {

    private static final Logger LOGGER = LoggerFactory.getLogger(MobileTaskSvc.class);
    public static final String ELEMENT_IS_NOT_VISIBLE = "Element [{}] is not visible";

    public static final Integer DEFAULT_IMPLICIT_TIMEOUT = 10;
    private Hashtable<String, Integer> namePrefices = new Hashtable<>();
    private AppiumDriver driver;
    private boolean sessionEstablished = false;

    @Autowired
    private MobileDriverSvc mobileDriverSvc;

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private UiSelectorUtils uiSelectorUtils;

    @Autowired
    private FormatterUtils formatterUtils;

    @Autowired
    private ThreadSvc threadSvc;

    @Autowired
    private FileDirUtils fileDirUtils;

    @Autowired
    private ScenarioUtils scenarioUtils;

    @Autowired
    private AdbUtils adbUtils;

    @Autowired
    private DateTimeUtils dateTimeUtils;

    public AppiumDriver getDriver() {
        if (!sessionEstablished) {
            driver = mobileDriverSvc.driver();
            sessionEstablished = true;
            driver.manage().timeouts().implicitlyWait(DEFAULT_IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        }
        return driver;
    }

    //MOBILE_PLATFORM variable is set during Driver object creation in MobileDriverSvc
    public boolean isIos() {
        return stateSvc.getStringVar(MOBILE_PLATFORM).equalsIgnoreCase(PLATFORM.IOS.toString());
    }


    public void setImplicitWaitTimeOutSeconds(final Integer seconds) {
        getDriver().manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
    }

    public TouchAction getTouchAction() {
        return new TouchAction(getDriver());
    }

    public MultiTouchAction getMultiTouchAction() {
        return new MultiTouchAction(getDriver());
    }

    public WebDriverWait getWebDriverWait(final Integer timeoutInSeconds) {
        return new WebDriverWait(getDriver(), timeoutInSeconds);
    }

    public void launchBrowser(final String url) {
        getDriver().get(url);
    }

    public void quitDriver() {
        try {
            mobileDriverSvc.quit(getDriver());
        } finally {
            sessionEstablished = false;
        }
    }

    public void closeApp() {
        try {
            getDriver().closeApp();
        } catch (java.lang.Exception e) {
            LOGGER.error("MobileException occurred while closing app");
        }
    }

    public void tap(final MobileElement mobileElement) {
        getTouchAction().tap(TapOptions.tapOptions()
                .withElement(element(mobileElement)))
                .waitAction(waitOptions(ofMillis(250)))
                .perform();

    }

    public void tap(final int xPoint, final int yPoint) {
        this.getTouchAction()
                .tap(point(xPoint, yPoint))
                .waitAction(waitOptions(ofMillis(250))).perform();
    }

    public void tap(final int xPoint, final int yPoint, final MobileElement relativeMobileElement) {
        getTouchAction().tap(TapOptions.tapOptions()
                .withElement(element(relativeMobileElement, xPoint, yPoint)))
                .waitAction(waitOptions(ofMillis(250)))
                .perform();
    }

    public void pressByCoordinates(final int x, final int y, final long seconds) {
        getTouchAction()
                .press(point(x, y))
                .waitAction(waitOptions(ofSeconds(seconds)))
                .release()
                .perform();
    }

    public void longPress(final MobileElement mobileElement) {
        getTouchAction().longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(mobileElement)))
                .release()
                .waitAction(waitOptions(ofMillis(250)))
                .perform();
    }

    public void longPress(final MobileElement mobileElement, final int durationInMilliSeconds) {
        getTouchAction().longPress(LongPressOptions.longPressOptions()
                .withElement(element(mobileElement)).withDuration(Duration.ofMillis(durationInMilliSeconds)))
                .release()
                .waitAction(waitOptions(ofMillis(250)))
                .perform();
    }

    public void longPress(final int xPoint, final int yPoint) {
        getTouchAction().longPress(point(xPoint, yPoint))
                .release()
                .waitAction(waitOptions(ofMillis(250)))
                .perform();
    }

    public void pressAndroidKey(AndroidKey androidKey) {
        try {
            ((AndroidDriver) getDriver()).pressKey(new KeyEvent(androidKey));
        } catch (java.lang.Exception e) {
            LOGGER.error("Unable to perform presskey on [{}]", androidKey.name(), e);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Unable to perform presskey on [{}]", androidKey.name());
        }
    }

    public void longPressAndroidKey(AndroidKey androidKey) {
        try {
            ((AndroidDriver) getDriver()).longPressKey(new KeyEvent(androidKey));
        } catch (java.lang.Exception e) {
            LOGGER.error("Unable to perform longpresskey on [{}]", androidKey.name(), e);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Unable to perform longpresskey on [{}]", androidKey.name());
        }
    }

    public void dragAndDrop(final MobileElement fromElement, final MobileElement toElement) {
        GetXY getXY = getElementXY(toElement);
        getTouchAction().longPress(LongPressOptions.longPressOptions()
                .withElement(ElementOption.element(fromElement)))
                .moveTo(PointOption.point(getXY.getX(), getXY.getY()))
                .release()
                .waitAction(waitOptions(ofMillis(250)))
                .perform();
    }

    public MobileElement scrollIntoView(final UISELECTORS parentSelector, final String parentIdentifier, final UISELECTORS elementSelector, final String elementIdentifier) {
        String parentUiLocator = uiSelectorUtils.constructUiSelector(parentSelector, parentIdentifier).getLocator();
        String elementUiLocator = uiSelectorUtils.constructUiSelector(elementSelector, elementIdentifier).getLocator();
        try {
            String scrollLocator = formatterUtils.format("new UiScrollable(%s).scrollIntoView(%s);", parentUiLocator, elementUiLocator);
            return (MobileElement) ((AndroidDriver) getDriver()).findElementByAndroidUIAutomator(scrollLocator);
        } catch (java.lang.Exception e) {
            LOGGER.error("MobileException occurred while scrolling to [{}]", elementIdentifier);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "MobileException occurred while scrolling to [{}]", elementIdentifier);
        }
    }

    public MobileElement scrollIntoView(final UISELECTORS elementSelector, final String elementIdentifier) {
        String elementUiLocator = uiSelectorUtils.constructUiSelector(elementSelector, elementIdentifier).getLocator();
        try {
            String scrollLocator = formatterUtils.format("new UiScrollable(new UiSelector().scrollable(true)).scrollIntoView(%s);", elementUiLocator);
            return (MobileElement) ((AndroidDriver) getDriver()).findElementByAndroidUIAutomator(scrollLocator);
        } catch (java.lang.Exception e) {
            LOGGER.error("MobileException occurred while scrolling to [{}]", elementIdentifier);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "MobileException occurred while scrolling to [{}]", elementIdentifier);
        }
    }

    public MobileElement scrollElementIntoView(final By by, final long swipeDownDuration) {
        MobileElement element = this.getElementByReference(by);
        while (element == null) {
            this.swipeScreen(SCREEN_DIRECTION.DOWN, swipeDownDuration);
            threadSvc.sleepSeconds(1);
            element = this.getElementByReference(by);
        }
        return element;
    }

    public void swipeElement(MobileElement element, ELEMENT_DIRECTION direction, long duration) {
        GetXY elementXY = this.getElementXY(element);
        Dimension size = this.getDriver().manage().window().getSize();
        int endPoint = 0;

        switch (direction) {
            case RIGHT:
                endPoint = (int) (size.width * 0.9);
                break;

            case LEFT:
                endPoint = (int) (size.width * 0.2);
                break;
        }

        this.getTouchAction()
                .press(point(elementXY.getX(), elementXY.getY()))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                .moveTo(PointOption.point(endPoint, elementXY.getY()))
                .release()
                .perform();
    }


    public void swipeScreen(SCREEN_DIRECTION direction, long durationInMilliSeconds) {
        Dimension size = getDriver().manage().window().getSize();

        int startX = 0;
        int endX = 0;
        int startY = 0;
        int endY = 0;

        switch (direction) {
            case LEFT:
                startY = (size.height / 2);
                startX = (int) (size.width * 0.90);
                endX = (int) (size.width * 0.05);
                getTouchAction()
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMilliSeconds)))
                        .moveTo(PointOption.point(endX, startY))
                        .release()
                        .perform();
                break;

            case RIGHT:
                startY = (size.height / 2);
                startX = (int) (size.width * 0.05);
                endX = (int) (size.width * 0.90);
                getTouchAction()
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMilliSeconds)))
                        .moveTo(PointOption.point(endX, startY))
                        .release()
                        .perform();
                break;

            case UP:
                startY = (int) (size.height * 0.30);
                startX = (size.width / 2);
                getTouchAction()
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMilliSeconds)))
                        .moveTo(PointOption.point(endX, startY))
                        .release()
                        .perform();
                break;


            case DOWN:
                startY = (int) (size.height * 0.80);
                endY = (int) (size.height * 0.20);
                startX = (size.width / 2);
                getTouchAction()
                        .press(PointOption.point(startX, startY))
                        .waitAction(WaitOptions.waitOptions(Duration.ofMillis(durationInMilliSeconds)))
                        .moveTo(PointOption.point(startX, endY))
                        .release()
                        .perform();
                break;

        }
    }


    public By constructByObject(final LOCATORS locator, final String identifier) {
        if (locator.equals(LOCATORS.XPATH)) {
            return By.xpath(identifier);
        } else if (locator.equals(LOCATORS.ID)) {
            return By.id(identifier);
        } else if (locator.equals(LOCATORS.CLASSNAME)) {
            return By.className(identifier);
        } else if (locator.equals(LOCATORS.CSS)) {
            return By.cssSelector(identifier);
        } else {
            LOGGER.error("Undefined Locator to construct By object");
            throw new MobileException(MobileExceptionType.UNDEFINED, "Undefined Locator to construct By object");
        }
    }


    public MobileElement findElement(final By by) {
        try {
            return (MobileElement) getDriver().findElement(by);
        } catch (Exception e) {
            LOGGER.error("Exception while finding element with By [{}]", by.toString(), e);
            throw new MobileException(MobileExceptionType.VALIDATION_FAILED, "Exception while finding element with By [{}]", by.toString());
        }
    }

    public MobileElement getElementByReference(final By by) {
        try {
            return (MobileElement) getDriver().findElement(by);
        } catch (NoSuchElementException e) {
            return null;
        }
    }


    public MobileElement getElementByReflection(final String className, final String mobileElementName) {
        Object bean = Bootstrap.getBean(Introspector.decapitalize(className));
        try {
            Field field = bean.getClass().getField(mobileElementName);
            return (MobileElement) field.get(bean);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            LOGGER.error("Unable to fetch MobileElement in Class [{}] with Name [{}]", className, mobileElementName, e);
            throw new MobileException(MobileExceptionType.UNSATISFIED_IMPLICIT_ASSUMPTION, "Unable to fetch MobileElement in Class [{}] with Name [{}]", className, mobileElementName);
        }
    }


    private GetXY getElementXY(final MobileElement mobileElement) {
        Point pointElement = mobileElement.getLocation();
        Dimension dimElement = mobileElement.getSize();

        int x = pointElement.getX() + dimElement.getWidth() / 2;
        int y = pointElement.getY() + dimElement.getHeight() / 2;

        return new GetXY(x, y);
    }

    public void launchApp() {
        getDriver().launchApp();
    }

    public void clickIfVisible(MobileElement element, Integer timeOutInSeconds) {
        if (this.waitTillElementIsPresent(element, timeOutInSeconds))
            this.click(element);
    }

    public void click(final By by) {
        MobileElement element = this.findElement(by);
        this.click(element);
    }

    public void click(MobileElement element) {
        try {
            getWebDriverWait(30)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));

            element.click();
        } catch (java.lang.Exception e) {
            LOGGER.error("Element [{}] is not clickable", element.toString(), e);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Element [{}] is not clickable", element.toString());
        }
    }

    public void sendKeys(MobileElement element, String textToEnter) {
        try {
            getWebDriverWait(30)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.elementToBeClickable(element));

            element.clear();
            element.setValue(textToEnter);
        } catch (java.lang.Exception e) {
            LOGGER.error("Element [{}] is not clickable", element.toString(), e);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Element [{}] is not clickable", element.toString());
        }
    }

    public void verifyElementIsVisible(MobileElement element) {
        try {
            if (element != null && !element.isDisplayed()) {
                LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString());
                throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
            }
        } catch (NoSuchElementException e) {
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString(), e);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
        }
    }

    public void verifyElementIsVisible(By by) {
        MobileElement element = this.findElement(by);
        verifyElementIsVisible(element);
    }

    public void verifyElementIsVisible(MobileElement element, Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));

            if (element != null && !element.isDisplayed()) {
                LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString());
                throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
            }
        } catch (NoSuchElementException e) {
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE, element.toString(), e);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, ELEMENT_IS_NOT_VISIBLE, element.toString());
        }
    }

    public boolean waitTillElementIsPresent(final By by, final Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public boolean waitTillElementIsPresent(MobileElement element, Integer timeOutInSeconds) {
        try {
            getWebDriverWait(timeOutInSeconds)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(StaleElementReferenceException.class)
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }

    public MobileElement getElementByUISelector(UISELECTORS uiSelectors, String identifier) {
        String locator = uiSelectorUtils.constructUiSelector(uiSelectors, identifier).getLocator();
        try {
            return (MobileElement) ((AndroidDriver) getDriver()).findElementByAndroidUIAutomator(locator);
        } catch (java.lang.Exception e) {
            LOGGER.error("MobileException occurred while finding element by Android UiAutomator with selector [{}] and identifier [{}]", uiSelectors.name(), identifier);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "MobileException occurred while finding element by Android UiAutomator with selector [{}] and identifier [{}]", uiSelectors.name(), identifier);
        }
    }

    private Integer generateScreenshotNumber(final String prefix) {
        Integer snapshotNumber = namePrefices.getOrDefault(prefix, 0);
        namePrefices.put(prefix, snapshotNumber + 1);
        return namePrefices.get(prefix);
    }

    public void takeScreenshot(final String prefix) {
        File src = getDriver().getScreenshotAs(OutputType.FILE);
        String pathname = SCREENSHOT_PATH + File.separator + prefix + "_" + this.generateScreenshotNumber(prefix) + ".png";
        File dst = new File(System.getProperty("user.dir") + File.separator + pathname);
        fileDirUtils.copyFile(src, dst);
        byte[] bytes = fileDirUtils.readFileToByteArray(dst.getAbsolutePath());
        scenarioUtils.embed(bytes, "image/png");
    }

    public synchronized void takeScreenshot() {
        this.takeScreenshot("screenshot");
    }


    public boolean waitTillApplicationIsOpened(final String activityId, final Integer maxTimeOutInSeconds) {
        long millisStart = dateTimeUtils.currentTimeMillis();
        long millisCurrent = millisStart;

        String foregroundActivity = adbUtils.getForegroundActivity();
        while ((millisCurrent - millisStart) / 1000 <= maxTimeOutInSeconds) {
            if (foregroundActivity.contains(activityId)) {
                return true;
            }
            LOGGER.warn("App is not opened Yet, Launching App...");
            this.launchApp();
            threadSvc.sleepSeconds(2);
            foregroundActivity = adbUtils.getForegroundActivity();
            millisCurrent = dateTimeUtils.currentTimeMillis();
        }
        return false;
    }

    public MobileElement getElementByReference(final By by, final Integer maxPollingTime) {
        long millisStart = dateTimeUtils.currentTimeMillis();
        long millisCurrent = millisStart;

        MobileElement element = this.getElementByReference(by);
        while (element == null && ((millisCurrent - millisStart) / 1000) <= maxPollingTime) {
            threadSvc.sleepSeconds(2);
            millisCurrent = dateTimeUtils.currentTimeMillis();
            element = this.getElementByReference(by);
        }

        if (element == null) {
            LOGGER.error("Element not found with By Object [{}]", by.toString());
            throw new MobileException(MobileExceptionType.ELEMENT_NOT_FOUND, "Element not found with By Object [{}]", by.toString());
        }
        return element;
    }


    public String getAttribute(final MobileElement element, final String attribute) {
        if (element != null) {
            return element.getAttribute(attribute);
        }
        return "";
    }


}


