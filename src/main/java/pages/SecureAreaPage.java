package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SecureAreaPage extends BasePage{

    public SecureAreaPage(WebDriver driver) {
        super(driver);
    }

    //Locators
    private final By flashText = By.id("flash");
    private final By logoutButton = By.cssSelector("a[href='/logout']");

    public String getFlashText() {
        return getText(flashText);
    }

    public void clickLogoutButton() {
        click(logoutButton);
    }

    public boolean isPageOpened() {
        return waitForUrlContains("secure");
    }

    public boolean isLogoutButtonVisible() {
       return isElementVisible(logoutButton);
    }
}
