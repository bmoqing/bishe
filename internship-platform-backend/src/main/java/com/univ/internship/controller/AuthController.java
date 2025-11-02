package com.univ.internship.controller;

import com.univ.internship.common.ApiResponse;
import com.univ.internship.dto.LoginRequest;
import com.univ.internship.dto.RegisterRequest;
import com.univ.internship.model.User;
import com.univ.internship.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@RequestBody @Valid LoginRequest req) {
        String token = authService.login(req.getUsername(), req.getPassword());
        return ApiResponse.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ApiResponse<User> register(@RequestBody @Valid RegisterRequest req) {
        User u = authService.register(req.getUsername(), req.getPassword(), req.getRealName(), req.getRole());
        return ApiResponse.ok(u);
    }

    @GetMapping("/me")
    public ApiResponse<String> me() {
        return ApiResponse.ok("ok");
    }
}
