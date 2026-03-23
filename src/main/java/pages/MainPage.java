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

    public void click(By locator){
        driver.findElement(locator).click();
    }

    public void clickFormAuthentication(){
        click(formAuthentication);
    }

}

