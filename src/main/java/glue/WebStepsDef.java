package glue;

import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.WebSteps;
import cucumber.api.java8.En;

public class WebStepsDef implements En {

    private WebSteps webSteps = (WebSteps) Bootstrap.getBean(WebSteps.class);

    public WebStepsDef() {

        Given("I navigate to url {string}", (String url) -> {
            webSteps.launchUrl(url);
        });


    }
}
