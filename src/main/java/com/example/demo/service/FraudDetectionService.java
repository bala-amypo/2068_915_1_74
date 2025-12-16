package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

@Service
public class FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudRuleRepository fraudRuleRepository;
    private final FraudCheckResultRepository fraudCheckResultRepository;

   
    public FraudDetectionService(
            ClaimRepository claimRepository,
            FraudRuleRepository fraudRuleRepository,
            FraudCheckResultRepository fraudCheckResultRepository) {

        this.claimRepository = claimRepository;
        this.fraudRuleRepository = fraudRuleRepository;
        this.fraudCheckResultRepository = fraudCheckResultRepository;
    }

    public FraudCheckResult evaluateClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));

        for (FraudRule rule : fraudRuleRepository.findAll()) {

            if ("claimAmount".equals(rule.getConditionField())) {

                double ruleValue = Double.parseDouble(rule.getValue());

                if (claim.getClaimAmount() > ruleValue) {

                    FraudCheckResult result = new FraudCheckResult();
                    result.setClaim(claim);
                    result.setIsFraudulent(true);
                    result.setTriggeredRuleName(rule.getRuleName());
                    result.setRejectionReason("Rule matched");
                    return fraudCheckResultRepository.save(result);
                }
            }
        }

        FraudCheckResult clean = new FraudCheckResult();
        clean.setClaim(claim);
        clean.setIsFraudulent(false);
        return fraudCheckResultRepository.save(clean);
    }

    public FraudCheckResult getResultByClaim(Long claimId) {
        return fraudCheckResultRepository.findByClaimId(claimId);
    }
}
