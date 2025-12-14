package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Policy {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private User user;
    @Column(unique = true)
    private String policyNumber;
    private String policyType;
    private LocalDate startDate;
    private LocalDate endDate;

}
