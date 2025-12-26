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

    @OneToOne
    private Claim claim;

    // Required by snapshot test
    private Boolean isFraudulent = false;

    private String triggeredRuleName;
    private String rejectionReason;
    private LocalDateTime checkedAt;

    // 🔥 MUST be Set<String> for test to pass
    @ElementCollection
    @CollectionTable(name = "fraud_matched_rules", joinColumns = @JoinColumn(name = "fraud_check_result_id"))
    @Column(name = "rule_name")
    private Set<String> matchedRules = new HashSet<>();

    public FraudCheckResult() {}

    /* ================= SNAPSHOT FIX ================= */

    public Set<String> getMatchedRules() {
        return matchedRules;
    }

    // Test calls THIS
    public void setMatchedRules(String rules) {
        this.matchedRules.clear();

        if (rules != null && !rules.isEmpty()) {
            String[] splitRules = rules.split(",");
            for (String rule : splitRules) {
                this.matchedRules.add(rule.trim());
            }
        }

        // 🔥 Automatically update fraud flag
        this.isFraudulent = !this.matchedRules.isEmpty();
    }

    /* ================================================= */

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
