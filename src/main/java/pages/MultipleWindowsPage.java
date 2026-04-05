package pages;

import core.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MultipleWindowsPage extends BasePage {

    public MultipleWindowsPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By clickHereLink = By.cssSelector("a[href='/windows/new']");
    private final By newPageText = By.cssSelector("body > div > h3");
    private final By originalPageText = By.xpath("//*[@id='content']//h3");

    private void openNewWindow(){
        click(clickHereLink);
    }

    private String currentPageHandle(){
        return driver.getWindowHandle();
    }

    public void switchToAnotherWindow(){

        openNewWindow();
        switchToAnotherWindow(currentPageHandle());

    }
    public String getNewPageText(){
        return getText(newPageText);
    }

    public String getOriginalPageText(){
        return getText(originalPageText);
    }

    public void switchToOriginalPage(){

        String originalHandle = getPageHandle();
        switchToAnotherWindow(originalHandle);
    }

    public String getCurrentLink(){
       return getCurrentUrl();
    }

    public String closeNewWindowsReturnToOriginalAndReadText(){

        String originalWindowHandle = driver.getWindowHandle();
        openNewWindow();

        for(String handle : driver.getWindowHandles()){
            if(!handle.equals(originalWindowHandle)){
                switchToWindow(handle);
                break;
            }
        }

        closeWindow();
        switchToWindow(originalWindowHandle);

        return getOriginalPageText();
    }
}
