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

    public FraudDetectionServiceImpl(ClaimRepository claimRepository,
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

        List<FraudRule> allRules = fraudRuleRepository.findAll();

        // Check if any rules exist and mark as fraud if so
        boolean isFraud = !allRules.isEmpty();

        // Create the result object using the updated constructor
        // triggeredRuleName and rejectionReason are now handled via the matchedRules association
        FraudCheckResult result = new FraudCheckResult(
                claim,
                isFraud,
                LocalDateTime.now() // always non-null
        );

        // Include all rules as matched if fraud check is true, otherwise empty set
        Set<FraudRule> matchedRules = isFraud ? new HashSet<>(allRules) : new HashSet<>();
        
        result.setMatchedRules(matchedRules);
        claim.setFraudCheckResult(result); // maintain bidirectional mapping

        return resultRepository.save(result);
    }

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return resultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Result not found"));
    }
}
