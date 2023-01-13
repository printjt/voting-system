package com.example.votingsystem.controller;

import com.example.votingsystem.dto.request.NewCandidateRequest;
import com.example.votingsystem.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@CrossOrigin(maxAge = 900000000)
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping("/candidate")
    @CrossOrigin
    public ResponseEntity<?> candidate(@Valid @RequestBody NewCandidateRequest candidate){
        return ResponseEntity.ok().body(candidateService.save(candidate));
    }

    @DeleteMapping("/candidate/{id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable Long id){
        return ResponseEntity.ok().body(candidateService.deleteCandidate(id));
    }

    @GetMapping("/candidate/{id}")
    @CrossOrigin
    public ResponseEntity<?> candidate(@PathVariable Long id){
        return ResponseEntity.ok().body(candidateService.getCandidate(id));
    }

    @GetMapping("/candidates")
    @CrossOrigin
    public ResponseEntity<?> candidates(){
        return ResponseEntity.ok().body(candidateService.getCandidates());
    }


    @PutMapping("/candidate/{id}")
    @CrossOrigin
    public ResponseEntity<?> candidate(@Valid @RequestBody NewCandidateRequest candidate,@PathVariable Long id){
        return ResponseEntity.ok().body(candidateService.updateCandiate(candidate,id));
    }
}
