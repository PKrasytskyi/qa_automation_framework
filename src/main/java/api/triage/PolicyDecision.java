package api.triage;

public record PolicyDecision(
        PolicyAction action,
        String reason,
        boolean confidenceAccepted,
        boolean humanReviewRequired,
        String notes
) {

    public static PolicyDecision of(
            PolicyAction action,
            String reason,
            boolean confidenceAccepted,
            boolean humanReviewRequired,
            String notes
    ) {
        return new PolicyDecision(
                action == null ? PolicyAction.HUMAN_REVIEW : action,
                safe(reason),
                confidenceAccepted,
                humanReviewRequired,
                safe(notes)
        );
    }

    public static PolicyDecision humanReview(String reason) {
        return of(
                PolicyAction.HUMAN_REVIEW,
                reason,
                false,
                true,
                "Manual investigation is required before taking action."
        );
    }

    public static PolicyDecision infoOnly(String reason) {
        return of(
                PolicyAction.INFO_ONLY,
                reason,
                false,
                false,
                "Use the AI triage as guidance only."
        );
    }

    public String toAttachmentText() {
        return """
                action: %s
                reason: %s
                confidenceAccepted: %s
                humanReviewRequired: %s
                notes: %s
                """.formatted(
                action,
                safe(reason),
                confidenceAccepted,
                humanReviewRequired,
                safe(notes)
        );
    }

    private static String safe(String value) {
        return value == null ? "" : value;
    }
}
