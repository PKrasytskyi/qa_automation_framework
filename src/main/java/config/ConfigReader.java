package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties;

    static {

        properties = new Properties();

        try {
            FileInputStream fis = new FileInputStream("config/config.properties");

            properties.load(fis);
        }
        catch (Exception e){

            throw new RuntimeException("Failed to load config file");
        }
    }

    public static String getBrowser(){
        return properties.getProperty("browser");
    }

    public static String getBaseUrl(){
        return properties.getProperty("baseUrl");
    }

    public static String getTimeOut(){
        return properties.getProperty("timeout");
    }

    public static String getScreenshotPath(){
        return properties.getProperty("screenshotPath");
    }

    public static String getHeadless(){
        return properties.getProperty("headless");
    }
}


