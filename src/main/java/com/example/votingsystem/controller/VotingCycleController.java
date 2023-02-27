package com.example.votingsystem.controller;

import com.example.votingsystem.dto.request.LoginRequest;
import com.example.votingsystem.dto.request.voteCycleRequest;
import com.example.votingsystem.model.VoteCycle;
import com.example.votingsystem.service.VotingCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class VotingCycleController {
    @Autowired
    private final VotingCycleService votingCycleService;


    @PostMapping("/start-cycle")
    @CrossOrigin
    public ResponseEntity cycle(@Valid @RequestBody voteCycleRequest voteCycle) throws Exception {
        return ResponseEntity.ok().body(votingCycleService.startCycle(voteCycle));
    }


    @DeleteMapping("/end-cycle")
    @CrossOrigin
    public ResponseEntity deleteCycle() throws Exception {
        return ResponseEntity.ok().body(votingCycleService.endCycle());
    }

    @GetMapping("/check-cycle")
    @CrossOrigin
    public ResponseEntity checkCycle() throws Exception {
        return ResponseEntity.ok().body(votingCycleService.checkCycle());
    }


}
