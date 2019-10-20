package com.gummarajum.automation.automobile.steps;

import com.google.common.base.Strings;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import com.gummarajum.automation.automobile.svc.StateSvc;
import com.gummarajum.automation.automobile.utils.DateTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConfigSteps {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSteps.class);

    @Autowired
    private StateSvc stateSvc;

    @Autowired
    private DateTimeUtils dateTimeUtils;


    public void assignValueToVariable(final String value, final String variable) {
        String expandValue = stateSvc.expandExpression(value);
        stateSvc.setStringVar(variable, expandValue);
    }

    public void verifyVariableValue(final String variable, final String actualValue) {
        String expectedValue = stateSvc.expandExpression(variable);
        String expandActualValue = stateSvc.expandExpression(actualValue);

        if (!expandActualValue.equals(expectedValue)) {
            LOGGER.error("Verification failed, expected value is [{}], but actual value is [{}]", expectedValue, expandActualValue);
            throw new MobileException(MobileExceptionType.VERIFICATION_FAILED, "Verification failed, expected value is [{}], but actual value is [{}]", expectedValue, expandActualValue);
        }
    }

    public void assignFormattedValueToVariable(final String format, final String targetVariable) {
        if (Strings.isNullOrEmpty(format)) {
            LOGGER.error("Format should not be Null or Empty");
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Format should not be Null or Empty");
        }
        this.assignValueToVariable(dateTimeUtils.getTimeStamp(format), targetVariable);
    }

}
