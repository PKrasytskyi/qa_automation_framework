package api.triage;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogType;
import org.testng.ITestResult;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public final class FailureContextBuilder {


    private final WebDriver driver;

    public FailureContextBuilder(WebDriver driver) {
        this.driver = driver;
    }

    public FailureContext build(ITestResult result, WebDriver driver, String screenshotPath) {
        WebDriver effectiveDriver = driver != null ? driver : this.driver;
        Throwable throwable = result.getThrowable();


        return new FailureContext(
                result.getName(),
                safe(result.getTestClass().getName()),
                safe(result.getTestContext().getSuite().getName()),
                Arrays.asList(result.getMethod().getGroups()),
                ConfigReader.getBrowser(),
                ConfigReader.isHeadless(),
                Instant.now(),
                throwable != null ? throwable.getClass().getName() : "",
                throwable != null ? safe(throwable.getMessage()) : "",
                truncate(stackTraceOf(throwable), ConfigReader.getAgentMaxStackTraceChars()),
                safeCurrentUrl(effectiveDriver),
                safeTitle(effectiveDriver),
                safe(screenshotPath),
                truncate(safePageSource(effectiveDriver), ConfigReader.getAgentMaxPageSourceChars()),
                safeConsoleLogs(effectiveDriver)
        );
    }

    private String stackTraceOf(Throwable throwable) {
        if (throwable == null) {
            return "";
        }

        StringWriter stringWriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private String truncate(String value, int maxLength) {
        String sanitized = safe(value);
        if (sanitized.length() <= maxLength) {
            return sanitized;
        }

        return sanitized.substring(0, Math.max(0, maxLength - 3)) + "...";
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private String safeCurrentUrl(WebDriver driver) {
        if (driver == null) {
            return "";
        }

        try {
            return safe(driver.getCurrentUrl());
        } catch (RuntimeException ignored) {
            return "";
        }
    }

    private String safeTitle(WebDriver driver) {
        if (driver == null) {
            return "";
        }

        try {
            return safe(driver.getTitle());
        } catch (RuntimeException ignored) {
            return "";
        }
    }

    private String safePageSource(WebDriver driver) {
        if (driver == null) {
            return "";
        }

        try {
            return safe(driver.getPageSource());
        } catch (RuntimeException ignored) {
            return "";
        }
    }

    private List<String> safeConsoleLogs(WebDriver driver) {
        if (driver == null) {
            return List.of();
        }

        try {
            return driver.manage().logs().get(LogType.BROWSER).getAll().stream()
                    .map(entry -> entry.getLevel() + ": " + safe(entry.getMessage()))
                    .toList();
        } catch (RuntimeException ignored) {
            return List.of();
        }
    }
}
