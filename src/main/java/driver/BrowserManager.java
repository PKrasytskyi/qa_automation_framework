package driver;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BrowserManager {

    public static WebDriver createDriver(String browser){

        return switch (browser.toLowerCase()) {
            case "chrome" -> createChrome();
            //case "firefox" -> new FirefoxDriver();
            default -> throw new RuntimeException("Browser not supported");
        };
    }

    private static WebDriver createChrome(){

        ChromeOptions options = new ChromeOptions();

        if(Boolean.parseBoolean(ConfigReader.getHeadless())){
            options.addArguments("--headless=new");
        }
        options.addArguments("--start-maximized");

        return new ChromeDriver(options);
    }
}
