package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CardPageCheckoutPage extends BasePage {

    @FindBy(xpath = "//input[@formcontrolname='pan']")
    private WebElement cardNumberField;

    @FindBy(xpath = "//input[@formcontrolname='expirationDate']")
    private WebElement expirationDateField;

    @FindBy(xpath = "//input[@formcontrolname='cvv']")
    private WebElement cvvNumberField;

    @FindBy(xpath = "//input[@formcontrolname='holderName']")
    private WebElement cardHolderNameField;

    @FindBy(xpath = "//button")
    private WebElement confirmPaymentButton;

    public CardPageCheckoutPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(cardNumberField);
    }

    public CardPageCheckoutPage setCardNumber(String cardNumber) {
        cardNumberField.sendKeys(cardNumber);
        return this;
    }

    public CardPageCheckoutPage setExpirationDate(String expirationDate) {
        expirationDateField.sendKeys(expirationDate);
        return this;
    }

    public CardPageCheckoutPage setCvvNumber(String cvvNumber) {
        cvvNumberField.sendKeys(cvvNumber);
        return this;
    }

    public CardPageCheckoutPage setCardholderName(String cardholderName) {
        cardHolderNameField.sendKeys(cardholderName);
        return this;
    }


    public Confirm3DSPage navigateToConfirm3DSPaymentPage() {
        clickElementUsingJs(confirmPaymentButton);
        return new Confirm3DSPage(webDriver);
    }
}
