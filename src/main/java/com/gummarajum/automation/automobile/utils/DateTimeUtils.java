package com.gummarajum.automation.automobile.utils;

import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.security.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateTimeUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateTimeUtils.class);

    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public String getTimeStamp() {
        return this.getTimeStamp("YMdHmSs");
    }

    public String getTimeStamp(final String format) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
            return LocalDateTime.now().format(formatter);
        } catch (Exception e) {
            LOGGER.error("Exception in formatting LocalDateTime with given format [{}]", format, e);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Exception in formatting LocalDateTime with given format [{}]", format);
        }
    }


}
