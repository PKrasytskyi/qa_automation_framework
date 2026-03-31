package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By userName = By.id("username");
    private final By userPass = By.id("password");
    private final By loginButton = By.cssSelector("#login > button");
    private final By flashMessage = By.id("flash");


    public void enterUsername(String name){
        clearAndType(userName, name);
    }

    public void enterPassword(String pass) {
        clearAndType(userPass, pass);
    }

    public void clickLoginButton(){
        click(loginButton);
    }

    public void login(String userName, String userPassword) {

        enterUsername(userName);
        enterPassword(userPassword);
        clickLoginButton();
    }

    public String getFlashMessage() {
        return getText(flashMessage);
    }

    public boolean isPageOpened() {
        return waitForUrlContains("/login");
    }

    public boolean isUsernameFieldVisible(){
       return waitForVisible(userName).isDisplayed();
    }
}
