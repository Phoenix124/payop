package io.payop.config;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.safari.SafariOptions;

import java.io.File;

public class WebDriverConfig extends TestConfig {

    public static final String adminPanelUrl = properties.getProperty("LOGIN_URL");

    private static final File classpathRoot = new File(System.getProperty("user.dir"));

    static ChromeOptions prepareChrome() {
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("useAutomationExtension", false);

        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability("showChromedriverLog ", true);
        options.setCapability("setWebContentsDebuggingEnabled", true);
        options.setCapability("recreateChromeDriverSessions", true);
        options.setCapability("pageLoadStrategy", "normal");

        options.addArguments("test-type");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        return options;
    }

    static SafariOptions prepareSafari() {
        SafariOptions options = new SafariOptions();
        options.setAutomaticInspection(true);
        options.setCapability("safari.cleanSession", true);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        return options;
    }

    static FirefoxOptions prepareFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setLogLevel(FirefoxDriverLogLevel.INFO);
        return options;
    }
}
