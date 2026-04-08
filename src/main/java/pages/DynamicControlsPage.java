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
    private final By checkBoxInput = By.id("checkbox");
    private final By message = By.id("message");
    private final By addButton = By.cssSelector("#checkbox-example > button");
    private final By inputRow = By.cssSelector("#input-example > input[type=text]");
    private final By toggleInputButton = By.cssSelector("#input-example > button");

    public void clickRemoveButton(){
        click(removeButton);
    }

    public boolean checkTextMessage(String text){
       return waitForTextToBePresent(message, text);
    }

    public String getTextMessage(){
        return getText(message);
    }

    public boolean checkCheckBoxInputAppearence(){
        return waitForAppearanceElement(checkBoxInput);
    }

    public boolean checkCheckBoxInputDisappearence(){
        return waitForDisappearenceElement(checkBoxInput);
    }

    public void clickAddButton(){
        click(addButton);
    }

    public boolean checkInputRowIsEnable(){
        return waitForElementBecomeEnable(inputRow);
    }

    public boolean checkInputRowIsDisable(){
        return waitForElementBecomeDisable(inputRow);
    }

    public void fillInputRow(String text){
        sendKeys(inputRow, text);
    }

    public String getTextFromInputRow(){
        return getAttribute(inputRow, "value");
    }

    public boolean isEnableButtonShown(){
        return waitForTextToBePresent(toggleInputButton, "Enable");
    }

    public boolean isDisableButtonShown(){
        return waitForTextToBePresent(toggleInputButton, "Disable");
    }

    public void clickEnableButton(){
        click(toggleInputButton);
    }

    public void clickDisableButton(){
        click(toggleInputButton);
    }

}
