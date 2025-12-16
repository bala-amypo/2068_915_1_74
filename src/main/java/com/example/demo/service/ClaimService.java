package com.example.demo.service;

import com.example.demo.model.Claim;
import com.example.demo.model.Policy;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.repository.PolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClaimService {

    private final ClaimRepository claimRepository;
    private final PolicyRepository policyRepository;

    public ClaimService(ClaimRepository claimRepository, PolicyRepository policyRepository) {
        this.claimRepository = claimRepository;
        this.policyRepository = policyRepository;
    }

    public Claim createClaim(Claim claim) {

        if (claim.getClaimAmount() <= 0) {
            throw new RuntimeException("invalid claim amount");
        }

        if (claim.getClaimDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("invalid claim date");
        }

        return claimRepository.save(claim);
    }

    public Claim getClaim(Long claimId) {
        return claimRepository.findById(claimId)
                .orElseThrow(() -> new RuntimeException("Claim not found"));
    }

    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }
}
