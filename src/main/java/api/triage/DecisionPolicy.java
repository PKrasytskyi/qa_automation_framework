package api.triage;

public class DecisionPolicy {

    private static final int LOW_CONFIDENCE_THRESHOLD = 50;
    private static final int FLAKY_RERUN_THRESHOLD = 70;
    private static final int LOCATOR_REPAIR_THRESHOLD = 75;
    private static final int ASSERTION_BUG_THRESHOLD = 80;

    public PolicyDecision evaluate(TriageDecision triageDecision) {
        if (triageDecision == null) {
            return PolicyDecision.humanReview("Triage decision is missing.");
        }

        if (triageDecision.needsHumanReview()) {
            return PolicyDecision.humanReview(
                    "The AI triage explicitly requested manual review."
            );
        }

        if (triageDecision.confidence() < LOW_CONFIDENCE_THRESHOLD) {
            return PolicyDecision.humanReview(
                    "The AI triage confidence is below the accepted threshold."
            );
        }

        String failureType = normalizeFailureType(triageDecision.failureType());

        return switch (failureType) {
            case "env_issue" -> PolicyDecision.of(
                    PolicyAction.INFRA_REVIEW,
                    "The failure is likely caused by environment or infrastructure instability.",
                    true,
                    false,
                    "Review browser startup, network stability, CI host health, or test environment availability."
            );
            case "flaky" -> evaluateFlaky(triageDecision);
            case "locator_issue" -> evaluateLocatorIssue(triageDecision);
            case "assertion_failure" -> evaluateAssertionFailure(triageDecision);
            default -> PolicyDecision.infoOnly(
                    "The AI triage produced a useful signal, but no strong workflow action was selected."
            );
        };
    }

    private PolicyDecision evaluateFlaky(TriageDecision triageDecision) {
        if (triageDecision.confidence() >= FLAKY_RERUN_THRESHOLD) {
            return PolicyDecision.of(
                    PolicyAction.RERUN_CANDIDATE,
                    "The failure looks flaky with sufficient confidence for a controlled rerun recommendation.",
                    true,
                    true,
                    "Recommend at most one rerun and review evidence before changing test logic."
            );
        }

        return PolicyDecision.humanReview(
                "The failure may be flaky, but the confidence is not high enough for a rerun recommendation."
        );
    }

    private PolicyDecision evaluateLocatorIssue(TriageDecision triageDecision) {
        if (triageDecision.confidence() >= LOCATOR_REPAIR_THRESHOLD) {
            return PolicyDecision.of(
                    PolicyAction.REPAIR_DRAFT,
                    "The failure is likely caused by an unstable or outdated locator.",
                    true,
                    true,
                    "Prepare a repair draft, but review the locator change before editing page objects."
            );
        }

        return PolicyDecision.infoOnly(
                "A locator issue is possible, but the confidence is not high enough for a repair recommendation."
        );
    }

    private PolicyDecision evaluateAssertionFailure(TriageDecision triageDecision) {
        if (triageDecision.confidence() >= ASSERTION_BUG_THRESHOLD) {
            return PolicyDecision.of(
                    PolicyAction.BUG_DRAFT,
                    "The failure likely reflects a product behavior mismatch or expectation violation.",
                    true,
                    true,
                    "Prepare a bug draft and validate the expected behavior against requirements."
            );
        }

        return PolicyDecision.infoOnly(
                "The assertion failure signal is useful, but not strong enough for an immediate bug draft recommendation."
        );
    }

    private String normalizeFailureType(String failureType) {
        return failureType == null ? "" : failureType.trim().toLowerCase();
    }
}
