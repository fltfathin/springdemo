package com.example.demo.services;

import com.example.demo.controllers.UserDetailsDto;
import com.example.demo.entities.UserEntity;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetailsDto getProfile(String username) {
        UserEntity user = userRepository.getByUsername(username);

        return new UserDetailsDto(){{
            setUsername(user.username);
            setEmail(user.email);
            setName(user.fullName);
        }};
    }
}
