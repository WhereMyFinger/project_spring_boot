package com.example.springboot.storage;

import com.example.springboot.exception.NotCSVFileExcetion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.example.springboot.exception.StorageException;

@Service
public class FileStorageService {

    @Value("${upload.path:}")
    private Path path;
    public void uploadFile(MultipartFile file) {

        if (file.isEmpty()) {
            throw new StorageException("Failed to store empty file");
        }
        try {
            String fileName = file.getOriginalFilename();
            /*if(fileName.substring(fileName.length()-3, fileName.length()) != "csv") {
                throw new NotCSVFileExcetion("Only upload csv file");
            }*/
            java.io.InputStream is = file.getInputStream();

            Files.copy(is, this.path.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            String msg = String.format("Failed to store file %s", file.getName());
            throw new StorageException(msg, e);
        }
    }
}
