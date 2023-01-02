package com.example.votingsystem.service;

import com.example.votingsystem.config.Constant;
import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.dto.request.VoteRequest;
import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.Votes;
import com.example.votingsystem.repository.CandidateRepo;
import com.example.votingsystem.repository.UserRepo;
import com.example.votingsystem.repository.VoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepo voteRepo;
    private final CandidateRepo candidateRepo;
    private final UserRepo userRepo;

    public GeneralResponse vote(VoteRequest voteRequest) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(voteRequest.getCandidateId());
        Optional<User> optionalUser = userRepo.findByNationalNumber(voteRequest.getNationalId().toString());
        Optional<Votes> optionalVotes = voteRepo.findByNationalId(voteRequest.getNationalId());
        if(optionalUser.isEmpty()){
            return new GeneralResponse(Constant.ResponseCode.UserNotFound.code, Constant.ResponseCode.UserNotFound.msg,null);
        }
        if(optionalVotes.isPresent()){
            return new GeneralResponse(Constant.ResponseCode.VoteAlreadyExists.code, Constant.ResponseCode.VoteAlreadyExists.msg,null);
        }
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            Votes votes = new Votes();
            votes.setCandidateId(candidate).setConfirmed(false).setNationalId(voteRequest.getNationalId());
            voteRepo.save(votes);
            return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null);
        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }

    public GeneralResponse getVote(Long id) {
        Optional<Votes> optionalVotes = voteRepo.findById(id);
        return optionalVotes.map(votes -> new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, votes)).orElseGet(() -> new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null));
    }

    public GeneralResponse confirmVote(Long id) {
        Optional<Votes> optionalVotes = voteRepo.findById(id);
        if (optionalVotes.isPresent()) {
            Votes votes = optionalVotes.get();
            Optional<Candidate> optionalCandidate = candidateRepo.findById(votes.getId());
            return validateConfirmVote(votes, optionalCandidate) ? new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null) : new GeneralResponse(Constant.ResponseCode.VoteAlreadyConfirmed.code, Constant.ResponseCode.VoteAlreadyConfirmed.msg, null);
        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }

    private boolean validateConfirmVote(Votes votes, Optional<Candidate> optionalCandidate) {
        if (optionalCandidate.isPresent() && votes.isConfirmed()) {
            Candidate candidate = optionalCandidate.get();
            votes.setConfirmed(true);
            candidate.setVotes(candidate.getVotes() + 1);
            voteRepo.save(votes);
            candidateRepo.save(candidate);
            return true;
        }
        return false;
    }

}
