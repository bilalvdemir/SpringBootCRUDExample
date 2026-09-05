package com.bilald.crudsample.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = UserNameValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UserNameValidation {
    String message() default "Attribute is not valid!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
