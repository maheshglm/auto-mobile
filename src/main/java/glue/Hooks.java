package glue;

import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.HooksSteps;
import cucumber.api.Scenario;
import cucumber.api.java8.En;

public class Hooks implements En {

    private HooksSteps hooksSteps = (HooksSteps) Bootstrap.getBean(HooksSteps.class);

    public Hooks() {

        After(0, () -> hooksSteps.closeApp());
        Before(0, () -> hooksSteps.setDriver());
        Before(1, (Scenario scenario) -> hooksSteps.setScenario(scenario));

    }


}
