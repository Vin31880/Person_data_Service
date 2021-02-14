package com.embl.personservice.model;

import com.embl.personservice.validators.custom.ValidatePersonInputRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ValidatePersonInputRequest
public class PersonInputRequest {
    private Long id;
    private String first_name;
    private String last_name;
    private String favourite_colour;
    private Integer age;
}
