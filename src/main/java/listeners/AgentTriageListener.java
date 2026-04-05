package listeners;

import api.triage.DecisionPolicy;
import api.triage.FailureContext;
import api.triage.OpenAiTriageService;
import api.triage.PolicyDecision;
import api.triage.TriageDecision;
import config.ConfigReader;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Optional;

public class AgentTriageListener implements ITestListener, IInvokedMethodListener {

    private static final String TRIAGE_ATTACHED_ATTRIBUTE = "allure.agentTriageAttached";

    private final OpenAiTriageService triageService;
    private final DecisionPolicy decisionPolicy;

    public AgentTriageListener() {
        this(new OpenAiTriageService(), new DecisionPolicy());
    }

    AgentTriageListener(OpenAiTriageService triageService, DecisionPolicy decisionPolicy) {
        this.triageService = triageService;
        this.decisionPolicy = decisionPolicy;
    }

    @Override
    public void onTestStart(ITestResult result) {
        AllureAttachmentSupport.captureTestCaseUuid(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult result) {
        if (method.isTestMethod()) {
            AllureAttachmentSupport.captureTestCaseUuid(result);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult result) {
        if (!method.isTestMethod() || result.getStatus() != ITestResult.FAILURE) {
            return;
        }

        attachTriageDetails(result);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        attachTriageDetails(result);
    }

    private void attachTriageDetails(ITestResult result) {
        if (!ConfigReader.isAgentEnabled() || !ConfigReader.isAgentTriageMode()) {
            return;
        }

        if (Boolean.TRUE.equals(result.getAttribute(TRIAGE_ATTACHED_ATTRIBUTE))) {
            return;
        }

        Object attribute = result.getAttribute(TestListener.FAILURE_CONTEXT_ATTRIBUTE);
        if (!(attribute instanceof FailureContext failureContext)) {
            if (attachText(result, "AI Triage", "FailureContext was not found in TestNG result attributes.")) {
                result.setAttribute(TRIAGE_ATTACHED_ATTRIBUTE, true);
            }
            return;
        }

        boolean attachedFailureContext = attachText(result, "Failure Context", formatFailureContext(failureContext));
        boolean attachedDecision;
        boolean attachedPolicyDecision;

        try {
            Optional<TriageDecision> triageResult = triageService.triage(failureContext);
            TriageDecision decision = triageResult.orElseGet(
                    () -> TriageDecision.skipped("AI triage is disabled or API key is missing.")
            );
            PolicyDecision policyDecision = decisionPolicy.evaluate(decision);
            attachedDecision = attachText(result, "AI Triage", decision.toAttachmentText());
            attachedPolicyDecision = attachText(result, "Policy Decision", policyDecision.toAttachmentText());
        } catch (Exception e) {
            attachedDecision = attachText(result, "AI Triage Error", safe(e.getMessage()));
            attachedPolicyDecision = false;
        }

        if (attachedFailureContext || attachedDecision || attachedPolicyDecision) {
            result.setAttribute(TRIAGE_ATTACHED_ATTRIBUTE, true);
        }
    }

    private boolean attachText(ITestResult result, String name, String content) {
        return AllureAttachmentSupport.addTextAttachment(result, name, safe(content));
    }

    private String formatFailureContext(FailureContext context) {
        return """
                testName: %s
                className: %s
                suiteName: %s
                groups: %s
                browser: %s
                headless: %s
                occurredAt: %s
                exceptionType: %s
                exceptionMessage: %s
                currentUrl: %s
                pageTitle: %s
                screenshotPath: %s
                consoleLogsCount: %d
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
                safe(context.currentUrl()),
                safe(context.pageTitle()),
                safe(context.screenshotPath()),
                context.consoleLogs() == null ? 0 : context.consoleLogs().size()
        );
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }
}
