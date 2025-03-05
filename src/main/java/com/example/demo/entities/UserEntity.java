package com.example.demo.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Setter
    String passwordHash;
}
