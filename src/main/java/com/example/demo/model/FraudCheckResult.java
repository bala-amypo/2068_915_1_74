package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

public class FraudCheckResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Claim claim;

    private Boolean isFraudulent;
    private String triggeredRuleName;
    private String rejectionReason;
    private LocalDateTime checkedAt;

    @PrePersist
    public void onCreate(){
        checkedAt = LocalDateTime.now();
    }
}
