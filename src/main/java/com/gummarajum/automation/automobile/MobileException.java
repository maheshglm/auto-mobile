package com.gummarajum.automation.automobile;

import org.slf4j.helpers.MessageFormatter;

public class MobileException extends RuntimeException {

    private final MobileExceptionType mobileExceptionType;

    public MobileException(MobileExceptionType mobileExceptionType, String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
        this.mobileExceptionType = mobileExceptionType;
    }

    public MobileException(Throwable throwable, MobileExceptionType mobileExceptionType, String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage(), throwable);
        this.mobileExceptionType = mobileExceptionType;
    }

    public MobileException(MobileExceptionType mobileExceptionType, Throwable throwable) {
        super(throwable);
        this.mobileExceptionType = mobileExceptionType;
    }

    public MobileExceptionType getMobileExceptionType() {
        return mobileExceptionType;
    }
}
