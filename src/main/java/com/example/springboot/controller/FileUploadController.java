package com.example.springboot.controller;

import com.example.springboot.exception.NotCSVFileExcetion;
import com.example.springboot.exception.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.storage.FileStorageService;

@Controller
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @GetMapping("/upload_form")
    public String showUploadForm() {
        return "upload";
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public String upload(@RequestParam MultipartFile file) {
        fileStorageService.uploadFile(file);
        return "success";
    }

    /*@ExceptionHandler(NotCSVFileExcetion.class)
    public String handleFileNotCSV(NotCSVFileExcetion e) {
        return "failure";
    }*/

    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e) {
        return "failure";
    }
}
