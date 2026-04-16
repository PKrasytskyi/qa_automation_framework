package pages.DynamicLoadingPages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPageExample2 extends BasePage {

    public DynamicLoadingPageExample2(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By startButton = By.cssSelector("#start > button");
    private final By loadingBar = By.id("loading");
    private final By finishText = By.id("finish");

    public void clickStartButton(){
        click(startButton);
    }

    public boolean isLoadingBarInvisible(){
        return waitForDisappearenceElement(loadingBar);
    }

    public boolean isFinishTextVisible(String text){
       return waitForTextToBePresent(finishText, text);
    }
}
