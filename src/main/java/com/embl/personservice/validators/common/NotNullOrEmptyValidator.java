package com.embl.personservice.validators.common;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class NotNullOrEmptyValidator implements ConstraintValidator<NotNullOrEmpty,Object> {

    public NotNullOrEmptyValidator(){

    }

    @Override
    public void initialize(NotNullOrEmpty notNullOrEmpty) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if(o == null)
            return false;
        else if(o instanceof String){
            return !StringUtils.isEmpty(o.toString());
        }
        else
            return true;
    }
}
