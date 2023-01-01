package com.example.votingsystem.model;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    private String role;
    private String dateOfBirth;
    private String nationalNumber;
    private String fullName;
    private String phone;

    private boolean NotLocked = true;

    private boolean NotExpired = true;

    private boolean enabled = true;

}
