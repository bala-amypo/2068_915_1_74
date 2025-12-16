package com.example.demo.dto;

public class ApiErrorResponse {

    private int status;
    private String message;

    public ApiErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    
}
