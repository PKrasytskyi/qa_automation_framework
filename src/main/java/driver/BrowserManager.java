package driver;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URI;

public class BrowserManager {

    public static WebDriver createDriver(String browser){
        return switch (browser.toLowerCase()){
            case "chrome" -> createChromeDriver();
            default -> throw new IllegalArgumentException(
                    "Unsupported browser:" + browser
            );
        };
    }

    private static WebDriver createChromeDriver() {

        ChromeOptions options = createChromeOptions();

        return switch (ConfigReader.getExecuteMode()){
            case LOCAL -> new ChromeDriver(options);
            case REMOTE -> createRemoteDriver(options);
        };
    }

    private static WebDriver createRemoteDriver(ChromeOptions options){
                String remoteUrl = ConfigReader.getSeleniumRemoteUrl();

            try {
                return new RemoteWebDriver(
                        URI.create(remoteUrl).toURL(), options
                );
            } catch (MalformedURLException e){
                throw new IllegalStateException("Invalid Selenium remote URL " + remoteUrl, e);
            }

    }

    private static ChromeOptions createChromeOptions(){
        ChromeOptions options = new ChromeOptions();

        options.addArguments("--window-size=1920,1080");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        if(ConfigReader.isHeadless()){
            options.addArguments("--headless");
        }

        return options;
    }
}
