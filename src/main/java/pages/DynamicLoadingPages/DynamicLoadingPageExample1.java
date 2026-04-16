package pages.DynamicLoadingPages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DynamicLoadingPageExample1 extends BasePage {

    public DynamicLoadingPageExample1(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By startButton = By.cssSelector("#start > button");
    private final By loadingBar = By.id("loading");
    private final By pageFinishText = By.id("finish");

    public void clickStartButton(){
        click(startButton);
    }

    public boolean waitForLoadingBarIsInvisible(){
      return waitForDisappearenceElement(loadingBar);
    }

    public boolean isFinishTextVisible(String text){
        return waitForTextToBePresent(pageFinishText, text);
    }


}
