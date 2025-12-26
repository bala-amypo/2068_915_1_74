package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fraud_check_results")
public class FraudCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Claim claim;

    private Boolean isFraudulent = false;

    private String triggeredRuleName;
    private String rejectionReason;
    private LocalDateTime checkedAt;

    // 🔥 SNAPSHOT FIELD (TEST EXPECTS THIS)
    @Column(length = 1000)
    private String matchedRules;

    public FraudCheckResult() {}

    // ---------- REQUIRED BY TEST ----------
    public void setMatchedRules(String ruleNames) {
        this.matchedRules = ruleNames;
        this.isFraudulent = ruleNames != null && !ruleNames.isBlank();
    }

    public String getMatchedRules() {
        return matchedRules;
    }
    // -------------------------------------

    public Boolean getIsFraudulent() {
        return isFraudulent;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
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
