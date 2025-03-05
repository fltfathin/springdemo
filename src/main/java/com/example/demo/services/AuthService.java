package com.example.demo.services;


import com.example.demo.controllers.RegisterDto;
import com.example.demo.controllers.UserLoginResponseDto;
import com.example.demo.controllers.UserRegisterDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserLoginResponseDto login(String username, String password) {
        UserEntity userEntity = userRepository.getByUsername(username);
        if (userEntity == null) {
            return null;
        }
//        TODO: compare password hash with the one on db
        SecretKey key = Jwts.SIG.HS256.key().build();
        String token = Jwts.builder()
                .subject(userEntity.username)
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24))
                //.encryptWith() // optionally encrypt the claim
                .signWith(key).compact();
        return new UserLoginResponseDto() {
            {
                setToken(token);
                setUsername(username);
            }
        };
    }

    public UserRegisterDto register(RegisterDto registerDto) {
        return null;
    }
}
