package com.example.demo.dto;

public class AuthRequest {

    // Used only during registration
    private String name;

    // Login / registration email
    private String email;

    // Raw password entered by user
    private String password;

    // ✅ No-arg constructor
    public AuthRequest() {
    }

    // ===== Getters & Setters =====

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    // Raw password; hashing is done in service layer
    public void setPassword(String password) {
        this.password = password;
    }
}
