package com.example.votingsystem.controller;

import com.example.votingsystem.dto.request.VoteRequest;
import com.example.votingsystem.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/vote")
    public ResponseEntity<?> vote(@Valid @RequestBody VoteRequest voteRequest){
        return ResponseEntity.ok().body(voteService.vote(voteRequest));
    }

    @GetMapping("/vote/{voteId}")
    public ResponseEntity<?> vote(@PathVariable Long voteId){
        return ResponseEntity.ok().body(voteService.getVote(voteId));
    }

    @GetMapping("/vote/confirm/{id}")
    public ResponseEntity<?> confirmVote(@PathVariable Long id){
        return ResponseEntity.ok().body(voteService.confirmVote(id));
    }
}
