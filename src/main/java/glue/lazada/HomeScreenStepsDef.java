package glue.lazada;

import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.lazada.HomeScreenSteps;
import cucumber.api.java8.En;

public class HomeScreenStepsDef implements En {

    private HomeScreenSteps homescreenSteps = (HomeScreenSteps) Bootstrap.getBean(HomeScreenSteps.class);

    public HomeScreenStepsDef(){

        Given("Lazada app is running", () -> {
            homescreenSteps.launchApp();
        });

        Then("I expect Scan element is visible", () -> {
            homescreenSteps.verifyScanIsVisible();
        });

        Then("I expect Wallet element is visible", () -> {
           homescreenSteps.verifyWalletIsVisible();
        });

        Then("I expect Search view element is visible", () -> {
            homescreenSteps.verifySearchViewIsVisible();
        });

        Then("I expect Login Now link is visible", () -> {
           homescreenSteps.verifyLoginNowIsVisible();
        });

        Then("I expect Easy login with SMS\\/Facebook\\/Google! is available", () -> {
            homescreenSteps.verifySmsFaceBookGoogleIsVisible();
        });

        Then("I expect Feed element is visible", () -> {
           homescreenSteps.verifyFeedIsVisible();
        });

        Then("I expect Messages element is visible", () -> {
           homescreenSteps.verifyMessagesIsVisible();
        });

        Then("I expect Cart element is visible", () -> {
            homescreenSteps.verifyCartIsVisible();
        });

        Then("I expect Account element is visible", () -> {
            homescreenSteps.verifyAccountIsVisible();
        });

        Then("I expect Enter Search Text element is visible", () -> {
            homescreenSteps.verifySearchInputIsVisible();
        });

        When("I search for {string}", (String item) -> {
            homescreenSteps.searchForAnItem(item);
        });




    }
}
