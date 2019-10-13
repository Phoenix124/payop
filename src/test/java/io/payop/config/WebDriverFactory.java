package io.payop.config;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.payop.enums.ProvidedBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.fail;

public class WebDriverFactory extends TestConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);

    private volatile static WebDriverFactory webFactoryInstance = null;

    public static WebDriverFactory getInstance() {
        if (webFactoryInstance == null) {
            synchronized (WebDriverFactory.class) {
                webFactoryInstance = new WebDriverFactory();
            }
        }
        return webFactoryInstance;
    }

    public WebDriver getDriver() {
        WebDriver webDriver = null;
        ProvidedBrowser browserName = ProvidedBrowser.valueOf(properties.getProperty("WEBDRIVER"));

        switch (browserName) {
            case CHROME:
                WebDriverManager.chromedriver().setup();
                LOGGER.info("Start Chrome browser");
                webDriver = new ChromeDriver(WebDriverConfig.prepareChrome());
                break;
            case SAFARI:
                LOGGER.info("Start Safari browser");
                webDriver = new SafariDriver(WebDriverConfig.prepareSafari());
                break;
            case FIREFOX:
                WebDriverManager.firefoxdriver().setup();
                LOGGER.info("Start Firefox browser");
                webDriver = new FirefoxDriver(WebDriverConfig.prepareFirefox());
                break;
            default:
                fail("No browser name is specified");
        }

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(implicitlyWaitInSeconds, TimeUnit.SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(implicitlyWaitInSeconds, TimeUnit.SECONDS);
        webDriver.manage().timeouts().setScriptTimeout(implicitlyWaitInSeconds, TimeUnit.SECONDS);

        return webDriver;
    }
}
