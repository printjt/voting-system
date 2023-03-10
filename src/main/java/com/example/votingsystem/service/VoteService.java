package com.example.votingsystem.service;

import com.example.votingsystem.config.Constant;
import com.example.votingsystem.config.UniqueIdGenerator;
import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.dto.request.VoteRequest;
import com.example.votingsystem.model.Candidate;
import com.example.votingsystem.model.User;
import com.example.votingsystem.model.VoteCycle;
import com.example.votingsystem.model.Votes;
import com.example.votingsystem.repository.CandidateRepo;
import com.example.votingsystem.repository.UserRepo;
import com.example.votingsystem.repository.VoteCycleRepo;
import com.example.votingsystem.repository.VoteRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoteRepo voteRepo;
    private final CandidateRepo candidateRepo;
    private final UserRepo userRepo;
    private final VoteCycleRepo voteCycleRepo;

    public GeneralResponse vote(VoteRequest voteRequest) throws ParseException {
        UniqueIdGenerator uniqueIdGenerator = new UniqueIdGenerator.UniqueIdGeneratorBuilder().useDigits(false).useLower(true).useUpper(false).build();
        Optional<Candidate> optionalCandidate = candidateRepo.findById(voteRequest.getCandidateId());
        Optional<User> optionalUser = userRepo.findByUsername(voteRequest.getUsername());
        Optional<Votes> optionalVotes = voteRepo.findByUsername(voteRequest.getUsername());
        List<VoteCycle> optionalVoteCycle = voteCycleRepo.findAll();

        if (optionalVotes.isPresent() && checkIfVoteCycleExpired(optionalVoteCycle.stream().findFirst().get().getEndDate())){
            return new GeneralResponse(Constant.ResponseCode.VoteCycleExpired.code, Constant.ResponseCode.VoteCycleExpired.msg, null);
        }

        if (optionalUser.isEmpty()) {
            return new GeneralResponse(Constant.ResponseCode.UserNotFound.code, Constant.ResponseCode.UserNotFound.msg, null);
        }
        if (optionalVotes.isPresent()) {
            return new GeneralResponse(Constant.ResponseCode.VoteAlreadyExists.code, Constant.ResponseCode.VoteAlreadyExists.msg, null);
        }
        if (optionalCandidate.isPresent()) {
            Candidate candidate = optionalCandidate.get();
            String uniqueId = uniqueIdGenerator.generate(8);
            Votes votes = new Votes();
            votes.setCandidateId(candidate).setConfirmed(false).setNationalId(voteRequest.getNationalId()).setUniqueId(uniqueId).setUsername(voteRequest.getUsername());
            voteRepo.save(votes);
            Map<String, Object> res = new HashMap<>();
            res.put("unique_id",uniqueId);
            return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, res);
        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }

    public GeneralResponse getVote(Long id) {
        Optional<Votes> optionalVotes = voteRepo.findById(id);
        return optionalVotes.map(votes -> new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, votes)).orElseGet(() -> new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null));
    }

    public GeneralResponse confirmVote(String id) {
        Optional<Votes> optionalVotes = voteRepo.findByUniqueIdOrUsername(id);
        if (optionalVotes.isPresent()) {
            Votes votes = optionalVotes.get();
            Optional<Candidate> optionalCandidate = candidateRepo.findById(votes.getCandidateId().getId());
            return validateConfirmVote(votes, optionalCandidate) ? new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null) : new GeneralResponse(Constant.ResponseCode.VoteAlreadyConfirmed.code, Constant.ResponseCode.VoteAlreadyConfirmed.msg, null);
        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }

    public GeneralResponse deleteVote(String uniqueId) {
        Optional<Votes> optionalVotes = voteRepo.findByUniqueIdOrUsername(uniqueId);
        if (optionalVotes.isPresent()) {
            if(optionalVotes.get().isConfirmed()){
                return new GeneralResponse(Constant.ResponseCode.VoteAlreadyConfirmed.code, Constant.ResponseCode.VoteAlreadyConfirmed.msg, null);

            }else{
                voteRepo.delete(optionalVotes.get());
                return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null);
            }

        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }

    public GeneralResponse getUserDataWithUniqueId(String uniqueId){
        Optional<Votes> optionalVotes = voteRepo.findByUniqueIdOrUsername(uniqueId);
        if(optionalVotes.isPresent()){
            Optional<User> user = userRepo.findByUsername(optionalVotes.get().getUsername());
            return user.map(value -> new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, value)).orElseGet(() -> new GeneralResponse(Constant.ResponseCode.UserNotFound.code, Constant.ResponseCode.UserNotFound.msg, null));
        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }


    public GeneralResponse checkUserVoteByUsername(String username){


        Optional<Votes> votes=voteRepo.findByUsername(username);
//           Optional<User> user = userRepo.findByUsername(username);
        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAss");
        System.out.println(votes.isPresent());
            return votes.map(value -> new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg,  userRepo.findByUsername(username).get())).orElseGet(() -> new GeneralResponse(Constant.ResponseCode.UserNotFound.code, Constant.ResponseCode.UserNotFound.msg, null));

    }



    //todo says no user found even when national id is correct in user data
    public GeneralResponse checkUserIdVote(Long id) {
        Optional<Votes> optionalVotes = voteRepo.findById(id);
        if (optionalVotes.isPresent()) {
            Votes votes = optionalVotes.get();
            Optional<User> optionalUser = userRepo.findByNationalNumber(optionalVotes.get().getNationalId().toString());
            return optionalUser.map(user -> new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, user)).orElseGet(() -> new GeneralResponse(Constant.ResponseCode.UserNotFound.code, Constant.ResponseCode.UserNotFound.msg, null));
        }
        return new GeneralResponse(Constant.ResponseCode.VoteNotFound.code, Constant.ResponseCode.VoteNotFound.msg, null);
    }

    private boolean validateConfirmVote(Votes votes, Optional<Candidate> optionalCandidate) {
        System.out.println("votes = " + votes.isConfirmed() + ", optionalCandidate = " + optionalCandidate.toString());
        if (optionalCandidate.isPresent() && !votes.isConfirmed()) {
            Candidate candidate = optionalCandidate.get();
            votes.setConfirmed(true);
            candidate.setVotes(candidate.getVotes() + 1);
            voteRepo.save(votes);
            candidateRepo.save(candidate);
            return true;
        }
        return false;
    }

    private boolean checkIfVoteCycleExpired(LocalDateTime time) {
        LocalDateTime localDateTime = LocalDateTime.now();
        return !localDateTime.isBefore(time);
    }

}
