package com.example.demo.dto;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private LocalDateTime timestamp;
    private String message;
    private String details;

    // ✅ No-arg constructor
    public ApiErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    // ✅ Full-arg constructor (matches GlobalExceptionHandler)
    public ApiErrorResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters & Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
