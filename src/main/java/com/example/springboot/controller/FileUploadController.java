package com.example.springboot.controller;

import com.example.springboot.exception.NotCSVFileExcetion;
import com.example.springboot.exception.StorageException;
import com.example.springboot.repository.UserRepository;
import com.example.springboot.service.UserService;
import com.example.springboot.storage.CSVUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.springboot.entities.User;
import com.example.springboot.storage.FileStorageService;

import java.util.List;

@Controller
public class FileUploadController {

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private CSVUploadService csvUploadService;

    @Autowired
    private UserService userService;

    @GetMapping("/upload_form")
    public String showUploadForm() {
        return "upload";
    }

    @GetMapping("import_csv")
    public String showImportCSVForm() {
        return "import-csv";
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    public String upload(@RequestParam MultipartFile file) {
        fileStorageService.uploadFile(file);
        return "success";
    }

    @Transactional
    @PostMapping("/upload_csv")
    public String uploadCSV(@RequestParam MultipartFile file) {
        List<User> list = csvUploadService.uploadCSVFile(file);
        for(User user : list) {
            User userFromDb = userService.getUserById(user.getId());
            if(userFromDb != null) {
                userFromDb.setUserName(user.getUserName());
                userFromDb.setEmail(user.getEmail());
                userFromDb.setBirthDay(user.getBirthDay());
                userService.saveUser(userFromDb);
            } else {
                userService.saveUser(user);
            }
        }
        return "success";
    }

    @ExceptionHandler(StorageException.class)
    public String handleStorageFileNotFound(StorageException e) {
        return "failure";
    }
}
