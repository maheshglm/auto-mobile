package com.gummarajum.automation.automobile.lazada.screens.locators;

import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.support.CacheLookup;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchResultsScreenObjects {

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/liststyle_button")
    public MobileElement listStyle;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/filter_image")
    public MobileElement filterImage;

    @CacheLookup
    @AndroidFindBy(id = "com.lazada.android:id/product_name_text_view")
    public List<MobileElement> results;
}
