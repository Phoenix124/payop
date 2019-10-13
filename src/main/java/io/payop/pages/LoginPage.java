package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "email")
    private WebElement emailField;

    @FindBy(id = "password")
    private WebElement passwordField;

    @FindBy(xpath = "//button")
    private WebElement loginButton;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(loginButton);
    }

    public HomePage login(String username, String password) {
        emailField.sendKeys(username);
        passwordField.sendKeys(password);
        clickElementUsingJs(loginButton);
        return new HomePage(webDriver);
    }
}
