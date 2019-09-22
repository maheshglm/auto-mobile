package com.gummarajum.automation.automobile.steps;

import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import com.gummarajum.automation.automobile.svc.StateSvc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WebSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSteps.class);

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private StateSvc stateSvc;

    public void launchUrl(final String url) {
        String expandedUrl = stateSvc.expandExpression(url);
        mobileTaskSvc.launchBrowser(expandedUrl);
    }
}
