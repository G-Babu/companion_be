package com.hridaya.backend.user.controller;


import com.hridaya.backend.common.dto.ApiResponse;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping("/me")
    public ApiResponse<Object> getCurrentUser(
            Authentication authentication
    ) {

        Long userId =
                (Long) authentication.getPrincipal();

        return new ApiResponse<>(
                200,
                "Current user fetched successfully",
                userId
        );
    }
}