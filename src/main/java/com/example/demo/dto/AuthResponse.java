package com.example.demo.dto;

public class AuthResponse {

    // JWT token
    private String token;

    // Logged-in user id
    private Long userId;

    // User email
    private String email;

    // USER or ADMIN
    private String role;

    // ✅ No-arg constructor
    public AuthResponse() {
    }

    // ✅ Parameterized constructor (commonly used in controllers/services)
    public AuthResponse(String token, Long userId, String email, String role) {
        this.token = token;
        this.userId = userId;
        this.email = email;
        this.role = role;
    }

    // ===== Getters & Setters =====

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
