package com.example.demo.repository;

import com.example.demo.model.FraudRule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FraudRuleRepository extends JpaRepository<FraudRule, Long> {

    // Fetch rule by ruleName
    Optional<FraudRule> findByRuleName(String ruleName);

    // Load all rules for evaluation
    @Override
    List<FraudRule> findAll();
}
