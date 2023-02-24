package com.example.votingsystem.repository;


import com.example.votingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {

    Optional<User> findByUsernameOrNationalNumber(String username, String nationalNumber);

    Optional<User> findByUsername(String username);

    Optional<User> findByNationalNumber(String nationalNumber);


    @Query("SELECT user FROM User user WHERE user.role = :role")
    List<User> findAllByRole(String role);
}
