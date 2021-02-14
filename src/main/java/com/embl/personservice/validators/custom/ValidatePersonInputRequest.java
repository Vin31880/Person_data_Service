package com.embl.personservice.validators.custom;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PersonInputRequestValidator.class)
@Documented
public @interface ValidatePersonInputRequest {
    String message() default "Invalid person request.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
