package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            properties.load(is);

        } catch (Exception e){
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getBrowser(){
        return properties.getProperty("browser", "chrome");
    }

    public static String getBaseUrl(){
        return properties.getProperty("baseUrl");
    }

    public static int getTimeout(){
        return Integer.parseInt(properties.getProperty("timeout", "10"));
    }

    public static boolean isHeadless(){
        return Boolean.parseBoolean(properties.getProperty("headless", "false"));
    }

    public static String getScreenshotPath(){
        return properties.getProperty("screenshotPath", "screenshots/");
    }
}


