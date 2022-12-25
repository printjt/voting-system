package com.example.votingsystem.service;

import com.example.votingsystem.config.Constant;
import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.dto.request.NewCandidateRequest;
import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.repository.CandidateRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepo candidateRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    public GeneralResponse getCandidate(Long id) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(id);
        return optionalCandidate.map(candidate -> new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, candidate)).orElseGet(() -> new GeneralResponse(Constant.ResponseCode.CandidateNotFound.code, Constant.ResponseCode.CandidateNotFound.msg, null));
    }

    public GeneralResponse getCandidates() {
        return new GeneralResponse(Constant.ResponseCode.Success.code,Constant.ResponseCode.Success.msg,candidateRepo.findAll());
    }

    public GeneralResponse save(NewCandidateRequest candidate) {
        return checkCandidateExist(candidate.getName()) ?  new GeneralResponse(Constant.ResponseCode.CandidateAlreadyExists.code, Constant.ResponseCode.CandidateAlreadyExists.msg, null) : new GeneralResponse(Constant.ResponseCode.Success.code,Constant.ResponseCode.Success.msg,candidateRepo.save(modelMapper.map(candidate, Candidate.class)));
    }

    private boolean checkCandidateExist(String name){
        return candidateRepo.findByName(name).isPresent();
    }


}
