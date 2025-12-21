package com.example.demo.service;

import com.example.demo.model.FraudCheckResult;

public interface FraudDetectionService {

    /**
     * Evaluate a claim for fraud using all defined rules.
     *
     * @param claimId id of the claim
     * @return FraudCheckResult with isFraudulent, triggeredRuleName, rejectionReason, checkedAt, matchedRules
     * @throws ResourceNotFoundException if claim is not found
     */
    FraudCheckResult evaluateClaim(Long claimId);

    /**
     * Retrieve previously evaluated fraud result by claim id.
     *
     * @param claimId id of the claim
     * @return FraudCheckResult
     * @throws ResourceNotFoundException if result is not found
     */
    FraudCheckResult getResultByClaim(Long claimId);
}
