package api.triage;

import java.time.Instant;
import java.util.List;

public record FailureContext(String testName,
                             String className,
                             String suiteName,
                             List<String> groups,
                             String browser,
                             boolean headless,
                             Instant occurredAt,
                             String exceptionType,
                             String exceptionMessage,
                             String stackTrace,
                             String currentUrl,
                             String pageTitle,
                             String screenshotPath,
                             String pageSourceSnippet,
                             List<String> consoleLogs) {
}
