package com.example.votingsystem.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
@Accessors(chain = true)
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String party;

    private int votes;

    @Lob
    private byte[] image;

    private String description;


}
