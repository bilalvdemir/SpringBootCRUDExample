package com.bilald.crudsample.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserNameValidation, String> {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[a-zA-Z][a-zA-Z0-9.,$;]+$");
    private static final String USERNAME_NOT_VALID_MESSAGE = "Username must start with a letter and contain only alphanumeric characters, ., $, ;";

    @Override
    public void initialize(UserNameValidation constraintAnnotation) {
        // No initialization needed
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            Matcher matcher = USERNAME_PATTERN.matcher(value);
            if (!matcher.matches()) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate(USERNAME_NOT_VALID_MESSAGE)
                       .addConstraintViolation();
                return false;
            }
            return true;
        }
        return false;
    }
}
