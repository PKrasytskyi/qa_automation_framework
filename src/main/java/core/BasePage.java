package core;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

    protected void jsClick(By locator) {
        WebElement element = findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
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

    protected void acceptAlert(){
        WaitUtils.waitForAlert(driver).accept();
    }

    protected void dismissAlert(){
        WaitUtils.waitForAlert(driver).dismiss();
    }

    protected String getAlertText(){
       return WaitUtils.waitForAlert(driver).getText();
    }

    protected void enterAlertText(String text){
        WaitUtils.waitForAlert(driver).sendKeys(text);
    }

    protected void switchToIFrameByLocator(WebElement element){
        driver.switchTo().frame(element);
    }

    protected void switchToIFrameByNameOrId(String nameOrId){
        driver.switchTo().frame(nameOrId);
    }

    protected void switchToIFrameByIndex(int index){
        driver.switchTo().frame(index);
    }

    protected void switchToParentIFrame(){
        driver.switchTo().parentFrame();
    }

    public void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }
}

