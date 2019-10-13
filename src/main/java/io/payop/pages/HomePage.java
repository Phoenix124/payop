package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='account-info__email']")
    private WebElement accountInfoLabel;

    @FindBy(xpath = "//a[@href='/en/profile/payment-methods']")
    private WebElement paymentMethodsButton;

    @FindBy(xpath = "//p[contains(text(),'Fees')]/..")
    private WebElement feeButton;

    public HomePage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(accountInfoLabel);
        waitForElementClickable(paymentMethodsButton);
    }

    public HomePage navigateToPaymentMethods(){
        clickElementUsingJs(paymentMethodsButton);
        return this;
    }

    public FeePage navigateToFeePage(){
        clickElementUsingJs(feeButton);
        return new FeePage(webDriver);
    }
}
