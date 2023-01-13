package com.example.votingsystem.service;

import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.dto.request.voteCycleRequest;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.VoteCycle;
import com.example.votingsystem.repository.VoteCycleRepo;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VotingCycleService {
    private final VoteCycleRepo voteCycleRepo;

    private final ModelMapper modelMapper = new ModelMapper();


    public GeneralResponse startCycle(voteCycleRequest voteCycle) throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        System.out.println("voteCycle = " + voteCycle.toString());
       List<VoteCycle> voteCycleList= voteCycleRepo.findAll();
       if(voteCycleList.isEmpty()){
           VoteCycle voteCycle1= modelMapper.map(voteCycle, VoteCycle.class);
           voteCycleRepo.save(voteCycle1);
           return new GeneralResponse(0,"Success",voteCycle);
       }else {
           return new GeneralResponse(-200,"Vote Cycle already started","Vote Cycle already started");


       }
       // return ResponseEntity.ok().body(.startCycle(voteCycle));

    }


    public GeneralResponse checkCycle() throws Exception {
        System.out.println("@@@@@@@@@@@@@@@@@@@");

        List<VoteCycle> voteCycleList= voteCycleRepo.findAll();
        if(!voteCycleList.isEmpty()){
            return new GeneralResponse(0,"Success","Vote Cycle has started");
        }else {
            return new GeneralResponse(-20,"Fail","No Cycle Started");
        }
        // return ResponseEntity.ok().body(.startCycle(voteCycle));

    }

     public GeneralResponse endCycle() throws Exception {
        List<VoteCycle> voteCycleList= voteCycleRepo.findAll();
        if(voteCycleList.isEmpty()){
            return new GeneralResponse(-10,"Vote Cycle already empty","Vote Cycle already empty");
        }else {
            voteCycleRepo.deleteAll();

            return new GeneralResponse(0,"Success",null);

        }
        // return ResponseEntity.ok().body(.startCycle(voteCycle));

    }
}
