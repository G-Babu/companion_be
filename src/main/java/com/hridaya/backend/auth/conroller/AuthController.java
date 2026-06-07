package com.hridaya.backend.auth.conroller;
import com.hridaya.backend.auth.dto.*;
import com.hridaya.backend.auth.entity.User;
import com.hridaya.backend.auth.service.AuthService;
import com.hridaya.backend.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ApiResponse<RegisterResponse> register(
          @RequestBody  RegisterRequest request
    ) {

        RegisterResponse response = authService.register(request);

        return new ApiResponse<>(
                200,
                "User registered Successfully",
                response
        );
    }

    @PostMapping ("/login")
    public ApiResponse<LoginResponse> login(
         @RequestBody   LoginRequest request
    ){
        LoginResponse response = authService.login(request);

        return new ApiResponse<>(
                200,
                "Login successful",
                response
        );

    }


    @PostMapping("/send-otp")
    public ApiResponse<Object> sendOtp(
            @RequestBody SendOtpRequest request
    ) {

        authService.sendOtp(
                request.getPhone()
        );

        return new ApiResponse<>(
                200,
                "OTP sent successfully",
                null
        );
    }

    @PostMapping("/verify-otp")
    public ApiResponse<OtpLoginResponse> verifyOtp(
            @RequestBody VerifyOtpRequest request
    ) {

        OtpLoginResponse response =
                authService.verifyOtp(request);

        return new ApiResponse<>(
                200,
                "OTP verified successfully",
                response
        );
    }


}