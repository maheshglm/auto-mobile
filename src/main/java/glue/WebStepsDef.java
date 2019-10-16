package glue;

import com.constants.LOCATORS;
import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.WebSteps;
import com.gummarajum.automation.automobile.utils.ScenarioUtils;
import cucumber.api.java8.En;

public class WebStepsDef implements En {

    private WebSteps webSteps = (WebSteps) Bootstrap.getBean(WebSteps.class);
    private ScenarioUtils scenarioUtils = (ScenarioUtils) Bootstrap.getBean(ScenarioUtils.class);

    public WebStepsDef() {

        Given("I navigate to url {string}", (String url) -> {
            webSteps.launchUrl(url);
        });

        Then("element having xpath {string} should have text as {string}", (String xpathLocator, String expectedText) -> {
            webSteps.verifyTextMatched(LOCATORS.XPATH, xpathLocator, expectedText);
        });


        Then("element having xpath {string} should not have text as {string}", (String xpathLocator, String expectedText) -> {
            webSteps.verifyTextNotMatched(LOCATORS.XPATH, xpathLocator, expectedText);
        });


        Then("element having xpath {string} should have value as {string}", (String xpathLocator, String expectedValue) -> {
            webSteps.verifyValueMatched(LOCATORS.XPATH, xpathLocator, expectedValue);
        });

        Then("element having xpath {string} should not have value as {string}", (String xpathLocator, String expectedValue) -> {
            webSteps.verifyValueNotMatched(LOCATORS.XPATH, xpathLocator, expectedValue);
        });

        Then("element having xpath {string} should be present", (String xpathLocator) -> {
            webSteps.verifyElementPresent(LOCATORS.XPATH, xpathLocator);
        });

        Then("element having xpath {string} should not be present", (String xpathLocator) -> {
            webSteps.verifyElementNotPresent(LOCATORS.XPATH, xpathLocator);
        });

        Then("element {string} should have text as {string}", (String pageObjectElement, String expectedText) -> {
        });

        Then("element {string} should not have text as {string}", (String pageObjectElement, String expectedText) -> {
        });

        Then("element {string} should be present", (String pageObjectElement) -> {
            webSteps.verifyElementPresent(pageObjectElement);
        });

        Then("element {string} should not be present", (String pageObjectElement) -> {
            webSteps.verifyElementNotPresent(pageObjectElement);
        });











    }
}
