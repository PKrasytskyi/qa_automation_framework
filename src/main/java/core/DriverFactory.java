package core;

import config.ConfigReader;
import driver.BrowserManager;
import org.openqa.selenium.WebDriver;


public class DriverFactory {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver InitDriver(){

        String browser = ConfigReader.getBrowser();

        WebDriver webDriver = BrowserManager.createDriver(browser);

        driver.set(webDriver);
        return webDriver;
    }

    public static WebDriver getDriver(){
        return driver.get();
    }

    public static void quitDriver(){

        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }
}