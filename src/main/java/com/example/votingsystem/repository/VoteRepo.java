package com.example.votingsystem.repository;

import com.example.votingsystem.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepo extends JpaRepository<Votes, Long> {

    @Query("SELECT a FROM Votes a WHERE a.nationalId = :nationalId ")
    Optional<Votes> findByNationalId(@Param("nationalId") Long nationalId);

    @Query("SELECT a FROM Votes a WHERE a.username = :username ")
    Optional<Votes> findByUsername(@Param("username") String username);

    @Query("SELECT a FROM Votes a WHERE a.uniqueId = :uniqueId ")
    Optional<Votes> findByUniqueId(@Param("uniqueId") String uniqueId);


    @Query("SELECT a FROM Votes a WHERE a.uniqueId = :uniqueId  OR a.username= :uniqueId")
    Optional<Votes> findByUniqueIdOrUsername(String uniqueId);
}
