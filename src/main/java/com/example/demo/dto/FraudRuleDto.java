package com.example.demo.dto;

public class FraudRuleDto {

    // Rule id (response)
    private Long id;

    // Name of rule
    private String ruleName;

    // Field to check (e.g., claimAmount)
    private String conditionField;

    // Comparison operator (>, <, >=, <=, =)
    private String operator;

    // Threshold value
    private String value;

    // LOW, MEDIUM, HIGH
    private String severity;

    // ✅ No-arg constructor
    public FraudRuleDto() {
    }

    // ✅ Parameterized constructor (optional)
    public FraudRuleDto(Long id,
                        String ruleName,
                        String conditionField,
                        String operator,
                        String value,
                        String severity) {
        this.id = id;
        this.ruleName = ruleName;
        this.conditionField = conditionField;
        this.operator = operator;
        this.value = value;
        this.severity = severity;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getConditionField() {
        return conditionField;
    }

    public void setConditionField(String conditionField) {
        this.conditionField = conditionField;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
