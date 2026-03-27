package utils;

import config.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ScreenshotUtils {


    public static String takeScreenshot(WebDriver driver, String testName){
        if (driver == null) {
            return null;
        }

        Path directory = Path.of(ConfigReader.getScreenshotPath());
        Path screenshotPath = directory.resolve(testName + "_" + System.currentTimeMillis() + ".png");

        try{
            Files.createDirectories(directory);
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(src, new File(screenshotPath.toString()));
        }
        catch (IOException e){
            throw new RuntimeException("Failed to persist screenshot to " + screenshotPath, e);
        }
        catch (ClassCastException e) {
            throw new RuntimeException("Current driver does not support screenshots", e);
        }
        return screenshotPath.toString();
    }
}
