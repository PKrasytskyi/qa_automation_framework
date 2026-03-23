package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
       driver.findElement(userName).sendKeys(name);
    }

    public void setUserPass(String pass){
        driver.findElement(userPass).sendKeys(pass);
    }

    public void login(String name, String pass){
        driver.findElement(userName).sendKeys(name);
        driver.findElement(userPass).sendKeys(pass);
        driver.findElement(loginButton).click();

    }

    public void clickLoginButton(){
        driver.findElement(loginButton).click();
    }

    public String getFlashText(){
        return driver.findElement(flashText).getText();
    }

    public boolean isPageOpened(){
      return  driver.getCurrentUrl().contains("/login");
    }
}
