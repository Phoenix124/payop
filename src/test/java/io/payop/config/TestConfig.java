package io.payop.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class TestConfig {

    public static final Properties properties = ConfigProperties.properties;
    public static final Logger LOGGER = LoggerFactory.getLogger(TestConfig.class);

    public static int implicitlyWaitInSeconds = ConfigProperties.implicitlyWaitInSeconds;
    public static int waitDeviceTimeoutInSeconds = ConfigProperties.waitDeviceTimeoutInSeconds;
    public static int waitDeviceTimeoutInMillis = waitDeviceTimeoutInSeconds * 1000;

}
