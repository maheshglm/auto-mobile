package glue;

import com.gummarajum.automation.automobile.Bootstrap;
import com.gummarajum.automation.automobile.steps.ConfigSteps;
import com.gummarajum.automation.automobile.utils.DateTimeUtils;
import cucumber.api.java8.En;

public class ConfigStepsDef implements En {

    private ConfigSteps steps = (ConfigSteps) Bootstrap.getBean(ConfigSteps.class);

    public ConfigStepsDef() {

        When("I assign {string} to a variable {string}", (String value, String variable) -> {
            steps.assignValueToVariable(value, variable);
        });

        When("I assign below value to a variable {string}", (String variable, String multilineValue) -> {
            steps.assignValueToVariable(multilineValue, variable);
        });

        When("I expect the value of variable {string} equals to {string}", (String variable, String expectedValue) -> {
            steps.verifyVariableValue(variable, expectedValue);
        });

        When("I generate value with date format {string} and assign to variable {string}", (String dateFormat, String targetVariable) -> {
            steps.assignFormattedValueToVariable(dateFormat, targetVariable);
        });

        When("I pause for {int} seconds", (Integer seconds) -> {
            steps.sleep(seconds);
        });
    }
}
