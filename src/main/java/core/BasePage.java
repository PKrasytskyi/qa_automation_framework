package core;

import org.openqa.selenium.*;
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

    protected boolean waitForDisappearenceElement(By locator) {
       return WaitUtils.waitForDisappearenceElement(driver, locator);}

    protected boolean waitForAppearanceElement(By locator){
        return WaitUtils.waitForAppearanceElement(driver, locator).isDisplayed();}

    protected boolean waitForElementBecomeEnable(By locator){
        return WaitUtils.waitForElementBecomeEnable(driver, locator);}

    protected boolean waitForElementBecomeDisable(By locator){
        return WaitUtils.waitForElementBecomeDisable(driver, locator);}

    protected boolean waitForTextToBePresent(By locator, String text){
        return WaitUtils.waitForTextToBePresent(driver, locator, text);
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

    protected void switchToDefaultContent(){
        driver.switchTo().defaultContent();
    }

    protected String getFrameTextByNameOrId(String nameOrId){
       return getText((By) driver.switchTo().frame(nameOrId));
    }

    protected String getPageHandle(){
       return driver.getWindowHandle();
    }

    protected void switchToNewWindow(){
        driver.switchTo().newWindow(WindowType.WINDOW);
    }

    protected void switchToNewTab(){
        driver.switchTo().newWindow(WindowType.TAB);
    }

    protected void switchToWindow(String handle) {
        driver.switchTo().window(handle);
    }


    protected void switchToAnotherWindow(String currentHandle){

        for(String handle : driver.getWindowHandles()){
            if(!handle.equals(currentHandle)){
                driver.switchTo().window(handle);
                return;
            }
        }
    }

    protected void closeWindow(){
        driver.close();
    }


}

