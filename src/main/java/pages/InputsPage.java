package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputsPage extends BasePage{

    public InputsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By inputsField = By.cssSelector("#content > div > div > div > input[type=number]");

    public void enterInputData(String number){
        clearAndType(inputsField, number);
    }

    public String getInputFieldValue() {
        return getAttribute(inputsField, "value");
    }

    public void refreshPage(){
        driver.navigate().refresh();
    }
}
