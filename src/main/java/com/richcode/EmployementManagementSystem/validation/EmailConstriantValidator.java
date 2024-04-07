package com.richcode.EmployementManagementSystem.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailConstriantValidator implements ConstraintValidator<Email,String> {
    private String customerPrefix;
    @Override
    public void initialize(Email email) {
        customerPrefix = email.value();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        boolean result;
        if(email != null){
            return email.contains(customerPrefix);
        }else result = true;
        return result;
    }
}
