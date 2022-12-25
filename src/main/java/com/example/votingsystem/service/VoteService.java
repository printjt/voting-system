package com.example.votingsystem.service;

import com.example.votingsystem.config.Constant;
import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.dto.request.VoteRequest;
import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.model.Votes;
import com.example.votingsystem.repository.CandidateRepo;
import com.example.votingsystem.repository.VoteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepo voteRepo;
    private final CandidateRepo candidateRepo;

    public GeneralResponse vote(VoteRequest voteRequest) {
        Optional<Candidate> optionalCandidate = candidateRepo.findById(voteRequest.getCandidateId());
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
            return ValidateVote(votes, optionalCandidate) ? new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null) : new GeneralResponse(Constant.ResponseCode.VoteAlreadyConfirmed.code, Constant.ResponseCode.VoteAlreadyConfirmed.msg, null);
        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }

    private boolean ValidateVote(Votes votes, Optional<Candidate> optionalCandidate) {
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
