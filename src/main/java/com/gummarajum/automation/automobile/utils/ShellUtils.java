package com.gummarajum.automation.automobile.utils;

import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
public class ShellUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShellUtils.class);

    public String executeShellCommand(final String command) {
        LOGGER.debug("Executing [{}] command", command);
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("bash", "-c", command);

        try {
            Process process = processBuilder.start();
            StringBuilder output = new StringBuilder();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal != 0) {
                LOGGER.error("Command executed with exit code [{}]", exitVal);
                throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Command executed with exit code [{}]", exitVal);
            }
            return output.toString();
        } catch (IOException | InterruptedException e) {
            LOGGER.error("Exception while executing command [{}]", command);
            throw new MobileException(MobileExceptionType.PROCESSING_FAILED, "Exception while executing command [{}]", command);
        }
    }
}
