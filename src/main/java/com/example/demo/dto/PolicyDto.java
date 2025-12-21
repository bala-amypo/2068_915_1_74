package com.example.demo.dto;

import java.time.LocalDate;

public class PolicyDto {

    // Policy id (response)
    private Long id;

    // Owner user id
    private Long userId;

    // Policy number
    private String policyNumber;

    // Policy type
    private String policyType;

    // Start date
    private LocalDate startDate;

    // End date
    private LocalDate endDate;

    // ✅ No-arg constructor
    public PolicyDto() {
    }

    // ✅ Parameterized constructor (optional but useful)
    public PolicyDto(Long id,
                     Long userId,
                     String policyNumber,
                     String policyType,
                     LocalDate startDate,
                     LocalDate endDate) {
        this.id = id;
        this.userId = userId;
        this.policyNumber = policyNumber;
        this.policyType = policyType;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // ===== Getters & Setters =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
