package utils;

import config.ConfigReader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private WaitUtils() {
    }

    public static WebElement waitForVisible(WebDriver driver, By locator) {
        return buildWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static WebElement waitForClickable(WebDriver driver, By locator) {
        return buildWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
    }

    public static boolean waitForUrlContains(WebDriver driver, String urlPart) {
        return buildWait(driver).until(ExpectedConditions.urlContains(urlPart));
    }

    private static WebDriverWait buildWait(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getTimeout()));
    }

    public static Alert waitForAlert(WebDriver driver) {
        return buildWait(driver).until(ExpectedConditions.alertIsPresent());
    }

}
