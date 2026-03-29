package api.triage;

public record TriageDecision(
        String failureType,
        String probableRootCause,
        int confidence,
        boolean rerunRecommended,
        boolean needsHumanReview,
        String suggestedFix,
        String summary
) {

    public static TriageDecision humanReview(String summary){
        return new TriageDecision(
                "unknown",
                "Insufficient evidence for automatic classification",
                0,
                false,
                true,
                "",
                summary
        );
    }

    public static TriageDecision skipped(String summary){
        return new TriageDecision(
                "skipped",
                "AI triage was skipped",
                0,
                false,
                false,
                "",
                summary
        );
    }

    public String toAttachmentText(){
        return """
                failureType: %s
                probableRootCause: %s
                confidence: %d
                rerunRecommended: %s
                needsHumanReview: %s
                suggestedFix: %s
                summary: %s
                """.formatted(
                        safe(failureType),
                        safe(probableRootCause),
                        confidence,
                        rerunRecommended,
                        needsHumanReview,
                        safe(suggestedFix),
                        safe(summary)
        );
    }

    private String safe(String value){
        return value == null ? "" : value;
    }

}
