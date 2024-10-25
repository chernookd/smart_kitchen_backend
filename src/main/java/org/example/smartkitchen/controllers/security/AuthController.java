package org.example.smartkitchen.controllers.security;

import lombok.RequiredArgsConstructor;
import org.example.smartkitchen.dto.auth.AuthRequest;
import org.example.smartkitchen.dto.auth.AuthResponse;
import org.example.smartkitchen.security.services.AuthService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@Validated @RequestBody AuthRequest authRequest) {
        return authService.register(authRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@Validated @RequestBody AuthRequest authRequest) {
        return authService.login(authRequest);
    }
}