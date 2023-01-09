package com.example.votingsystem.repository;

import com.example.votingsystem.model.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, Long> {
}
