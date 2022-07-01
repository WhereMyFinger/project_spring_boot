package com.example.springboot.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.example.springboot.validation.EmailValidation;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = EmailValidation.class)
@Documented
public @interface Email {

    String message() default "Invalid email.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
