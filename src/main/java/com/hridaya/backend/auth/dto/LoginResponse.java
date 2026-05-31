package com.hridaya.backend.auth.dto;


public class LoginResponse {

    private Long id;
    private String phone;
    private String name;
    private String token;

    public LoginResponse(
            Long id,
            String phone,
            String name,
            String token
    ) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.token = token;
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

    public String getToken() {
        return token;
    }
}
