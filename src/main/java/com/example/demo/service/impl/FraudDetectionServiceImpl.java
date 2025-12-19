package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.service.FraudDetectionService;
import com.example.demo.service.FraudRuleService;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final FraudRuleService fraudRuleService;
    private final FraudCheckResultRepository resultRepository;

    public FraudDetectionServiceImpl(FraudRuleService fraudRuleService,
                                     FraudCheckResultRepository resultRepository) {
        this.fraudRuleService = fraudRuleService;
        this.resultRepository = resultRepository;
    }

    @Override
    public FraudCheckResult checkFraud(Claim claim) {

        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);
        result.setIsFraudulent(false);

        for (FraudRule rule : fraudRuleService.getAllRules()) {
            if ("claimAmount".equals(rule.getConditionField())
                    && claim.getClaimAmount() > Double.parseDouble(rule.getValue())) {

                result.setIsFraudulent(true);
                result.setTriggeredRuleName(rule.getRuleName());
                result.setRejectionReason("Fraud detected");
                break;
            }
        }

        return resultRepository.save(result);
    }
}
