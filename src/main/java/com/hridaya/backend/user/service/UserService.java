package com.hridaya.backend.user.service;


import com.hridaya.backend.auth.entity.User;
import com.hridaya.backend.auth.repository.UserRepository;
import com.hridaya.backend.user.dto.UpdateProfileRequest;
import com.hridaya.backend.user.dto.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserProfileResponse getCurrentUser(
            Long userId
    ) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        return new UserProfileResponse(
                user.getId(),
                user.getPhone(),
                user.getName(),
                user.getAge(),
                user.getGender(),
                user.getProfileCompleted()
        );
    }


    public UserProfileResponse updateProfile(
            Long userId,
            UpdateProfileRequest request
    ) {

        User user = userRepository
                .findById(userId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "User not found"
                        ));

        user.setName(
                request.getName()
        );

        user.setAge(
                request.getAge()
        );

        user.setGender(
                request.getGender()
        );

        user.setProfileCompleted(
                true
        );

        User savedUser =
                userRepository.save(user);

        return new UserProfileResponse(
                savedUser.getId(),
                savedUser.getPhone(),
                savedUser.getName(),
                user.getAge(),
                user.getGender(),
                savedUser.getProfileCompleted()
        );
    }
}