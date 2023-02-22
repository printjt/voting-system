package com.example.votingsystem.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
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



//    public static Map<String, Object> toUserAuth(User user){
//        Map<String, Object> userAuthData = new HashMap<>();
//        userAuthData.put("id",user.getId());
//        userAuthData.put("username",user.getUsername());
//        userAuthData.put("role",user.getRole());
//        userAuthData.put("dateOfBirth",user.getDateOfBirth());
//        userAuthData.put("nationalNumber",user.getNationalNumber());
//        userAuthData.put("fullName",user.getFullName());
//        userAuthData.put("phone",user.getPhone());
//        userAuthData.put("enabled",user.isEnabled());
//        userAuthData.put("notLocked",user.isNotLocked());
//        userAuthData.put("notExpired",user.isNotExpired());
//        return userAuthData;
//    }

}
