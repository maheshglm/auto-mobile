package com.gummarajum.automation.automobile.steps.lazada;

import com.gummarajum.automation.automobile.lazada.screens.actions.SearchResultsScreen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class SearchResultsScreenSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchResultsScreenSteps.class);

    @Lazy @Autowired
    private SearchResultsScreen searchResultsScreen;

    public void verifySearchResultsAreDisplayed(String item) {
        searchResultsScreen.isResultsPageOpened()
                .verifySearchResultsDisplayed()
                .verifySearchResultsWithGivenItemDisplayed(item);

    }


}
