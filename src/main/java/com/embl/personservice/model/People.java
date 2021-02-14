package com.embl.personservice.model;

import com.embl.personservice.entity.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class People {
    private List<Person> person;
}
