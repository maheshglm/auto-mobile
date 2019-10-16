package com.gummarajum.automation.automobile.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class DateTimeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);

    public long currentTimeMillis(){
        return System.currentTimeMillis();
    }



}
