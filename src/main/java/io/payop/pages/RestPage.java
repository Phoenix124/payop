package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RestPage extends BasePage {

    @FindBy(xpath = "//mat-select")
    private WebElement selectDropdownList;

    @FindBy(xpath = "//input[@formcontrolname='amount']")
    private WebElement amountInputField;

    @FindBy(xpath = "//input[@formcontrolname='currency']")
    private WebElement currencyInputField;

    @FindBy(xpath = "//input[@formcontrolname='id']")
    private WebElement orderIdInputField;

    @FindBy(xpath = "//input[@formcontrolname='resultUrl']")
    private WebElement resultUrlInputField;

    @FindBy(xpath = "//input[@formcontrolname='failPath']")
    private WebElement failPathInputField;

    @FindBy(xpath = "//span[contains(text(),'Generate config')]/..")
    private WebElement generateConfigButton;

    @FindBy(xpath = "//span[contains(text(),'Show payment page ')]/..")
    private WebElement showPaymentPageButton;

    public RestPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(selectDropdownList);
    }

    public RestPage setAmount(String amount) {
        amountInputField.sendKeys(amount);
        return this;
    }

    public RestPage setCurrency(String currency) {
        currencyInputField.sendKeys(currency);
        return this;
    }

    public RestPage setOrderId(String orderId) {
        orderIdInputField.sendKeys(orderId);
        return this;
    }

    public RestPage setResultUrl(String resultUrl) {
        resultUrlInputField.sendKeys(resultUrl);
        return this;
    }

    public RestPage setFailPath(String failPath) {
        failPathInputField.sendKeys(failPath);
        return this;
    }

    public RestPage generateConfig() {
        clickElement(generateConfigButton);
        return this;
    }

    public CheckoutPage showPaymentPage() {
        scrollToElement(showPaymentPageButton);
        clickElement(showPaymentPageButton);
        return new CheckoutPage(webDriver);
    }
}
