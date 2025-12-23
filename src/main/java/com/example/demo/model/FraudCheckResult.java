package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "fraud_check_results")
public class FraudCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Snapshot reference only (NO entity relation)
     */
    @Column(nullable = false)
    private Long claimId;

    @Column(nullable = false)
    private Boolean isFraudulent;

    private String triggeredRuleName;
    private String rejectionReason;

    @Column(nullable = false)
    private LocalDateTime checkedAt;

    /**
     * Snapshot-safe rule storage
     */
    @ElementCollection
    @CollectionTable(
            name = "fraud_result_rules",
            joinColumns = @JoinColumn(name = "fraud_result_id")
    )
    @Column(name = "rule_name")
    private Set<String> matchedRuleNames = new HashSet<>();

    public FraudCheckResult() {}

    // ---------- Getters & Setters ----------

    public Long getId() {
        return id;
    }

    public Long getClaimId() {
        return claimId;
    }

    public void setClaimId(Long claimId) {
        this.claimId = claimId;
    }

    public Boolean getIsFraudulent() {
        return isFraudulent;
    }

    public void setIsFraudulent(Boolean fraudulent) {
        isFraudulent = fraudulent;
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

    public Set<String> getMatchedRuleNames() {
        return matchedRuleNames;
    }

    public void setMatchedRuleNames(Set<String> matchedRuleNames) {
        this.matchedRuleNames = matchedRuleNames;
    }
}
