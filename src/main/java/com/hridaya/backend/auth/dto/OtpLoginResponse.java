package com.hridaya.backend.auth.dto;


public class OtpLoginResponse {

    private Long id;
    private String phone;
    private String token;
    private boolean newUser;

    public OtpLoginResponse(
            Long id,
            String phone,
            String token,
            boolean newUser
    ) {
        this.id = id;
        this.phone = phone;
        this.token = token;
        this.newUser = newUser;
    }

    public Long getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public String getToken() {
        return token;
    }

    public boolean isNewUser() {
        return newUser;
    }
}