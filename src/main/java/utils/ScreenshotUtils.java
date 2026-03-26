package utils;

import config.ConfigReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class ScreenshotUtils {


    public static String takeScreenshot(WebDriver driver, String testName){

        String path = ConfigReader.getScreenshotPath() + testName + "_" + System.currentTimeMillis() + ".png";

        try{
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            FileHandler.copy(src, new File(path));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return path;
    }
}
