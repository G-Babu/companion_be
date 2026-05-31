package com.hridaya.backend.auth.service;

import com.hridaya.backend.auth.dto.LoginRequest;
import com.hridaya.backend.auth.dto.LoginResponse;
import com.hridaya.backend.auth.dto.RegisterRequest;
import com.hridaya.backend.auth.dto.RegisterResponse;
import com.hridaya.backend.auth.entity.User;
import com.hridaya.backend.auth.repository.UserRepository;
import com.hridaya.backend.auth.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public RegisterResponse register(RegisterRequest request) {

        boolean userExists = userRepository.findByPhone(request.getPhone()).isPresent();

        if (userExists) {
            throw new RuntimeException("User already exists");
        }

        User user = new User();

        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
         User savedUser = userRepository.save(user);

        return new RegisterResponse(
                savedUser.getId(),
                savedUser.getPhone(),
                savedUser.getName()
        );
    }

    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByPhone(request.getPhone())
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        boolean passwordMatches = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        if (!passwordMatches) {
            throw new RuntimeException(
                    "Invalid password"
            );
        }

        String token = jwtService.generateToken(user.getId());


        return new LoginResponse(
                user.getId(),
                user.getPhone(),
                user.getName(),
                token
        );

    }
}