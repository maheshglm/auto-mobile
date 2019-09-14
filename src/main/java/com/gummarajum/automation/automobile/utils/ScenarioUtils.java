package com.gummarajum.automation.automobile.utils;

import cucumber.api.Scenario;
import org.springframework.stereotype.Component;

@Component
public class ScenarioUtils {

    private InheritableThreadLocal<Scenario> scenario = new InheritableThreadLocal<>();

    public Scenario getScenario(){
        return scenario.get();
    }

    public void setScenario(Scenario scenario){
        this.scenario.set(scenario);
    }

    public void write(final String data){
        if(getScenario() != null){
            getScenario().write(data);
        }
    }

    public void embed(final byte[] bytes, final String var){
        if(getScenario() != null){
            getScenario().embed(bytes,var);
        }
    }
}
