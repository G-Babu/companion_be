package com.hridaya.backend.auth.dto;

public class RegisterResponse {

    private Long id;
    private String phone;
    private String name;

    public RegisterResponse(Long id, String phone, String name) {
        this.id = id;
        this.phone = phone;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }
}