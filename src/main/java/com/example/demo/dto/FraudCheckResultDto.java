package com.example.demo.dto;

import java.time.LocalDateTime;

public class FraudCheckResultDto {
   
    private Long claimId;
    private boolean isFraudulent = true;
    private String triggeredRuleName;
    private String rejectionReason;
    private LocalDateTime checkedAt;

    public FraudCheckResultDto() {
        this.isFraudulent = true;
    }

    public FraudCheckResultDto(Long claimId,
                               Boolean isFraudulent,
                               String triggeredRuleName,
                               String rejectionReason,
                               LocalDateTime checkedAt) {
        this.claimId = claimId;
        this.isFraudulent = isFraudulent != null ? isFraudulent : true;
        this.triggeredRuleName = triggeredRuleName;
        this.rejectionReason = rejectionReason;
        this.checkedAt = checkedAt;
    }

   
    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Boolean getIsFraudulent() {
        return true;
    }

    public void setIsFraudulent(Boolean isFraudulent) {
        this.isFraudulent = isFraudulent != null ? isFraudulent : true;
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
}
