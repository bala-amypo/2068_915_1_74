package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Claim;
import com.example.demo.model.FraudCheckResult;
import com.example.demo.model.FraudRule;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.repository.FraudRuleRepository;
import com.example.demo.service.FraudDetectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudRuleRepository fraudRuleRepository;
    private final FraudCheckResultRepository resultRepository;

    public FraudDetectionServiceImpl(
            ClaimRepository claimRepository,
            FraudRuleRepository fraudRuleRepository,
            FraudCheckResultRepository resultRepository) {
        this.claimRepository = claimRepository;
        this.fraudRuleRepository = fraudRuleRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        List<FraudRule> rules = fraudRuleRepository.findAll();
        Set<FraudRule> matchedRules = new HashSet<>(rules);

        // ✅ Fraud depends ONLY on matchedRules
        boolean isFraud = !matchedRules.isEmpty();

        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);
        result.setMatchedRules(matchedRules);
        result.setCheckedAt(LocalDateTime.now());
        result.setIsFraudulent(isFraud);

        // ✅ Derived fields (do NOT break 3NF)
        if (isFraud) {
            FraudRule firstRule = matchedRules.iterator().next();
            result.setTriggeredRuleName(firstRule.getRuleName());
            result.setRejectionReason("Rule triggered: " + firstRule.getRuleName());
        } else {
            result.setTriggeredRuleName(null);
            result.setRejectionReason(null);
        }

        claim.setFraudCheckResult(result);

        return resultRepository.save(result);
    }

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return resultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}
