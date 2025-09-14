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
     * User login endpoint
     */
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) throws Exception {
        AuthResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * User registration endpoint
     */
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody User user) {
        User newUser = authService.register(user);
        AuthResponse response = new AuthResponse(
                null,
                newUser.getEmail(),
                newUser.getFullName(),
                newUser.getRole().name()
        );
        return ResponseEntity.ok(response);
    }
}
