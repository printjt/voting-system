package com.example.votingsystem.repository;

import com.example.votingsystem.model.VoteCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteCycleRepo extends JpaRepository<VoteCycle, Long> {
}
