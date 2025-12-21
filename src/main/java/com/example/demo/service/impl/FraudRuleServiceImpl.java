package com.example.demo.service.impl;

import com.example.demo.model.FraudRule;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudRuleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FraudRuleServiceImpl implements FraudRuleService {

    private final FraudRuleRepository fraudRuleRepository;

    // ✅ Constructor injection ONLY
    public FraudRuleServiceImpl(FraudRuleRepository fraudRuleRepository) {
        this.fraudRuleRepository = fraudRuleRepository;
    }

    @Override
    public FraudRule addRule(FraudRule rule) {

        // Validate unique rule name
        if (fraudRuleRepository.findByRuleName(rule.getRuleName()).isPresent()) {
            throw new IllegalArgumentException("Invalid or duplicate rule name");
        }

        // Validate severity
        String severity = rule.getSeverity();
        if (!("LOW".equalsIgnoreCase(severity)
                || "MEDIUM".equalsIgnoreCase(severity)
                || "HIGH".equalsIgnoreCase(severity))) {
            throw new IllegalArgumentException("Invalid fraud rule severity");
        }

        return fraudRuleRepository.save(rule);
    }

    @Override
    public List<FraudRule> getAllRules() {
        return fraudRuleRepository.findAll();
    }
}
