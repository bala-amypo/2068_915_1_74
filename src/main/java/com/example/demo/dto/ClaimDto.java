package com.example.demo.dto;

import java.time.LocalDate;

public class ClaimDto {
    
    private Long id;
    private Long policyId;
    private LocalDate claimDate;
    private Double claimAmount;
    private String description;
    private String status;
 
    public ClaimDto() {
    }

    public ClaimDto(Long id,
                    Long policyId,
                    LocalDate claimDate,
                    Double claimAmount,
                    String description,
                    String status) {
        this.id = id;
        this.policyId = policyId;
        this.claimDate = claimDate;
        this.claimAmount = claimAmount;
        this.description = description;
        this.status = status;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Long policyId) {
        this.policyId = policyId;
    }

    public LocalDate getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(LocalDate claimDate) {
        this.claimDate = claimDate;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
