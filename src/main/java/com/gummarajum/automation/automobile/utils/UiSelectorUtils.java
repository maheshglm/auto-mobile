package com.gummarajum.automation.automobile.utils;

import com.constants.UISELECTORS;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class UiSelectorUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(UiSelectorUtils.class);

    private String locator = "new UiSelector()";

    public String getLocator() {
        String locator = this.locator;
        this.locator = "new UiSelector()";
        return locator;
    }

    public UiSelectorUtils constructUiSelector(final LinkedHashMap<UISELECTORS, String> selectorIdentifiersMap) {
        UiSelectorUtils result = new UiSelectorUtils();
        for (Map.Entry<UISELECTORS, String> entry : selectorIdentifiersMap.entrySet()) {
            result = result.constructUiSelector(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public UiSelectorUtils constructUiSelector(UISELECTORS selectors, String identifier) {
        switch (selectors) {
            case RESOURCE_ID:
                return this.resourceId(identifier);
            case XPATH:
                return this.xPath(identifier);
            case CLASSNAME:
                return this.className(identifier);
            case CLASSNAME_MATCHES:
                return this.classNameMatches(identifier);
            case TEXT:
                return this.text(identifier);
            case TEXT_CONTAINS:
                return this.textContains(identifier);
            case INDEX:
                return this.index(Integer.valueOf(identifier));
            case CLICKABLE:
                return this.clickable(Boolean.valueOf(identifier));
            case CHECKED:
                return this.checked(Boolean.valueOf(identifier));
            case ENABLED:
                return this.enabled(Boolean.valueOf(identifier));
            case DESCRIPTION:
                return this.description(identifier);
            case DESCRIPTION_CONTAINS:
                return this.descriptionContains(identifier);
            case DESCRIPTION_MATCHES:
                return this.descriptionMatches(identifier);
            default:
                LOGGER.error("Invalid UiSelector [{}] chosen", selectors.name());
                throw new MobileException(MobileExceptionType.UNDEFINED, "Invalid UiSelector [{}] chosen", selectors.name());

        }
    }


    private UiSelectorUtils resourceId(String value) {
        locator += ".resourceId(\"" + value + "\")";
        return this;
    }

    private UiSelectorUtils xPath(String xPath) {
        locator = xPath;
        return this;
    }

    private UiSelectorUtils className(String value) {
        locator += ".className(\"" + value + "\")";
        return this;
    }

    private UiSelectorUtils classNameMatches(String regex) {
        locator += ".classNameMatches(\"" + regex + "\")";
        return this;
    }

    private UiSelectorUtils text(String value) {
        locator += ".text(\"" + value + "\")";
        return this;
    }

    private UiSelectorUtils textContains(String value) {
        locator += ".textContains(\"" + value + "\")";
        return this;
    }

    private UiSelectorUtils index(int value) {
        locator += ".index(" + value + ")";
        return this;
    }

    private UiSelectorUtils clickable(boolean value) {
        locator += ".clickable(" + value + ")";
        return this;
    }

    private UiSelectorUtils checked(boolean value) {
        locator += ".checked(" + value + ")";
        return this;
    }

    private UiSelectorUtils enabled(boolean value) {
        locator += ".enabled(" + value + ")";
        return this;
    }

    private UiSelectorUtils description(String value) {
        locator += ".description(\"" + value + "\")";
        return this;
    }

    private UiSelectorUtils descriptionContains(String value) {
        locator += ".descriptionContains(\"" + value + "\")";
        return this;
    }

    private UiSelectorUtils descriptionMatches(String regex) {
        locator += ".descriptionMatches(\"" + regex + "\")";
        return this;
    }
}
