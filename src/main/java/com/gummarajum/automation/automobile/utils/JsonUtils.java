package com.gummarajum.automation.automobile.utils;

import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JsonUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    @SuppressWarnings("unchecked")
    public Map<String, Object> readJsonStringIntoMap(final String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, Map.class);
        } catch (IOException e) {
            LOGGER.error("MobileException while converting Json to Map");
            throw new MobileException(MobileExceptionType.IO_ERROR,"MobileException while converting Json to Map");
        }
    }
}
