package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage extends BasePage {

    public JavaScriptAlertsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By jsAlert = By.cssSelector("#content > div > ul > li:nth-child(1) > button");
    private final By jsConfirm = By.cssSelector("#content > div > ul > li:nth-child(2) > button");
    private final By jsPrompt = By.cssSelector("#content > div > ul > li:nth-child(3) > button");
    private final By result = By.id("result");

    public void openJsAlert(){
        click(jsAlert);
    }

    public void openJsConfirm(){
        click(jsConfirm);
    }

    public void openJsPrompt(){
        click(jsPrompt);
    }

    public String getResult(){
        return findElement(result).getText();
    }

    public String getJsAlertText(){
        return getAlertText();
    }

    public void confirmJsAlert(){
        acceptAlert();
    }

    public void dismissJsAlert(){
        dismissAlert();
    }

    public void enterJsAlertText(String text){
        enterAlertText(text);
    }
}
