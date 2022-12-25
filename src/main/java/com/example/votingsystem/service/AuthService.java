package com.example.votingsystem.service;


import com.example.votingsystem.config.Constant;
import com.example.votingsystem.config.JWTUtils;
import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.dto.request.LoginRequest;
import com.example.votingsystem.dto.request.RegisterRequest;
import com.example.votingsystem.model.User;
import com.example.votingsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepo userRepo;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;
    private final ModelMapper modelMapper = new ModelMapper();

    public GeneralResponse login(LoginRequest loginRequest){
        Map<String, Object> res = new HashMap<>();
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        if(authentication.isAuthenticated()){
            List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
            String token = jwtUtils.getAccessToken(loginRequest, "MaxNet" , roles);
            res.put("accessToken",token);
        }

        return new GeneralResponse(Constant.ResponseCode.Success.code,Constant.ResponseCode.Success.msg,res);
    }

    public GeneralResponse register(RegisterRequest registerRequest){
        User user = modelMapper.map(registerRequest,User.class);
        user.setPassword(encoder.encode(user.getPassword()));
        return checkUserExist(registerRequest.getUsername()) ? new GeneralResponse(Constant.ResponseCode.UserAlreadyExists.code,Constant.ResponseCode.UserAlreadyExists.msg,null) : new GeneralResponse(Constant.ResponseCode.Success.code,Constant.ResponseCode.Success.msg,userRepo.save(user));
    }

    private boolean checkUserExist(String username){
        return userRepo.findByUsername(username).isPresent();
    }
}
