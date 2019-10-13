package io.payop.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProjectsListPage extends BasePage {

    @FindBy(xpath = "//button[contains(@class,'add-new')]")
    private WebElement addNewButton;

    @FindBy(xpath = "//a[@href='/en/profile/projects/rest']")
    private WebElement restButton;

    public ProjectsListPage(WebDriver webDriver) {
        super(webDriver);
        waitForPageVisible(addNewButton);
    }

    public RestPage navigateToRestPage(){
        clickElementUsingJs(restButton);
        return new RestPage(webDriver);
    }
}
