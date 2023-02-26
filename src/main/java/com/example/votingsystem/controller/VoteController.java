package com.example.votingsystem.controller;

import com.example.votingsystem.dto.request.VoteRequest;
import com.example.votingsystem.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping("/vote")
    @CrossOrigin
    public ResponseEntity<?> vote(@Valid @RequestBody VoteRequest voteRequest) throws ParseException {
        return ResponseEntity.ok().body(voteService.vote(voteRequest));
    }

    @GetMapping("/vote/{voteId}")
    @CrossOrigin
    public ResponseEntity<?> vote(@PathVariable Long voteId){
        return ResponseEntity.ok().body(voteService.getVote(voteId));
    }

    @GetMapping("/vote/confirm/{id}")
    @CrossOrigin
    public ResponseEntity<?> confirmVote(@PathVariable String id){
        return ResponseEntity.ok().body(voteService.confirmVote(id));
    }

    @DeleteMapping("/vote/delete/{id}")
    @CrossOrigin
    public ResponseEntity<?> deleteVote(@PathVariable String id){
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

    @GetMapping("/vote/username/{username}")
    @CrossOrigin
    public ResponseEntity<?> checkinUserDataVote(@PathVariable String username){
        return ResponseEntity.ok().body(voteService.checkUserVoteByUsername(username));
    }




    //checkUserIdVote
}
