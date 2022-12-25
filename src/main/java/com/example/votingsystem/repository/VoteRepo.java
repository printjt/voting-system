package com.example.votingsystem.repository;

import com.example.votingsystem.model.Votes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepo extends JpaRepository<Votes, Long> {
}
