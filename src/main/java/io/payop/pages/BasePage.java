package io.payop.pages;

import io.payop.config.ConfigProperties;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.testng.Assert.fail;

public class BasePage {
    protected static final Logger LOGGER = LoggerFactory.getLogger(BasePage.class);
    protected static final long WAIT_ELEMENT_TIMEOUT_IN_SECONDS = ConfigProperties.implicitlyWaitInSeconds;
    private static final long WAIT_ELEMENT_NOT_EXIST_TIMEOUT_IN_SECONDS = 6;

    protected JavascriptExecutor javascriptExecutor;
    protected WebDriver webDriver;

    public BasePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
        this.webDriver = webDriver;
        this.javascriptExecutor = (JavascriptExecutor) webDriver;
    }

    protected void scrollToElement(WebElement element) {
        javascriptExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void clickElement(WebElement element) {
        waitForElementClickable(element);
        element.click();
    }

    public void clickElementUsingJs(WebElement element){
        javascriptExecutor.executeScript("arguments[0].click();", element);
    }

    protected void waitForElementVisible(WebElement element) {
        getWaiter().until(visibilityOf(element));
    }

    protected void waitForElementVisible(WebElement element, String errorMessage) {
        getWaiter(errorMessage).until(visibilityOf(element));
    }

    protected void waitForElementClickable(WebElement element) {
        getWaiter().until(elementToBeClickable(element));
    }

    protected void waitForPageVisible(WebElement element) {
        String pageName = getClass().getSimpleName();

        try {
            waitForElementVisible(element);
        } catch (Exception e) {
            String errorMessage = pageName + " was not loaded after " + WAIT_ELEMENT_TIMEOUT_IN_SECONDS + " seconds.\n";
            fail(errorMessage);
            e.printStackTrace();
        }
    }

    protected void waitUntilFunctionIsTrue(Function<WebDriver, Object> function) {
        getWaiter().until(function);
    }

    protected void waitUntilFunctionIsTrue(Function<WebDriver, Object> function, String errorMessage) {
        getWaiter(errorMessage).until(function);
    }

    protected void waitForElementVisibleNot(WebElement element) {
        waitUntilFunctionIsTrue(func -> !isElementExist(element));
    }

    protected boolean isElementExist(WebElement element) {
        boolean isElementExist;
        setWaitElementTimeout(WAIT_ELEMENT_NOT_EXIST_TIMEOUT_IN_SECONDS);

        try {
            isElementExist = element.isDisplayed();
        } catch (NoSuchElementException | ElementNotInteractableException e) {
            isElementExist = false;
        }

        setWaitElementTimeout(WAIT_ELEMENT_TIMEOUT_IN_SECONDS);
        return isElementExist;
    }

    protected void waitUntilListNotEmpty(List<WebElement> list) {
        waitUntilFunctionIsTrue(size -> list.size() != 0);
    }

    private void setWaitElementTimeout(long timeoutInSecond) {
        webDriver.manage().timeouts().implicitlyWait(timeoutInSecond, TimeUnit.SECONDS);
    }

    private FluentWait<WebDriver> getWaiter() {
        return new FluentWait<>(webDriver)
                .withTimeout(Duration.ofSeconds(WAIT_ELEMENT_TIMEOUT_IN_SECONDS))
                .ignoring(ElementNotInteractableException.class)
                .ignoring(InvalidElementStateException.class)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class);
    }

    private FluentWait<WebDriver> getWaiter(String errorMessage) {
        return getWaiter()
                .withMessage(errorMessage + "\nWait timeout: " + WAIT_ELEMENT_TIMEOUT_IN_SECONDS + " seconds.\n");
    }
}
