package com.example.demo.controller;

import com.example.demo.dto.PolicyDto;
import com.example.demo.model.Policy;
import com.example.demo.service.PolicyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/policies")
public class PolicyController {

    private final PolicyService policyService;

    // ✅ Constructor injection ONLY
    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    // 🔹 POST /api/policies/{userId}
    @PostMapping("/{userId}")
    public PolicyDto createPolicy(@PathVariable Long userId,
                                  @RequestBody PolicyDto dto) {

        Policy policy = new Policy(
                null,
                dto.getPolicyNumber(),
                dto.getPolicyType(),
                dto.getStartDate(),
                dto.getEndDate()
        );

        Policy saved = policyService.createPolicy(userId, policy);

        return mapToDto(saved);
    }

    // 🔹 GET /api/policies/user/{userId}
    @GetMapping("/user/{userId}")
    public List<PolicyDto> getPoliciesByUser(@PathVariable Long userId) {

        return policyService.getPoliciesByUser(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 🔹 Entity → DTO mapper
    private PolicyDto mapToDto(Policy policy) {
        PolicyDto dto = new PolicyDto();
        dto.setId(policy.getId());
        dto.setUserId(policy.getUser().getId());
        dto.setPolicyNumber(policy.getPolicyNumber());
        dto.setPolicyType(policy.getPolicyType());
        dto.setStartDate(policy.getStartDate());
        dto.setEndDate(policy.getEndDate());
        return dto;
    }
}
