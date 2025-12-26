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
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudRuleRepository fraudRuleRepository;
    private final FraudCheckResultRepository fraudCheckResultRepository;

    public FraudDetectionServiceImpl(
            ClaimRepository claimRepository,
            FraudRuleRepository fraudRuleRepository,
            FraudCheckResultRepository fraudCheckResultRepository
    ) {
        this.claimRepository = claimRepository;
        this.fraudRuleRepository = fraudRuleRepository;
        this.fraudCheckResultRepository = fraudCheckResultRepository;
    }

    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        List<FraudRule> rules = fraudRuleRepository.findAll();

        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);
        result.setCheckedAt(LocalDateTime.now());

        if (!rules.isEmpty()) {
            String ruleNames = rules.stream()
                    .map(FraudRule::getRuleName)
                    .collect(Collectors.joining(","));

            result.setMatchedRules(ruleNames);  // ✅ STRING
            result.setTriggeredRuleName(rules.get(0).getRuleName());
            result.setRejectionReason("Rule triggered");
        }

        claim.setFraudCheckResult(result);
        return fraudCheckResultRepository.save(result);
    }

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return fraudCheckResultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Fraud result not found"));
    }
}
