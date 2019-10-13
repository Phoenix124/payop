package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutPage extends BasePage {

    @FindBy(xpath = "//mat-select")
    private WebElement currencySelectDropdown;

    @FindBy(xpath = "//span[contains(text(),'Cards International')]")
    private WebElement cardsInternationalPaymentMethodTab;

    @FindBy(xpath = "(//div[@class='payment-item']/span)[3]")
    private WebElement paymentItems;

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailField;

    @FindBy(xpath = "(//button)[1]")
    private WebElement confirmButton;

    public CheckoutPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(currencySelectDropdown);
    }

    public CardPageCheckoutPage navigateToPaymentCheckout(String email) {
        clickElementUsingJs(cardsInternationalPaymentMethodTab);
        emailField.sendKeys(email);
        clickElement(confirmButton);
        return new CardPageCheckoutPage(webDriver);
    }
}
