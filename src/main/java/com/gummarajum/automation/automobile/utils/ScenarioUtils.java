package com.gummarajum.automation.automobile.utils;

import cucumber.api.Scenario;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ScenarioUtils {

    private InheritableThreadLocal<Scenario> scenario = new InheritableThreadLocal<>();

    public Scenario getScenario() {
        return scenario.get();
    }

    public void setScenario(Scenario scenario) {
        this.scenario.set(scenario);
    }

    public String getFeatureName() {
        String rawFeatureName = scenario.get().getId().split(";")[0];
        return "Feature " + rawFeatureName.substring(0, 1).toUpperCase() + rawFeatureName.substring(1);
    }

    public void write(final String data) {
        try {
            if (getScenario() != null) {
                getScenario().write(data);
            }
        } catch (Exception e) {
            //ignore
        }
    }

    public void embed(final byte[] bytes, final String var) {
        if (getScenario() != null) {
            getScenario().embed(bytes, var);
        }
    }

    public ArrayList<String> getTagNames() {
        if (this.getScenario() != null) {
            return (ArrayList<String>) this.getScenario().getSourceTagNames();
        }
        return new ArrayList<>();
    }

    public boolean isTagPresent(final String tagName) {
        return getTagNames().contains(tagName);
    }

    public boolean isScenarioFailed() {
        return this.getScenario() != null && this.getScenario().isFailed();
    }

    public String getScenarioName() {
        if (this.getScenario() != null) {
            return this.getScenario().getName();
        }
        return "";
    }


}
