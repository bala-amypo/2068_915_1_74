package com.example.demo.service;

import com.example.demo.model.FraudRule;
import java.util.List;

public interface FraudRuleService {

    FraudRule createRule(FraudRule rule);

    List<FraudRule> getAllRules();
}
