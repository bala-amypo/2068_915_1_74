package com.example.demo.service.impl;

import com.example.demo.model.Claim;
import com.example.demo.repository.ClaimRepository;
import com.example.demo.service.ClaimService;
import org.springframework.stereotype.Service;

@Service
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }
}
