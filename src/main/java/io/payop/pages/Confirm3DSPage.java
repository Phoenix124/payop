package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Confirm3DSPage extends BasePage {

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement confirmButton;

    public Confirm3DSPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(confirmButton);
    }

    public RestPage connfirm3DSPayment(){
        clickElementUsingJs(confirmButton);
        return new RestPage(webDriver);
    }
}
