package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {

    WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    //Locators
    By userName = By.id("username");
    By userPass = By.id("password");
    By loginButton = By.cssSelector("#login > button");
    By flashText = By.id("flash");

    public void setUserName(String name){
       WaitUtils.waitForVisible(driver, userName).sendKeys(name);
    }

    public void setUserPass(String pass){
        WaitUtils.waitForVisible(driver, userPass).sendKeys(pass);
    }

    public void login(String name, String pass){
        WaitUtils.waitForVisible(driver, userName).clear();
        WaitUtils.waitForVisible(driver, userName).sendKeys(name);
        WaitUtils.waitForVisible(driver, userPass).clear();
        WaitUtils.waitForVisible(driver, userPass).sendKeys(pass);
        WaitUtils.waitForClickable(driver, loginButton).click();

    }

    public void clickLoginButton(){
        WaitUtils.waitForClickable(driver, loginButton).click();
    }

    public String getFlashText(){
        return WaitUtils.waitForVisible(driver, flashText).getText();
    }

    public boolean isPageOpened(){
      return  driver.getCurrentUrl().contains("/login");
    }
}
