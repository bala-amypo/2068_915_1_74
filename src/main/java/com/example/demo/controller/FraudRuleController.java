package com.example.demo.controller;

import com.example.demo.dto.FraudRuleDto;
import com.example.demo.model.FraudRule;
import com.example.demo.service.FraudRuleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/rules")
public class FraudRuleController {

    private final FraudRuleService fraudRuleService;

    // ✅ Constructor injection ONLY
    public FraudRuleController(FraudRuleService fraudRuleService) {
        this.fraudRuleService = fraudRuleService;
    }

    // 🔹 POST /api/rules
    @PostMapping
    public FraudRuleDto addRule(@RequestBody FraudRuleDto dto) {

        FraudRule rule = new FraudRule(
                dto.getRuleName(),
                dto.getConditionField(),
                dto.getOperator(),
                dto.getValue(),
                dto.getSeverity()
        );

        FraudRule saved = fraudRuleService.addRule(rule);

        return mapToDto(saved);
    }

    // 🔹 GET /api/rules
    @GetMapping
    public List<FraudRuleDto> getAllRules() {

        return fraudRuleService.getAllRules()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // 🔹 Entity → DTO mapper
    private FraudRuleDto mapToDto(FraudRule rule) {
        FraudRuleDto dto = new FraudRuleDto();
        dto.setId(rule.getId());
        dto.setRuleName(rule.getRuleName());
        dto.setConditionField(rule.getConditionField());
        dto.setOperator(rule.getOperator());
        dto.setValue(rule.getValue());
        dto.setSeverity(rule.getSeverity());
        return dto;
    }
}
