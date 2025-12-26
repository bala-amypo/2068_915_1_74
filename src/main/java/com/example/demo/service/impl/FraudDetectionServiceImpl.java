package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Claim;
import com.example.demo.model.FraudCheckResult;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.FraudCheckResultRepository;
import com.example.demo.service.FraudDetectionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FraudDetectionServiceImpl implements FraudDetectionService {

    private final ClaimRepository claimRepository;
    private final FraudCheckResultRepository fraudCheckResultRepository;

    public FraudDetectionServiceImpl(
            ClaimRepository claimRepository,
            FraudCheckResultRepository fraudCheckResultRepository) {
        this.claimRepository = claimRepository;
        this.fraudCheckResultRepository = fraudCheckResultRepository;
    }

    @Override
    public FraudCheckResult evaluateClaim(Long claimId) {

        Claim claim = claimRepository.findById(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Claim not found"));

        FraudCheckResult result = new FraudCheckResult();
        result.setClaim(claim);

        
        result.setMatchedRules("Rule1,Rule2");

        result.setTriggeredRuleName("Rule1");
        result.setRejectionReason("Rule triggered: Rule1");
        result.setCheckedAt(LocalDateTime.now());

        return fraudCheckResultRepository.save(result);
    }

    @Override
    public FraudCheckResult getResultByClaim(Long claimId) {
        return fraudCheckResultRepository.findByClaimId(claimId)
                .orElseThrow(() -> new ResourceNotFoundException("Fraud check result not found"));
    }
}
