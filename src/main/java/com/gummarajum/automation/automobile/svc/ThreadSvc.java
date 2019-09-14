package com.gummarajum.automation.automobile.svc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ThreadSvc {

    public static final Logger LOGGER = LoggerFactory.getLogger(ThreadSvc.class);

    public void sleepMillis(long millis){
        LOGGER.debug("sleepmillis [{}]",millis);
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            //
        }
    }

    public void sleepSeconds(int seconds){
        sleepMillis(seconds * 1000L);
    }


}
