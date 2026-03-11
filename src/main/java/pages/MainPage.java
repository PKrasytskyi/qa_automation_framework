package pages;

import org.openqa.selenium.WebDriver;

public class MainPage {

    private final WebDriver driver;

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public String getHeader(){
       return driver.getTitle();
    }
}

