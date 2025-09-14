package com.emtech.logistics.service;

import com.emtech.logistics.dto.AuthResponse;
import com.emtech.logistics.dto.LoginRequest;
import com.emtech.logistics.entity.User;
import com.emtech.logistics.repository.UserRepository;
import com.emtech.logistics.security.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthResponse login(LoginRequest loginRequest) throws Exception {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new Exception("User not found"));

        String token = jwtTokenUtil.generateToken(user.getUsername());

        return new AuthResponse(token, user.getEmail(), user.getFullName(), user.getRole().name());
    }

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}
