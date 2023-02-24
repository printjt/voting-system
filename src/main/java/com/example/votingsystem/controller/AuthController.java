package com.example.votingsystem.controller;


import com.example.votingsystem.dto.request.LoginRequest;
import com.example.votingsystem.dto.request.RegisterRequest;
import com.example.votingsystem.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    @CrossOrigin()
    public ResponseEntity login(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        return ResponseEntity.ok().body(authService.login(loginRequest));
    }

    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok().body(authService.register(registerRequest));
    }

    @GetMapping("/users")
    @CrossOrigin
    public ResponseEntity users() {
        return ResponseEntity.ok().body(authService.users());
    }

    @GetMapping("/users/{id}")
    @CrossOrigin
    public ResponseEntity users(@PathVariable Long id) {
        return ResponseEntity.ok().body(authService.delete(id));
    }

}
