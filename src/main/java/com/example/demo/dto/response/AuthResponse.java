package com.example.demo.dto.response;

public class AuthResponse {
    private Long id;
    private String fullName;

    public AuthResponse(Long id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return fullName;
    }
}