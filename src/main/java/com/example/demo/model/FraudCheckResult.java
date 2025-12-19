package com.example.demo.model;

public class FraudCheckResult {

    private boolean fraud;
    private String matchedRules;

    public FraudCheckResult() {
    }

    public FraudCheckResult(boolean fraud, String matchedRules) {
        this.fraud = fraud;
        this.matchedRules = matchedRules;
    }

    public boolean isFraud() {
        return fraud;
    }

    public void setFraud(boolean fraud) {
        this.fraud = fraud;
    }

    public String getMatchedRules() {
        return matchedRules;
    }

    public void setMatchedRules(String matchedRules) {
        this.matchedRules = matchedRules;
    }
}
