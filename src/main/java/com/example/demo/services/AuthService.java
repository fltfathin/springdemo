package com.example.demo.services;


import com.example.demo.controllers.RegisterDto;
import com.example.demo.controllers.UserLoginResponseDto;
import com.example.demo.controllers.UserRegisterDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
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
        Pbkdf2PasswordEncoder encoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        boolean pass_compare = encoder.matches(password, userEntity.getPasswordHash());
        if (!pass_compare) {
            return null;
        }
        SecretKey key = Jwts.SIG.HS256.key().build();
        String token = Jwts.builder().subject(userEntity.username).issuedAt(new Date()).expiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 24)).signWith(key).compact();
        return new UserLoginResponseDto(username, token);
    }

    public UserRegisterDto register(RegisterDto registerDto) {
//      save the new user
        UserEntity userEntity = new UserEntity();
        userEntity.username = registerDto.username;
        userEntity.email = registerDto.email;
        userEntity.fullName = registerDto.fullName;
        userEntity.setPasswordHash(generateHash(registerDto.password));

        userRepository.save(userEntity);

        return new UserRegisterDto(registerDto.username);
    }

    String generateHash(String password) {
        Pbkdf2PasswordEncoder encoder = Pbkdf2PasswordEncoder.defaultsForSpringSecurity_v5_8();
        return encoder.encode(password);
    }
}
