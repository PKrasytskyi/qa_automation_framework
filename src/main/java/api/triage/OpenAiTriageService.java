package api.triage;

import api.OpenAiAgentService;
import config.ConfigReader;

import java.util.Optional;

public class OpenAiTriageService {

    private static final String TRIAGE_INSTRUCTIONS = """
            
            You are a QA failure triage assistant for a Java Selenium TestNG framework.
            
            Rules:
            1. Use only the providence evidence.
            2. Do not invent product behavior.
            3. Do not change expected result.
            4. If the evidence is week, mark needHumanReview=true.
            5. Distinguish between locator_issue, assertion_failure, env_issue, flaky, data_issue, and unknown.
            6. Respond in a structure way that can be mapped to:
                    failureType, probableRootCause, confidence, rerunRecommended, needHumanReview, suggestedFix, summary.
            
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
                
                Return:
                    - failureType
                    - probableRootCause
                    - confidence
                    - rerunRecommended
                    - needsHumanReview
                    - suggestedFix
                    - summary
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

    private TriageDecision parseDecision(String rawResponse) {

        return TriageDecision.humanReview(rawResponse);
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}
