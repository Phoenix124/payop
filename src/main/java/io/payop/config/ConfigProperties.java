package io.payop.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.testng.Assert.fail;

public class ConfigProperties {

    public static Properties properties;
    public static boolean isNativeKey;
    public static int implicitlyWaitInSeconds;
    public static int waitDeviceTimeoutInSeconds;

    static {
        File globalConfigFile = new File("src/main/resources/config.properties");
        File localConfigFile = new File("src/main/resources/local.properties");

        Properties globalProperties = new Properties();
        Properties localProperties = new Properties();

        try {
            globalProperties.load(new FileInputStream(globalConfigFile));

            properties = new Properties();
            properties.putAll(globalProperties);

            if (localConfigFile.exists()) {
                localProperties.load(new FileInputStream(localConfigFile));
                properties.putAll(localProperties);
            }

        } catch (IOException e) {
            fail("Error open config file.\n" + e.getMessage());
        }

        isNativeKey = Boolean.parseBoolean(properties.getProperty("IS_NATIVE_KEY"));

        implicitlyWaitInSeconds = Integer.valueOf(properties.getProperty("IMPLICITLY_WAIT_IN_SECONDS"));
        waitDeviceTimeoutInSeconds = Integer.valueOf(properties.getProperty("WAIT_DEVICE_TIMEOUT_IN_SECONDS"));
    }
}
