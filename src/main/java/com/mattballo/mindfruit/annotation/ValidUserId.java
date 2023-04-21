package com.mattballo.mindfruit.annotation;

import com.mattballo.mindfruit.util.UserIdValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserIdValidator.class)
public @interface ValidUserId {
    String message() default "Invalid external user ID";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
