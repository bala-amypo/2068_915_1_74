package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.service.FraudDetectionService;
import com.example.demo.service.FraudRuleService;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudRuleService fraudRuleService;
    private final FraudCheckResultRepository resultRepository;

    public FraudDetectionServiceImpl(ClaimRepository claimRepository,
                                     FraudRuleService fraudRuleService,
                                     FraudCheckResultRepository resultRepository) {
        this.claimRepository = claimRepository;
        this.fraudRuleService = fraudRuleService;
        this.resultRepository = resultRepository;
    }

    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

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

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {

        return resultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new RuntimeException("Result not found"));
    }
}
