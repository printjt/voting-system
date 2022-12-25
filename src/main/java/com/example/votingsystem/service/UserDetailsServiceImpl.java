package com.example.votingsystem.service;

import com.example.votingsystem.model.User;
import com.example.votingsystem.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Before query");
        Optional<User> user = userRepo.findByUsername(username);
//        System.out.println("User: "+ user.get() );
        if(!user.isPresent()){
            System.out.println("No user name found " + username);
            throw new UsernameNotFoundException("No user name found " + username);
        }
        System.out.println("Exist");
        return new UserDetailsImpl(user.get());
    }
}
