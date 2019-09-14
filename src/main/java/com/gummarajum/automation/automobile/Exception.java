package com.gummarajum.automation.automobile;

import org.slf4j.helpers.MessageFormatter;

public class Exception extends RuntimeException {
    private final ExceptionType exceptionType;

    public Exception(ExceptionType exceptionType, String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage());
        this.exceptionType = exceptionType;
    }

    public Exception(Throwable throwable, ExceptionType exceptionType, String message, Object... args) {
        super(MessageFormatter.arrayFormat(message, args).getMessage(), throwable);
        this.exceptionType = exceptionType;
    }

    public Exception(ExceptionType exceptionType, Throwable throwable) {
        super(throwable);
        this.exceptionType = exceptionType;
    }

    public ExceptionType getExceptionType() {
        return exceptionType;
    }
}
