package io.payop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class TransactionsPage extends BasePage {

    @FindBy(xpath = "//transaction-list")
    private WebElement transactionPage;

    @FindBy(xpath = "//tbody//tr")
    private List<WebElement> transactions;

    public TransactionsPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(transactionPage);
    }

    public String getSumOfTransaction() {
        WebElement lastTransaction = getTransaction();
        return lastTransaction.findElement(By.xpath("//span[contains(text(),'USD')]")).getText();
    }

    private WebElement getTransaction() {
        return transactions.stream()
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Transactions not found"));
    }
}
