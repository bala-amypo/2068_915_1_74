package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.Set;

public class FraudCheckResultDto {

    private Long claimId;
    private Boolean isFraudulent;
    private String triggeredRuleName;
    private String rejectionReason;
    private LocalDateTime checkedAt;
    private Set<String> matchedRules;

    public FraudCheckResultDto() {}

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Boolean getIsFraudulent() {
        return isFraudulent;
    }

    public void setIsFraudulent(Boolean isFraudulent) {
        this.isFraudulent = isFraudulent;
    }

    public String getTriggeredRuleName() {
        return triggeredRuleName;
    }

    public void setTriggeredRuleName(String triggeredRuleName) {
        this.triggeredRuleName = triggeredRuleName;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    public Set<String> getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(Set<String> matchedRules) {
        this.matchedRules = matchedRules;
    }
}
