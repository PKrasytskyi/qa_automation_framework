package config;

import driver.ExecutionMode;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String DEFAULT_BASE_URL = "https://the-internet.herokuapp.com/";
    private static final String DEFAULT_API_BASE_URL = "https://jsonplaceholder.typicode.com";
    private static final String DEFAULT_TOKEN_API_BASE_URL = "https://gorest.co.in/";
    private static final String DEFAULT_BROWSER = "chrome";
    private static final int DEFAULT_EXPLICIT_WAIT = 10;
    private static final int DEFAULT_PAGE_LOAD_TIMEOUT = 30;
    private static final String DEFAULT_SCREENSHOT_PATH = "reports/screenshots/";
    private static final String DEFAULT_OPENAI_MODEL = "gpt-4.1";
    private static final int DEFAULT_MAX_TRACE_CHARS = 8_000;
    private static final int DEFAULT_MAX_PAGE_SOURCE_CHARS = 12_000;
    private static final String DEFAULT_API_TOKEN = "";
    private static final String DEFAULT_API_KEY = "";
    private static final String EXECUTE_MODE = "";

    static {
        try {
            InputStream is = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            if (is != null) {
                properties.load(is);
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to load config file", e);
        }
    }

    public static String getBrowser() {
        return getString("browser", "BROWSER", DEFAULT_BROWSER);
    }

    public static String getBaseUrl() {
        return getString(new String[]{"baseUrl", "base.url"}, "BASE_URL", DEFAULT_BASE_URL);
    }

    public static String getApiBaseUrl() {
        return getString(new String[]{"api.baseUrl", "api.base.url"}, "API_BASE_URL", DEFAULT_API_BASE_URL);
    }

    public static String getTokenApiBaseUrl(){
        return getString(new String[]{"api.tokenBaseUrl"}, "API_TOKEN_BASE_URL", DEFAULT_TOKEN_API_BASE_URL);
    }

    public static int getTimeout() {
        return getInt(new String[]{"explicit.wait", "timeout"}, "EXPLICIT_WAIT", DEFAULT_EXPLICIT_WAIT);
    }

    public static int getPageLoadTimeout() {
        return getInt("page.load.timeout", "PAGE_LOAD_TIMEOUT", DEFAULT_PAGE_LOAD_TIMEOUT);
    }

    public static ExecutionMode getExecuteMode(){
        String value = getString(
                "execute.mode",
                "EXECUTE_MODE",
                ExecutionMode.LOCAL.name()
        );
        return ExecutionMode.valueOf(value.trim().toUpperCase());
    }

    public static String getSeleniumRemoteUrl(){
        return getString(
                "selenium.remote.url",
                "SELENIUM_REMOTE_URL",
                "http://localhost:4444"
        );
    }

    public static boolean isHeadless() {
        return getBoolean("headless", "HEADLESS", false);
    }

    public static String getScreenshotPath() {
        return getString("screenshotPath", "SCREENSHOT_PATH", DEFAULT_SCREENSHOT_PATH);
    }

    public static int getAgentMaxStackTraceChars() {
        return getInt("maxStackTraceChars", "MAX_STACK_TRACE_CHARS", DEFAULT_MAX_TRACE_CHARS);
    }

    public static int getAgentMaxPageSourceChars() {
        return getInt("maxPageSourceChars", "MAX_PAGE_SOURCE_CHARS", DEFAULT_MAX_PAGE_SOURCE_CHARS);
    }

    public static String getOpenAiApiKey() {
        return getString("openai.api.key", "OPENAI_API_KEY", "");
    }

    public static String getOpenAiModel() {
        return getString("openai.model", "OPENAI_MODEL", DEFAULT_OPENAI_MODEL);
    }

    public static boolean isAgentEnabled() {
        return getBoolean("agent.enabled", "AGENT_ENABLED", false);
    }

    public static boolean isAgentTriageMode() {
        return "triage".equalsIgnoreCase(getAgentMode());
    }

    public static int getAgentTimeoutSeconds() {
        return getInt("agent.timeout.seconds", "AGENT_TIMEOUT_SECONDS", 30);
    }

    public static String getAgentMode() {
        return getString("agent.mode", "AGENT_MODE", "off");
    }

    public static String getApiToken(){ return getString("api.token", "API_TOKEN", DEFAULT_API_TOKEN); }

    public static String getApiKey() { return getString("api.key", "API_KEY", DEFAULT_API_KEY); }

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
