package com.example.demo.repository;

import com.example.demo.model.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PolicyRepository extends JpaRepository<Policy, Long> {

    // Lookup policy by policy number
    Optional<Policy> findByPolicyNumber(String policyNumber);

    // Check uniqueness of policy number
    boolean existsByPolicyNumber(String policyNumber);

    // List policies for a specific user
    List<Policy> findByUserId(Long userId);
}
