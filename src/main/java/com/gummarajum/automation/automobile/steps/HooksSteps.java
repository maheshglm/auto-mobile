package com.gummarajum.automation.automobile.steps;

import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.utils.ScenarioUtils;
import cucumber.api.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HooksSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(HooksSteps.class);

    @Autowired
    private ScenarioUtils scenarioUtils;

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    public void setScenario(Scenario scenario) {
        scenarioUtils.setScenario(scenario);
    }

    public void setDriver(){
        mobileTaskSvc.getDriver();
    }

    public void quitDriver(){
        mobileTaskSvc.quitDriver();
    }

    public void closeApp(){
        mobileTaskSvc.closeApp();
    }
}
