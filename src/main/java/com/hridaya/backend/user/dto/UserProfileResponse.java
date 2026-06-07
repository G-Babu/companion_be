package com.hridaya.backend.user.dto;


public record UserProfileResponse(
        Long id,
        String phone,
        String name,
        Integer age,
        String gender,
        Boolean profileCompleted
) {
}