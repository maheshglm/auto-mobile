package com.gummarajum.automation.automobile.svc;

import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.utils.FileDirUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatePropertiesSvc {

    private static Logger LOGGER = LoggerFactory.getLogger(StatePropertiesSvc.class);

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private FileDirUtils fileDirUtils;

    private Map<String, String> globalPropsMap = null;

    public synchronized void loadProperties() {
        File file = fileDirUtils.getFileFromResources("framework.properties");
        LOGGER.debug("loading properties from [{}]", file.getAbsolutePath());
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(file.getAbsolutePath()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("properties read:[{}]", Objects.toString(props));
            }
            globalPropsMap = new HashMap<>();
            for (String key : props.stringPropertyNames()) {
                globalPropsMap.put(key, props.getProperty(key));
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("globalPropsMap:[{}]", Objects.toString(globalPropsMap));
            }
        } catch (IOException e) {
            LOGGER.error("failed while loading properties file [{}]", file.getAbsolutePath(), e);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "failed while loading properties file [{}]", file.getAbsolutePath());
        }
    }

    public void populateGlobalPropsMap() {
        if (globalPropsMap == null) {
            LOGGER.debug("loadproperties(): first time invocation.");
            this.loadProperties();
        }
    }

    public String getGlobalPropsMap(String name) {
        String result;
        if (globalPropsMap.containsKey(name)) {
            result = globalPropsMap.get(name);
        } else {
            LOGGER.debug("[{}] not found in GlobalPropsMap", name);
            result = "";
        }
        return result;
    }

    public Map<String, String> getGlobalPropsMapFromPrefix(String prefix, boolean expandVars) {
        populateGlobalPropsMap();
        List<String> keys = globalPropsMap.keySet().stream().filter(val -> val.startsWith(prefix)).collect(Collectors.toList());
        Map<String, String> result = new HashMap<>();
        int prefixLen = prefix.length();
        for (String key : keys) {
            String shortenedKey = key.substring(prefixLen + 1);
            String value = globalPropsMap.get(key);
            result.put(shortenedKey, expandVars ? stateSvc.expandExpression(value) : value);
        }
        return result;
    }


}
