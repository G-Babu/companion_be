package com.hridaya.backend.user.controller;


import com.hridaya.backend.common.dto.ApiResponse;

import com.hridaya.backend.user.dto.UpdateProfileRequest;
import com.hridaya.backend.user.dto.UserProfileResponse;
import com.hridaya.backend.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ApiResponse<UserProfileResponse> getCurrentUser(
            Authentication authentication
    ) {

        Long userId =
                (Long) authentication.getPrincipal();

        UserProfileResponse response =
                userService.getCurrentUser(
                        userId
                );

        return new ApiResponse<>(
                200,
                "Current user fetched successfully",
                response
        );
    }

    @PutMapping("/profile")
    public ApiResponse<UserProfileResponse> updateProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request
    ) {

        Long userId =
                (Long) authentication.getPrincipal();

        UserProfileResponse response =
                userService.updateProfile(
                        userId,
                        request
                );

        return new ApiResponse<>(
                200,
                "Profile updated successfully",
                response
        );
    }
}