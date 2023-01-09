package com.example.votingsystem.service;

import com.example.votingsystem.config.CSVHelper;
import com.example.votingsystem.config.Constant;
import com.example.votingsystem.dto.GeneralResponse;
import com.example.votingsystem.model.UserData;
import com.example.votingsystem.repository.UserDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserDataService {
    private final UserDataRepo userDataRepo;

    public GeneralResponse uploadData(MultipartFile file) {
        try {
            if(!file.getOriginalFilename().contains("csv")){
                return new GeneralResponse(Constant.ResponseCode.CsvFileExtensionException.code, Constant.ResponseCode.CsvFileExtensionException.msg, null);
            }
            List<UserData> tutorials = CSVHelper.csvToUserDataList(file.getInputStream());
            userDataRepo.saveAll(tutorials);
            return new GeneralResponse(Constant.ResponseCode.Success.code, Constant.ResponseCode.Success.msg, null);
        } catch (IOException e) {
            return new GeneralResponse(Constant.ResponseCode.CsvException.code, Constant.ResponseCode.CsvException.msg, "fail to store csv data: " + e.getMessage());
        }
    }
}
