package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    //Locators
    By formAuthentication = By.cssSelector("a[href='/login']");
    By checkboxes = By.cssSelector("a[href='/checkboxes']");
    By inputsPage = By.cssSelector("a[href='/inputs']");
    By dropDownPage = By.cssSelector("a[href='/dropdown']");
    By addRemoveElements = By.cssSelector("a[href='/add_remove_elements/']");

    public void click(By locator){
        WaitUtils.waitForClickable(driver, locator).click();
    }

    public void openFormAuthentication(){
        click(formAuthentication);
    }

    public void openCheckboxes(){
        click(checkboxes);
    }

    public void openInputsPage(){
        click(inputsPage);
    }

    public void openDropDownPage(){
        click(dropDownPage);
    }

    public void openAddRemoveElementsPage(){ click(addRemoveElements);}

}

