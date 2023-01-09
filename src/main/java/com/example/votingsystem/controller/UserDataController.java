package com.example.votingsystem.controller;

import com.example.votingsystem.service.UserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserDataController {

    private final UserDataService userDataService;

    @PostMapping("/user-data/upload")
    public ResponseEntity save(@RequestParam("file")MultipartFile file){
        return ResponseEntity.ok().body(userDataService.uploadData(file));
    }
}
