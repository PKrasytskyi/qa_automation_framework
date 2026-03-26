package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    //Locators
    By formAuthentication = By.cssSelector("#content > ul > li:nth-child(21) > a");
    By checkboxes = By.cssSelector("#content > ul > li:nth-child(6) > a");
    By inputsPage = By.cssSelector("#content > ul > li:nth-child(27) > a");
    By dropDownPage = By.cssSelector("#content > ul > li:nth-child(11) > a");

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void clickFormAuthentication(){
        click(formAuthentication);
    }

    public void clickCheckboxes(){
        click(checkboxes);
    }

    public void openInputsPage(){
        click(inputsPage);
    }

    public void openDropDownPage(){
        click(dropDownPage);
    }

}

