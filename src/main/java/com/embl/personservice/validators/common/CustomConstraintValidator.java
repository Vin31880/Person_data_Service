package com.embl.personservice.validators.common;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public abstract class CustomConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {
    @Autowired
    protected NotNullOrEmptyValidator notNullOrEmptyValidator;


    @Autowired
    protected NumberValidator numberValidator;

    private ConstraintValidatorContext constraintValidatorContext;

    protected abstract boolean validate(T validationObject, ConstraintValidatorContext constraintValidatorContext);

    @Override
    public boolean isValid(T validationObject, ConstraintValidatorContext constraintValidatorContext) {
        this.constraintValidatorContext = constraintValidatorContext;
        constraintValidatorContext.disableDefaultConstraintViolation();

        return validate(validationObject, constraintValidatorContext);
    }

}
