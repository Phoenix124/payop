package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentMethodsPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class,'add-new')]")
    private WebElement addNewButton;

    @FindBy(xpath = "//payment-methods")
    private WebElement paymentMethodsPage;

    @FindBy(xpath = "//a[@href='/en/profile/transaction']")
    private WebElement transactionButton;

    @FindBy(xpath = "//a[@href='/en/profile/projects/rest']")
    private WebElement restButton;

    @FindBy(xpath = "//a[@href='/en/profile/projects']")
    private WebElement projectListButton;

    @FindBy(xpath = "//p[contains(text(),'Fees')]/..")
    private WebElement feeButton;

    public PaymentMethodsPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(paymentMethodsPage);
    }

    public RestPage navigateToRestPage(){
        clickElementUsingJs(projectListButton);
        clickElementUsingJs(restButton);
        return new RestPage(webDriver);
    }

    public FeePage navigateToFeePage(){
        clickElementUsingJs(feeButton);
        return new FeePage(webDriver);
    }

    public TransactionsPage navigateToTransactionsPage(){
        clickElementUsingJs(transactionButton);
        return new TransactionsPage(webDriver);
    }
}
