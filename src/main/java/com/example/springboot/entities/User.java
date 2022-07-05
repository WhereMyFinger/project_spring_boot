package com.example.springboot.entities;

import com.example.springboot.custom.Date;
import com.example.springboot.custom.Email;
import com.opencsv.bean.CsvBindByName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class User {

    @Id
    @CsvBindByName
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @CsvBindByName(column = "user_name")
    @Size(min = 4, message = "Name can not have less than 4 letters")
    private String userName;
    @CsvBindByName
    @NotEmpty(message = "Empty email")
    @Email
    private String email;
    @CsvBindByName(column = "birth_day")
    @NotEmpty(message = "Empty date")
    @Date
    private String birthDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }
}
