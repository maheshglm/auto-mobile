package com.gummarajum.automation.automobile.lazada.screens.actions;

import com.gummarajum.automation.automobile.Exception;
import com.gummarajum.automation.automobile.ExceptionType;
import com.gummarajum.automation.automobile.lazada.screens.locators.SearchResultsScreenObjects;
import com.gummarajum.automation.automobile.svc.MobileTaskSvc;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Lazy
@Component
public class SearchResultsScreen {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResultsScreen.class);

    @Autowired
    private MobileTaskSvc mobileTaskSvc;

    @Autowired
    private SearchResultsScreenObjects searchResultsScreenObjects;

    @PostConstruct
    public void init() {
        PageFactory.initElements(new AppiumFieldDecorator(mobileTaskSvc.getDriver()), searchResultsScreenObjects);
    }

    public SearchResultsScreen isResultsPageOpened() {
        mobileTaskSvc.isElementVisible(searchResultsScreenObjects.listStyle);
        return this;
    }

    public SearchResultsScreen verifySearchResultsDisplayed() {
        if (searchResultsScreenObjects.results.size() == 0) {
            LOGGER.error("Search Results are not Displayed");
            throw new Exception(ExceptionType.VERIFICATION_FAILED, "Search Results are not Displayed");
        }
        return this;
    }

    public SearchResultsScreen verifySearchResultsWithGivenItemDisplayed(final String item) {
        for (MobileElement element : searchResultsScreenObjects.results) {
            element.getText();
            if (!element.getText().toLowerCase().contains(item.toLowerCase())) {
                LOGGER.error("Relevant search results for item [{}] are not displayed", item);
                throw new Exception(ExceptionType.VERIFICATION_FAILED, "Relevant search results for item [{}] are not displayed", item);
            }
        }
        return this;
    }

}
