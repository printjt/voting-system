package com.example.votingsystem.controller;

import com.example.votingsystem.dto.request.NewCandidateRequest;
import com.example.votingsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/candidate")
    public ResponseEntity<?> candidate(@Valid @RequestBody NewCandidateRequest candidate){
        return ResponseEntity.ok().body(candidateService.save(candidate));
    }

    @GetMapping("/candidate/{id}")
    public ResponseEntity<?> candidate(@PathVariable Long id){
        return ResponseEntity.ok().body(candidateService.getCandidate(id));
    }

    @GetMapping("/candidates")
    public ResponseEntity<?> candidates(){
        return ResponseEntity.ok().body(candidateService.getCandidates());
    }


}
