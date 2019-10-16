package com.gummarajum.automation.automobile.utils;

import com.google.common.base.Strings;
import com.gummarajum.automation.automobile.MobileException;
import com.gummarajum.automation.automobile.MobileExceptionType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class AdbUtils {


    private static final Logger LOGGER = LoggerFactory.getLogger(AdbUtils.class);

    private String deviceId;

    public void setDeviceId(final String deviceId) {
        this.deviceId = deviceId;
        LOGGER.debug("Setting device id [{}]", deviceId);
    }

    private void validateDeviceIdIsSet() {
        if (Strings.isNullOrEmpty(deviceId)) {
            LOGGER.error("Device Id is not set either in Local or in Aws");
            throw new MobileException(MobileExceptionType.UNDEFINED, "Device Id is not set either in Local or in Aws");
        }
    }

    private String ANDROID_HOME;

    public String getAndroidHome() {
        if (ANDROID_HOME == null) {
            ANDROID_HOME = System.getenv("ANDROID_HOME");
            if (ANDROID_HOME == null) {
                throw new RuntimeException("Failed to find ANDROID_HOME, make sure the environment variable is set");
            }
        }
        return ANDROID_HOME;
    }


    private String runAdbCommand(String command) {
        String output = null;
        try {
            Scanner scanner = new Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\\A");
            if (scanner.hasNext()) {
                output = scanner.next();
            }
            scanner.close();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return output;
    }


    private String constructAdbCommand(String command) {
        validateDeviceIdIsSet();
        if (command.startsWith("adb")) {
            command = command.replace("adb", this.getAndroidHome() + "/platform-tools/adb");
        } else {
            LOGGER.error("This method is designed to run ADB commands only");
            throw new RuntimeException("This method is designed to run ADB commands only");
        }
        LOGGER.debug("Formatted ADB Command: " + command);
        String output = this.runAdbCommand(command);
        if (output == null) {
            return "";
        }
        LOGGER.debug("Output of the ADB Command: " + output.trim());
        return output.trim();
    }

    public void killServer() {
        constructAdbCommand("adb kill-server");
    }

    public void startServer() {
        constructAdbCommand("adb start-server");
    }

    public ArrayList getConnectedDevices() {
        ArrayList devices = new ArrayList();
        String output = constructAdbCommand("adb devices");
        for (String line : output.split("\n")) {
            line = line.trim();
            if (line.endsWith("device")) {
                devices.add(line.replace("device", "").trim());
            }
        }
        return devices;
    }

    public String getForegroundActivity() {
        return constructAdbCommand("adb -s " + deviceId + " shell dumpsys window windows | grep mCurrentFocus");
    }

    public String getAndroidVersionAsString() {
        String output = constructAdbCommand("adb -s " + deviceId + " shell getprop ro.build.version.release");
        if (output.length() == 3) {
            output += ".0";
        }
        return output;
    }

    public int getAndroidVersion() {
        return Integer.parseInt(getAndroidVersionAsString().replaceAll("\\.", ""));
    }

    public ArrayList getInstalledPackages() {
        ArrayList packages = new ArrayList();
        String[] output = constructAdbCommand("adb -s " + deviceId + " shell pm list packages").split("\n");
        for (String packageID : output) {
            packages.add(packageID.replace("package:", "").trim());
        }
        return packages;
    }

    public void openAppsActivity(String packageID, String activityID) {
        constructAdbCommand("adb -s " + deviceId + " shell am start -c api.android.intent.category.LAUNCHER -a api.android.intent.action.MAIN -n " + packageID + "/" + activityID);
    }

    public void clearAppsData(String packageID) {
        constructAdbCommand("adb -s " + deviceId + " shell pm clear " + packageID);
    }

    public void forceStopApp(String packageID) {
        constructAdbCommand("adb -s " + deviceId + " shell am force-stop " + packageID);
    }

    public void installApp(String apkPath) {
        constructAdbCommand("adb -s " + deviceId + " install " + apkPath);
    }

    public void uninstallApp(String packageID) {
        constructAdbCommand("adb -s " + deviceId + " uninstall " + packageID);
    }

    public void clearLogBuffer() {
        constructAdbCommand("adb -s " + deviceId + " shell -c");
    }

    public void pushFile(String source, String target) {
        constructAdbCommand("adb -s " + deviceId + " push " + source + " " + target);
    }

    public void pullFile(String source, String target) {
        constructAdbCommand("adb -s " + deviceId + " pull " + source + " " + target);
    }

    public void deleteFile(String target) {
        constructAdbCommand("adb -s " + deviceId + " shell rm " + target);
    }

    public void moveFile(String source, String target) {
        constructAdbCommand("adb -s " + deviceId + " shell mv " + source + " " + target);
    }

    public void takeScreenshot(String target) {
        constructAdbCommand("adb -s " + deviceId + " shell screencap " + target);
    }

    public void rebootDevice() {
        constructAdbCommand("adb -s " + deviceId + " reboot");
    }

    public String getDeviceModel() {
        return constructAdbCommand("adb -s " + deviceId + " shell getprop ro.product.model");
    }

    public String getDeviceSerialNumber() {
        return constructAdbCommand("adb -s " + deviceId + " shell getprop ro.serialno");
    }

    public String getDeviceCarrier() {
        return constructAdbCommand("adb -s " + deviceId + " shell getprop gsm.operator.alpha");
    }

    public ArrayList getLogcatProcesses() {
        String[] output = constructAdbCommand("adb -s " + deviceId + " shell top -n 1 | grep -i 'logcat'").split("\n");
        ArrayList processes = new ArrayList();
        for (String line : output) {
            processes.add(line.split(" ")[0]);
            processes.removeAll(Arrays.asList("", null));
        }
        return processes;
    }
}
