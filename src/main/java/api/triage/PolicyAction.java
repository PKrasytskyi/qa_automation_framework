package api.triage;

public enum PolicyAction {
    HUMAN_REVIEW,
    INFO_ONLY,
    BUG_DRAFT,
    REPAIR_DRAFT,
    RERUN_CANDIDATE,
    INFRA_REVIEW
}
