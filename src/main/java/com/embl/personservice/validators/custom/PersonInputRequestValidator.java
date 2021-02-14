package com.embl.personservice.validators.custom;

import com.embl.personservice.model.PersonInputRequest;
import com.embl.personservice.validators.common.CustomConstraintValidator;
import com.embl.personservice.validators.common.ValidationMessage;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidatorContext;

@Component
public class PersonInputRequestValidator extends CustomConstraintValidator<ValidatePersonInputRequest, PersonInputRequest> {
    @Override
    protected boolean validate(PersonInputRequest personInputRequest, ConstraintValidatorContext constraintValidatorContext) {
        boolean isValid = true;

        if (!notNullOrEmptyValidator.isValid(personInputRequest.getFirst_name(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("first_name").addConstraintViolation();
            isValid = false;
        }

        if (!notNullOrEmptyValidator.isValid(personInputRequest.getLast_name(), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_REQUIRED).addPropertyNode("last_name").addConstraintViolation();
            isValid = false;
        }

        if (!numberValidator.isValid(String.valueOf(personInputRequest.getAge()), constraintValidatorContext)) {
            constraintValidatorContext.buildConstraintViolationWithTemplate(ValidationMessage.ERROR_NUMBER).addPropertyNode("age").addConstraintViolation();
            isValid = false;
        }

        return isValid;
    }

    @Override
    public void initialize(ValidatePersonInputRequest constraintAnnotation) {

    }

}
