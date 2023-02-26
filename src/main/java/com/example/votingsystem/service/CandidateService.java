package com.example.votingsystem.service;

import com.example.votingsystem.config.Constant;
import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.dto.request.NewCandidateRequest;
import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.model.Votes;
import com.example.votingsystem.repository.CandidateRepo;
import com.example.votingsystem.repository.VoteRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepo candidateRepo;
    private final VoteRepo voteRepo;
    private final ModelMapper modelMapper = new ModelMapper();

    public GeneralResponse getCandidate(Long id) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(id);
        return optionalCandidate.map(candidate -> new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, candidate)).orElseGet(() -> new GeneralResponse(Constant.ResponseCode.CandidateNotFound.code, Constant.ResponseCode.CandidateNotFound.msg, null));
    }

    public GeneralResponse getCandidates() {
        return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, candidateRepo.findAll());
    }

    public GeneralResponse save(NewCandidateRequest candidate) {
        return checkCandidateExist(candidate.getName()) ? new GeneralResponse(Constant.ResponseCode.CandidateAlreadyExists.code, Constant.ResponseCode.CandidateAlreadyExists.msg, null) : new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, candidateRepo.save(modelMapper.map(candidate, Candidate.class)));
    }

    public GeneralResponse deleteCandidate(Long id) {
        Optional<Candidate> newCandidate = candidateRepo.findById(id);
        if (newCandidate.isPresent()) {
            List<Votes> votesList = voteRepo.findByCandidateId(id);
            if (!votesList.isEmpty()) {
                return new GeneralResponse(Constant.ResponseCode.CandidateHasVotes.code, Constant.ResponseCode.CandidateHasVotes.msg, null);
            }
            candidateRepo.delete(newCandidate.get());
            return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null);
        }
        return new GeneralResponse(Constant.ResponseCode.CandidateNotFound.code, Constant.ResponseCode.CandidateNotFound.msg, null);
    }

    public GeneralResponse forceDeleteCandidate(Long id) {
        Optional<Candidate> newCandidate = candidateRepo.findById(id);
        if (newCandidate.isPresent()) {
            List<Votes> votesList = voteRepo.findByCandidateId(id);
            if (!votesList.isEmpty()) {
                voteRepo.deleteAllByCandidateId(id);
            }
            candidateRepo.delete(newCandidate.get());
            return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null);
        }
        return new GeneralResponse(Constant.ResponseCode.CandidateNotFound.code, Constant.ResponseCode.CandidateNotFound.msg, null);
    }

    public GeneralResponse updateCandiate(NewCandidateRequest candidate, Long id) {
        Optional<Candidate> newCandidate = candidateRepo.findById(id);
        if (newCandidate.isPresent()) {
            newCandidate.get().setName(candidate.getName());

            newCandidate.get().setParty(candidate.getParty());
            candidateRepo.save(newCandidate.get());
            return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, newCandidate);
        }
        return new GeneralResponse(Constant.ResponseCode.CandidateNotFound.code, Constant.ResponseCode.CandidateNotFound.msg, null);
    }

    private boolean checkCandidateExist(String name) {
        return candidateRepo.findByName(name).isPresent();
    }


}
