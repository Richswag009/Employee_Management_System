package com.richcode.EmployementManagementSystem.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Constraint(validatedBy = EmailConstriantValidator.class)
@Target({ElementType.FIELD,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

        // define default customer code;

        public  String value() default "@";
        // define default error message;
        public  String message() default "invalid Email Format";

        // define default groups: can group related constraints
        public  Class<?>[] groups() default {};

        // define default paloads;
        //payloads provide custom details about validation failure
        // (severity lever , error code etc)
        public  Class<? extends Payload>[] payload() default {};
}
