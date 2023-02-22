package com.example.votingsystem.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class Votes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    private Candidate candidateId;

    private String username;

    private Long nationalId;

    private String uniqueId;

    private boolean confirmed;
}
