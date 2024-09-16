package com.example.springboot.exception;

public class NotCSVFileExcetion extends RuntimeException{

    public NotCSVFileExcetion(String message) {
        super(message);
    }
}
