package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage {

    WebDriver driver;

    public SecureAreaPage(WebDriver driver){
        this.driver = driver;
    }

    //Locators
    By flashText = By.id("flash");
    By logoutButton = By.cssSelector("#content > div > a");

    public String getFlashText(){
       return driver.findElement(flashText).getText();
    }

    public void clickLogoutButton(){
        driver.findElement(logoutButton).click();
    }

    public boolean isPageOpened(){
       return driver.getCurrentUrl().contains("secure");
    }

    public boolean isLogoutButtonVisible(){
        return driver.findElement(logoutButton).isDisplayed();
    }
}
