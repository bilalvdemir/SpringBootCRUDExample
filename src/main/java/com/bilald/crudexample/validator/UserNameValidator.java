package com.bilald.crudexample.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.bilald.crudexample.utils.Utils;

public class UserNameValidator implements ConstraintValidator<UserNameValidation, String> {

    @Override
    public void initialize(UserNameValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // my validator example to simple. You can make more than complex validations
        if (value != null) {
            return value.matches(Utils.USER_NAME_VALIDATION_REGEX);
        }
        return false;
    }
}
