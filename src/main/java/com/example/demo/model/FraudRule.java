package com.example.demo.model;

import jakarta.persistence.*;

public class FraudRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column( unique = true )
    private String ruleName;
    private String conditionField;
    private String operator;
    private String value;
    private String severity;
}
