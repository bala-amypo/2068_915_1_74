package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FraudCheckResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "fraudCheckResult")
    private Claim claim;

    private boolean isFraudulent;
    
    // Removed fields:
    // private String triggeredRuleName;
    // private String rejectionReason;

    private LocalDateTime checkedAt;

    @ManyToMany
    @JoinTable(
            name = "fraud_result_rules",
            joinColumns = @JoinColumn(name = "result_id"),
            inverseJoinColumns = @JoinColumn(name = "rule_id")
    )
    private Set<FraudRule> matchedRules = new HashSet<>();

    // Default constructor for JPA
    public FraudCheckResult() {
    }

    // Updated constructor
    public FraudCheckResult(Claim claim, boolean isFraudulent, LocalDateTime checkedAt) {
        this.claim = claim;
        this.isFraudulent = isFraudulent;
        this.checkedAt = checkedAt;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Claim getClaim() {
        return claim;
    }

    public void setClaim(Claim claim) {
        this.claim = claim;
    }

    public boolean isFraudulent() {
        return isFraudulent;
    }

    public void setFraudulent(boolean fraudulent) {
        isFraudulent = fraudulent;
    }

    public LocalDateTime getCheckedAt() {
        return checkedAt;
    }

    public void setCheckedAt(LocalDateTime checkedAt) {
        this.checkedAt = checkedAt;
    }

    public Set<FraudRule> getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(Set<FraudRule> matchedRules) {
        this.matchedRules = matchedRules;
    }
}
