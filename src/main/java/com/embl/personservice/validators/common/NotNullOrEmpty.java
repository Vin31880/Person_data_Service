package com.embl.personservice.validators.common;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNullOrEmptyValidator.class)
@Documented
public @interface NotNullOrEmpty {

    String message() default "Invalid value";


    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
