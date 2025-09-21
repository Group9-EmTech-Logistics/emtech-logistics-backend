package com.emtech.logistics.controller;

import com.emtech.logistics.dto.AuthResponse;
import com.emtech.logistics.dto.LoginRequest;
import com.emtech.logistics.entity.User;
import com.emtech.logistics.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    /**
     * ✅ User login - returns JWT token + user details
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        AuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * ✅ User registration - creates new account and returns response
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        AuthResponse response = authService.register(user);
        return ResponseEntity.ok(response);
    }
}
