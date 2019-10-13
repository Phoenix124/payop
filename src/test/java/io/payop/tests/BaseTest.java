package io.payop.tests;

import io.payop.config.WebDriverConfig;
import io.payop.config.WebDriverFactory;
import io.payop.dataprovider.DtoController;
import io.payop.dataprovider.UserDto;
import io.payop.dataprovider.UsersDto;
import io.payop.pages.HomePage;
import io.payop.pages.LoginPage;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.lang.reflect.Method;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class BaseTest {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    private WebDriver webDriver;
    protected UserDto userDto;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        userDto = getDefaultUser();
    }

    @BeforeMethod(alwaysRun = true)
    public void createDriver() {
        webDriver = WebDriverFactory.getInstance().getDriver();
    }

    @AfterMethod(alwaysRun = true)
    public void afterWeb(Method method, ITestResult result) {
        LOGGER.info("AfterWeb start. Test: " + method.getName());
        attachScreenshotAndFiles(result);
        stopWebDriver();
    }

    protected HomePage loginToPayop() {
        return loginToPayop(webDriver, userDto.email, userDto.password);
    }

    protected HomePage loginToPayop(WebDriver webDriver, String username, String password) {
        webDriver.navigate().to(WebDriverConfig.adminPanelUrl);
        LoginPage loginPage = new LoginPage(webDriver);

        return loginPage.login(username, password);
    }

    private UserDto getDefaultUser() {
        return getCorrectUsers()
                .stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("No User found"));
    }

    protected List<UserDto> getCorrectUsers() {
        return new DtoController<>(UsersDto.class)
                .getDataFromFile("users-correct.xml")
                .usersDto;
    }

    private String attachFile(String attachName, String message) {
        return Optional.ofNullable(message).orElse(null);
    }

    private synchronized byte[] attachScreenshot(String screenshotName) {
        LOGGER.info("AttachScreenshot method start");

        if (webDriver == null) {
            LOGGER.info("Driver is null");
            return null;
        }

        webDriver.manage().window().fullscreen();

        return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
    }

    private void attachScreenshotAndFiles(ITestResult result) {
        try {
            attachScreenshot("screenshot_web.png");
        } catch (Exception e) {
            LOGGER.error("Error while attach screenshot");
            e.printStackTrace();
        }

        try {
            if (result != null && !result.isSuccess() && webDriver != null) {
                Optional
                        .ofNullable(result.getThrowable())
                        .ifPresent(error -> attachFile("error_web.txt", error.getMessage()));

                attachFile("page-source_web.txt", webDriver.getPageSource());
            }
        } catch (Exception e) {
            LOGGER.error("Error while attach files");
            e.printStackTrace();
        }
    }

    private void stopWebDriver() {
        try {
            Optional.ofNullable(webDriver).ifPresent(func -> webDriver.quit());
        } catch (Exception e) {
            LOGGER.error("Error closing session or session not exist");
            e.printStackTrace();
        }
    }
}
