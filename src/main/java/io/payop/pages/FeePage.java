package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;

public class FeePage extends BasePage {

    @FindBy(xpath = "//div[@class='mat-tab-labels']/div")
    private List<WebElement> feeTabs;

    @FindBy(xpath = "//button[contains(@class,'search-block__button')]")
    private WebElement searchButton;

    @FindBy(xpath = "(//input[@formcontrolname='merchantPart'])[3]")
    private WebElement feeInputField;

    @FindBy(xpath = "(//button[contains(@class,'fee-submit')])[3]")
    private WebElement saveFeeButton;

    @FindBy(xpath = "//a[@href='/en/profile/projects/list']")
    private WebElement projectsListButton;

    @FindBy(xpath = "//alert-comp")
    private WebElement alertSuccess;

    @FindBy(xpath = "//button[@class='close-alert']")
    private WebElement closeAlertButton;

    public FeePage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(searchButton);
    }

    public FeePage navigateToFeeTab(String tabName){
        WebElement tab = getFeeTab(tabName);
        clickElementUsingJs(tab);
        return this;
    }

    public FeePage setPercentToField(String fee){
        scrollToElement(feeInputField);
        feeInputField.clear();
        feeInputField.sendKeys(fee);
        saveFeeButton.click();
        waitForElementVisible(alertSuccess);
        clickElement(closeAlertButton);
        return this;
    }

    public ProjectsListPage navigateToProjectsListPage(){
        clickElementUsingJs(projectsListButton);
        return new ProjectsListPage(webDriver);
    }

    private WebElement getFeeTab(String tabName){
        waitUntilFunctionIsTrue(func -> feeTabs.stream().anyMatch(tab -> tab.getText().contains(tabName)), "tab not found");
        return feeTabs.stream()
                .filter(tab -> tab.getText().contains(tabName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Tab not found"));
    }

    public FeePage waitForMessageIsPresent(String message) {
        String errorMessage = "Message '" + message + "' did not appear in the chat";

        waitUntilFunctionIsTrue(fun -> {
            LOGGER.info("Last message in chat: '" + feeInputField.getText() + "'.\n");
            return feeInputField.getText().equals(message);
        }, errorMessage);

        return this;
    }
}
