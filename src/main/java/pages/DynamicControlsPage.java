package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicControlsPage extends BasePage {

    public DynamicControlsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By removeButton = By.cssSelector("#checkbox-example > button");
    private final By checkBoxInput = By.cssSelector("#checkbox > input[type=checkbox]");
    private final By message = By.id("message");
    private final By addButton = By.cssSelector("#checkbox-example > button");
    private final By inputRow = By.cssSelector("#input-example > input[type=text]");
    private final By enableButton = By.cssSelector("#input-example > button");
    private final By disableButton = By.cssSelector("#input-example > button");
}
