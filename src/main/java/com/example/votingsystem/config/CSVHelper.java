package com.example.votingsystem.config;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.votingsystem.model.UserData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERS = {"id", "nationalId", "name", "birthDate", "fatherName", "motherName", "placeOfBirth"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<UserData> csvToUserDataList(InputStream is) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
//            FileReader fileReader = new FileReader("voting-system.xlsx");
            CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

            List<UserData> userDataList = new ArrayList<UserData>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                System.out.println(Long.parseLong(csvRecord.get("ID")));
                UserData userData = new UserData(Long.parseLong(csvRecord.get("id")), csvRecord.get("nationalId"), csvRecord.get("name"), csvRecord.get("birthDate"), csvRecord.get("fatherName"), csvRecord.get("motherName"), csvRecord.get("placeOfBirth"));

                userDataList.add(userData);
            }

            return userDataList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

}