package com.example.springboot.storage;

import com.example.springboot.entities.User;
import com.example.springboot.exception.StorageException;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Service
public class CSVUploadService {

    public List<User> uploadCSVFile(MultipartFile file) {
        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            // create csv bean reader
            CsvToBean<User> csvToBean = new CsvToBeanBuilder(reader)
                    .withType(User.class)
                    //.withSkipLines(1)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            // convert `CsvToBean` object to list of users
            List<User> users = csvToBean.parse();
            return users;
        } catch (IOException e) {
            String msg = String.format("Failed to store file %s", file.getName());
            throw new StorageException(msg, e);
        }
    }
}
