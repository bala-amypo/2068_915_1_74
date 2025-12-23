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

        // 1. Fetch claim
        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        // 2. Fetch all fraud rules
        List<FraudRule> allRules = fraudRuleRepository.findAll();

        // 3. Simple fraud decision logic
        boolean isFraudulent = !allRules.isEmpty();
        String triggeredRuleName = isFraudulent ? allRules.get(0).getRuleName() : null;
        String rejectionReason = isFraudulent
                ? "Rule triggered: " + triggeredRuleName
                : null;

        // 4. Create result
        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);
        result.setIsFraudulent(isFraudulent);
        result.setTriggeredRuleName(triggeredRuleName);
        result.setRejectionReason(rejectionReason);
        result.setCheckedAt(LocalDateTime.now());

        // 5. Set matched rules
        Set<FraudRule> matchedRules = new HashSet<>(allRules);
        result.setMatchedRules(matchedRules);

        // 6. Link result to claim
        claim.setFraudCheckResult(result);

        // 7. Save result
        return resultRepository.save(result);
    }

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return resultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Fraud check result not found"));
    }
}
