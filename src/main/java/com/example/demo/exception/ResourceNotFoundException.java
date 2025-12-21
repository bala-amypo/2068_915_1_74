package com.example.demo.exception;

/**
 * Thrown when a requested resource is not found.
 * Example messages:
 *  - "User not found"
 *  - "Policy not found"
 *  - "Claim not found"
 *  - "Fraud rule not found"
 *  - "Result not found"
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
