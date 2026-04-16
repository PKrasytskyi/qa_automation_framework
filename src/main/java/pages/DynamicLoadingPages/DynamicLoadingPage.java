package pages.DynamicLoadingPages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPage extends BasePage {

    public DynamicLoadingPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By example1 = By.cssSelector("a[href='/dynamic_loading/1']");
    private final By example2 = By.cssSelector("a[href='/dynamic_loading/2']");

    public void openExample1(){
        click(example1);
    }

    public void openExample2(){
        click(example2);
    }
}
