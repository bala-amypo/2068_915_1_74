package com.example.demo.service.impl;

import com.example.demo.model.Policy;
import com.example.demo.repository.PolicyRepository;
import com.example.demo.service.PolicyService;
import org.springframework.stereotype.Service;

@Service
public class PolicyServiceImpl implements PolicyService {

    private final PolicyRepository policyRepository;

    public PolicyServiceImpl(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public Policy createPolicy(Policy policy) {
        return policyRepository.save(policy);
    }
}
