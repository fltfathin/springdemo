package com.example.demo.controllers;

import com.example.demo.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    ResponseEntity<UserLoginResponseDto> login(@RequestBody AuthLoginDto authLoginDto) {
        return ResponseEntity.ok(authService.login(authLoginDto.username, authLoginDto.password));
    }

    @PostMapping("/auth/register")
    ResponseEntity<UserRegisterDto> register(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(authService.register(registerDto));
    }
}
