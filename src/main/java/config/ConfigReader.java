package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String DEFAULT_BASE_URL = "https://the-internet.herokuapp.com/";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final int DEFAULT_EXPLICIT_WAIT = 10;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;
    private static final String DEFAULT_SCREENSHOT_PATH = "reports/screenshots/";

    static {
        try {
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (is != null) {
                properties.load(is);
            }

        } catch (Exception e){
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getBrowser(){
        return getString("browser", "BROWSER", DEFAULT_BROWSER);
    }

    public static String getBaseUrl(){
        return getString(new String[]{"baseUrl", "base.url"}, "BASE_URL", DEFAULT_BASE_URL);
    }

    public static int getTimeout(){
        return getInt(new String[]{"explicit.wait", "timeout"}, "EXPLICIT_WAIT", DEFAULT_EXPLICIT_WAIT);
    }

    public static int getPageLoadTimeout(){
        return getInt("page.load.timeout", "PAGE_LOAD_TIMEOUT", DEFAULT_PAGE_LOAD_TIMEOUT);
    }

    public static boolean isHeadless(){
        return getBoolean("headless", "HEADLESS", false);
    }

    public static String getScreenshotPath(){
        return getString("screenshotPath", "SCREENSHOT_PATH", DEFAULT_SCREENSHOT_PATH);
    }

    private static String getString(String propertyKey, String envKey, String defaultValue) {
        return getString(new String[]{propertyKey}, envKey, defaultValue);
    }

    private static String getString(String[] propertyKeys, String envKey, String defaultValue) {
        String envValue = System.getenv(envKey);
        if (envValue != null && !envValue.isBlank()) {
            return envValue.trim();
        }

        for (String propertyKey : propertyKeys) {
            String value = properties.getProperty(propertyKey);
            if (value != null && !value.isBlank()) {
                return value.trim();
            }
        }

        return defaultValue;
    }

    private static int getInt(String propertyKey, String envKey, int defaultValue) {
        return getInt(new String[]{propertyKey}, envKey, defaultValue);
    }

    private static int getInt(String[] propertyKeys, String envKey, int defaultValue) {
        String rawValue = getString(propertyKeys, envKey, String.valueOf(defaultValue));
        return Integer.parseInt(rawValue);
    }

    private static boolean getBoolean(String propertyKey, String envKey, boolean defaultValue) {
        String rawValue = getString(propertyKey, envKey, String.valueOf(defaultValue));
        return Boolean.parseBoolean(rawValue);
    }
}
