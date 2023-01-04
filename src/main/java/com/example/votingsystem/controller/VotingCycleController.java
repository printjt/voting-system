package com.example.votingsystem.controller;

import com.example.votingsystem.dto.request.LoginRequest;
import com.example.votingsystem.model.VoteCycle;
import com.example.votingsystem.service.VotingCycleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
public class VotingCycleController {
    VotingCycleService votingCycleService;


    @PostMapping("/start-cycle")
    @CrossOrigin
    public ResponseEntity cycle(@Valid @RequestBody VoteCycle voteCycle) throws Exception {
        return ResponseEntity.ok().body(votingCycleService.startCycle(voteCycle));
    }


    @DeleteMapping("/start-cycle")
    @CrossOrigin
    public ResponseEntity deleteCycle() throws Exception {
        return ResponseEntity.ok().body(votingCycleService.endCycle());
    }
}
