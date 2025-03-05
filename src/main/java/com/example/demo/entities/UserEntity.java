package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class UserEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String username;
    @Column(columnDefinition = "TEXT")
    public String fullName;
    @Column(columnDefinition = "TEXT")
    public String email;
    @Getter
    String passwordHash;
}
