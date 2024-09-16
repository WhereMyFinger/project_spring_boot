package com.example.springboot.validation;

import com.example.springboot.custom.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class DateValidation implements ConstraintValidator<Date, String> {

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    String regexPattern = "^\\d{1,2}[-|/]\\d{1,2}[-|/]\\d{4}$";

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return patternMatches(s, regexPattern);
    }
}
