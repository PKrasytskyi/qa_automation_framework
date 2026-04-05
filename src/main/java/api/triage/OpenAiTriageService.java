package api.triage;

import api.OpenAiAgentService;
import config.ConfigReader;

import java.util.Optional;

public class OpenAiTriageService {

    private static final String TRIAGE_INSTRUCTIONS = """
            You are a QA failure triage assistant for a Java Selenium TestNG framework.

            Rules:
            1. Use only the provided evidence.
            2. Do not invent product behavior.
            3. Do not change the expected result.
            4. If the evidence is weak, set needsHumanReview: true.
            5. Distinguish between locator_issue, assertion_failure, env_issue, flaky, data_issue, and unknown.
            6. Return exactly seven lines.
            7. Each line must follow this format: key: value
            8. Do not return markdown, code fences, bullets, or extra commentary.
            9. The keys must be exactly:
               failureType
               probableRootCause
               confidence
               rerunRecommended
               needsHumanReview
               suggestedFix
               summary
            """;

    private final OpenAiAgentService agentService;

    public OpenAiTriageService() {
        this(null);
    }

    public OpenAiTriageService(OpenAiAgentService agentService) {
        this.agentService = agentService;
    }

    public Optional<TriageDecision> triage(FailureContext context) {
        if (!ConfigReader.isAgentEnabled()) {
            return Optional.empty();
        }

        if (!ConfigReader.isAgentTriageMode()) {
            return Optional.empty();
        }

        if (ConfigReader.getOpenAiApiKey().isBlank()) {
            return Optional.empty();
        }

        try {
            OpenAiAgentService effectiveAgentService = agentService != null ? agentService : new OpenAiAgentService();
            String userTask = buildUserTask(context);
            String rawResponse = effectiveAgentService.runTask(TRIAGE_INSTRUCTIONS, userTask);

            TriageDecision decision = parseDecision(rawResponse);
            return Optional.of(decision);
        } catch (Exception e) {
            return Optional.of(TriageDecision.humanReview("AI triage failed: " + safe(e.getMessage())));
        }
    }

    private String buildUserTask(FailureContext context) {
        return """
                Analyze this failure UI test.
                
                Test name: %s
                Class name: %s
                Suite name: %s
                Groups: %s
                Browser: %s
                Headless: %s
                Occurred at: %s
                
                Exception type: %s
                Exception message: %s
                Stack trace: %s
                
                Current URL: %s
                Page title: %s
                Screenshot path: %s
                
                Page source snippet: %s
                
                Browser console logs: %s
                
                Return exactly these lines:
                failureType: <value>
                probableRootCause: <value>
                confidence: <0-100>
                rerunRecommended: <true|false>
                needsHumanReview: <true|false>
                suggestedFix: <value>
                summary: <value>
                """.formatted(
                safe(context.testName()),
                safe(context.className()),
                safe(context.suiteName()),
                context.groups(),
                safe(context.browser()),
                context.headless(),
                context.occurredAt(),
                safe(context.exceptionType()),
                safe(context.exceptionMessage()),
                safe(context.stackTrace()),
                safe(context.currentUrl()),
                safe(context.pageTitle()),
                safe(context.screenshotPath()),
                safe(context.pageSourceSnippet()),
                context.consoleLogs()
        );
    }

    TriageDecision parseDecision(String rawResponse) {
        if (rawResponse == null || rawResponse.isBlank()) {
            return TriageDecision.humanReview("Model returned an empty triage response.");
        }

        String failureType = extractValue(rawResponse, "failureType");
        String probableRootCause = extractValue(rawResponse, "probableRootCause");
        int confidence = parseConfidence(extractValue(rawResponse, "confidence"));
        boolean rerunRecommended = parseBoolean(extractValue(rawResponse, "rerunRecommended"));
        boolean needsHumanReview = parseBoolean(extractValue(rawResponse, "needsHumanReview"));
        String suggestedFix = extractValue(rawResponse, "suggestedFix");
        String summary = extractValue(rawResponse, "summary");

        boolean missingCoreFields = isBlank(failureType) && isBlank(probableRootCause) && isBlank(summary);
        if (missingCoreFields) {
            return TriageDecision.humanReview(rawResponse);
        }

        return new TriageDecision(
                defaultIfBlank(failureType, "unknown"),
                defaultIfBlank(probableRootCause, "The model did not provide a root cause."),
                confidence,
                rerunRecommended,
                needsHumanReview,
                suggestedFix,
                defaultIfBlank(summary, rawResponse)
        );
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private String extractValue(String rawResponse, String key) {
        String prefix = key + ":";

        for (String line : rawResponse.split("\\R")) {
            String trimmed = line.trim();
            if (trimmed.regionMatches(true, 0, prefix, 0, prefix.length())) {
                return trimmed.substring(prefix.length()).trim();
            }
        }

        return "";
    }

    private int parseConfidence(String rawValue) {
        if (isBlank(rawValue)) {
            return 0;
        }

        String digitsOnly = rawValue.replaceAll("[^0-9]", "");
        if (digitsOnly.isBlank()) {
            return 0;
        }

        try {
            return Math.max(0, Math.min(100, Integer.parseInt(digitsOnly)));
        } catch (NumberFormatException ignored) {
            return 0;
        }
    }

    private boolean parseBoolean(String rawValue) {
        String normalized = safe(rawValue).trim().toLowerCase();
        return normalized.equals("true")
                || normalized.equals("yes")
                || normalized.equals("y")
                || normalized.equals("1");
    }

    private String defaultIfBlank(String value, String fallback) {
        return isBlank(value) ? fallback : value;
    }

    private boolean isBlank(String value) {
        return value == null || value.isBlank();
    }
}
