package com.example.demo.controller;

import com.example.demo.dto.ClaimDto;
import com.example.demo.model.Claim;
import com.example.demo.service.ClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/claims")
public class ClaimController {

    private final ClaimService claimService;

    // ✅ Constructor injection ONLY
    public ClaimController(ClaimService claimService) {
        this.claimService = claimService;
    }

    // 🔹 POST /api/claims/{policyId}
    @PostMapping("/{policyId}")
    public ClaimDto createClaim(@PathVariable Long policyId,
                                @RequestBody ClaimDto dto) {

        Claim claim = new Claim(
                null,
                dto.getClaimDate(),
                dto.getClaimAmount(),
                dto.getDescription()
        );

        Claim saved = claimService.createClaim(policyId, claim);

        return mapToDto(saved);
    }

    // 🔹 GET /api/claims/{id}
    @GetMapping("/{id}")
    public ClaimDto getClaim(@PathVariable Long id) {
        return mapToDto(claimService.getClaim(id));
    }

    // 🔹 GET /api/claims
    @GetMapping
    public List<ClaimDto> getAllClaims() {
        return claimService.getAllClaims()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 🔹 Entity → DTO mapper
    private ClaimDto mapToDto(Claim claim) {
        ClaimDto dto = new ClaimDto();
        dto.setId(claim.getId());
        dto.setPolicyId(claim.getPolicy().getId());
        dto.setClaimDate(claim.getClaimDate());
        dto.setClaimAmount(claim.getClaimAmount());
        dto.setDescription(claim.getDescription());
        dto.setStatus(claim.getStatus());
        return dto;
    }
}
