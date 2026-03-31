package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.WaitUtils;

import java.util.List;

public abstract class BasePage {

    protected final WebDriver driver;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
    }

    protected WebElement waitForVisible(By locator){
       return WaitUtils.waitForVisible(driver, locator);
    }

    protected WebElement waitForClickable(By locator){
       return WaitUtils.waitForClickable(driver, locator);
    }

    protected boolean waitForUrlContains(String text){
       return WaitUtils.waitForUrlContains(driver, text);
    }

    protected WebElement findElement(By locator) {
        return waitForVisible(locator);
    }

    protected List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    protected void click(By locator) {
        waitForClickable(locator).click();
    }

    protected String getTitle() {
        return driver.getTitle();
    }

    protected String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    protected boolean isElementVisible(By locator){
        return waitForVisible(locator).isDisplayed();
    }

    protected void sendKeys(By locator, String key){
        waitForVisible(locator).sendKeys(key);
    }

    protected void clear(By locator){
       waitForVisible(locator).clear();
    }

    protected void clearAndType(By locator, String text){
        WebElement element = findElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getAttribute(By locator, String attributeName){
        return findElement(locator).getAttribute(attributeName);
    }

    protected String getText(By locator){
       return findElement(locator).getText();
    }
}

