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
    @CrossOrigin
    public ResponseEntity<?> vote(@Valid @RequestBody VoteRequest voteRequest){
        return ResponseEntity.ok().body(voteService.vote(voteRequest));
    }

    @GetMapping("/vote/{voteId}")
    @CrossOrigin
    public ResponseEntity<?> vote(@PathVariable Long voteId){
        return ResponseEntity.ok().body(voteService.getVote(voteId));
    }

    @GetMapping("/vote/confirm/{id}")
    @CrossOrigin
    public ResponseEntity<?> confirmVote(@PathVariable Long id){
        return ResponseEntity.ok().body(voteService.confirmVote(id));
    }

    @DeleteMapping("/vote/confirm/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteVote(@PathVariable Long id){
        return ResponseEntity.ok().body(voteService.deleteVote(id));
    }

    @GetMapping("/vote/check-user-with-uniqueId/{uniqueId}")
    @CrossOrigin
    public ResponseEntity<?> checkUserDataWithUniqueId(@PathVariable String uniqueId){
        return ResponseEntity.ok().body(voteService.getUserDataWithUniqueId(uniqueId));
    }

    @GetMapping("/vote/check-user/{id}")
    @CrossOrigin
    public ResponseEntity<?> checkinUserDataVote(@PathVariable Long id){
        return ResponseEntity.ok().body(voteService.checkUserIdVote(id));
    }
    //checkUserIdVote
}
