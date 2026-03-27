package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class SecureAreaPage {

    WebDriver driver;

    public SecureAreaPage(WebDriver driver){
        this.driver = driver;
    }

    //Locators
    By flashText = By.id("flash");
    By logoutButton = By.cssSelector("a[href='/logout']");

    public String getFlashText(){
       return WaitUtils.waitForVisible(driver, flashText).getText();
    }

    public void clickLogoutButton(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", WaitUtils.waitForClickable(driver, logoutButton));
    }

    public boolean isPageOpened(){
       return driver.getCurrentUrl().contains("secure");
    }

    public boolean isLogoutButtonVisible(){
        return WaitUtils.waitForVisible(driver, logoutButton).isDisplayed();
    }
}
