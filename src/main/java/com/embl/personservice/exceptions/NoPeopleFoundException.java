package com.embl.personservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class NoPeopleFoundException extends Exception {
    public NoPeopleFoundException() {
    }
}
