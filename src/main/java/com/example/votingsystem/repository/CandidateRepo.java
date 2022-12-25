package com.example.votingsystem.repository;

import com.example.votingsystem.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {

    Optional<Candidate> findByName(String name);
}
