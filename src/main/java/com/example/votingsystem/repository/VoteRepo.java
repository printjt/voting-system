package com.example.votingsystem.repository;

import com.example.votingsystem.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VoteRepo extends JpaRepository<Votes, Long> {

    @Query("SELECT a FROM Votes a WHERE a.nationalId = :nationalId ")
    Optional<Votes> findByNationalId(@Param("nationalId") Long nationalId);
}
