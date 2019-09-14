package glue.lazada;

import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.lazada.SearchResultsScreenSteps;
import cucumber.api.java8.En;

public class SearchResultsScreenStepDef implements En {

    private SearchResultsScreenSteps steps = (SearchResultsScreenSteps) Bootstrap.getBean(SearchResultsScreenSteps.class);

    public SearchResultsScreenStepDef(){

        Then("I should see search results related to {string}", (String item) -> {
            steps.verifySearchResultsAreDisplayed(item);
        });
    }
}
