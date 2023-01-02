package com.example.votingsystem.service;

import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.model.VoteCycle;
import com.example.votingsystem.repository.VoteCycleRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@RequiredArgsConstructor
public class VotingCycleService {
    VoteCycleRepo voteCycleRepo;

    public GeneralResponse startCycle(VoteCycle voteCycle) throws Exception {
       List<VoteCycle> voteCycleList= voteCycleRepo.findAll();
       if(voteCycleList.size()>0){
           return new GeneralResponse(-200,"Vote Cycle already started","Vote Cycle already started");
       }else {
           voteCycleRepo.save(voteCycle);
           return new GeneralResponse(0,"Success",voteCycle);

       }
       // return ResponseEntity.ok().body(.startCycle(voteCycle));

    }
}
