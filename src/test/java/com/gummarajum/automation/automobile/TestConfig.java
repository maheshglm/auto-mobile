package com.gummarajum.automation.automobile;

import org.apache.log4j.xml.DOMConfigurator;

import java.net.MalformedURLException;
import java.net.URL;

public class TestConfig {

    /**
     * Configure Log4J logging framework using default config file
     */
    public static void configureLogging(Class clazz){
        final String absolutePath = "target/classes/log4j.xml";
        System.out.println(absolutePath);
        try {
            DOMConfigurator.configure(new URL("file:" + absolutePath));
        } catch (MalformedURLException e) {
            System.out.println("FATAL: failed to configure Logging.");
            System.exit(1);
        }
    }
}
