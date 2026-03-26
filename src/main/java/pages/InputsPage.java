package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class InputsPage {

    WebDriver driver;

    public InputsPage(WebDriver driver){
        this.driver = driver;
    }

    //Locators
    By inputsField = By.cssSelector("#content > div > div > div > input[type=number]");

    public void setInputsData(String number){
        driver.findElement(inputsField).clear();
        driver.findElement(inputsField).sendKeys(number);
    }

    public String getInputFieldValue(){
        return driver.findElement(inputsField).getAttribute("value");
    }
}
