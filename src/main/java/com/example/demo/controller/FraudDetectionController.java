package com.example.demo.controller;

import com.example.demo.dto.FraudCheckResultDto;
import com.example.demo.model.FraudCheckResult;
import com.example.demo.service.FraudDetectionService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fraud-check")
public class FraudDetectionController {

    private final FraudDetectionService fraudDetectionService;

    // ✅ Constructor injection ONLY
    public FraudDetectionController(FraudDetectionService fraudDetectionService) {
        this.fraudDetectionService = fraudDetectionService;
    }

    // 🔹 POST /api/fraud-check/evaluate/{claimId}
    @PostMapping("/evaluate/{claimId}")
    public FraudCheckResultDto evaluateClaim(@PathVariable Long claimId) {

        FraudCheckResult result = fraudDetectionService.evaluateClaim(claimId);

        return mapToDto(result);
    }

    // 🔹 GET /api/fraud-check/result/claim/{claimId}
    @GetMapping("/result/claim/{claimId}")
    public FraudCheckResultDto getResult(@PathVariable Long claimId) {

        FraudCheckResult result = fraudDetectionService.getResultByClaim(claimId);

        return mapToDto(result);
    }

    // 🔹 Entity → DTO mapper
    private FraudCheckResultDto mapToDto(FraudCheckResult result) {
        FraudCheckResultDto dto = new FraudCheckResultDto();
        dto.setClaimId(result.getClaim().getId());
        dto.setIsFraudulent(result.getIsFraudulent());
        dto.setTriggeredRuleName(result.getTriggeredRuleName());
        dto.setRejectionReason(result.getRejectionReason());
        dto.setCheckedAt(result.getCheckedAt());
        return dto;
    }
}
