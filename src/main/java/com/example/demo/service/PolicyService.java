package com.example.demo.service;

import com.example.demo.model.Policy;
import com.example.demo.model.User;
import com.example.demo.repository.PolicyRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final UserRepository userRepository;

    // MUST be in this order
    public PolicyService(PolicyRepository policyRepository, UserRepository userRepository) {
        this.policyRepository = policyRepository;
        this.userRepository = userRepository;
    }

    public Policy createPolicy(Policy policy) {

        if (policy.getEndDate().isBefore(policy.getStartDate())) {
            throw new RuntimeException("invalid policy dates");
        }

        return policyRepository.save(policy);
    }

    public List<Policy> getPoliciesByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return policyRepository.findAll()
                .stream()
                .filter(p -> p.getUser().getId().equals(user.getId()))
                .toList();
    }
}
