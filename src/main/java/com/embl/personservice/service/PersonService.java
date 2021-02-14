package com.embl.personservice.service;

import com.embl.personservice.entity.Person;
import com.embl.personservice.exceptions.NoPeopleFoundException;
import com.embl.personservice.exceptions.PersonNotFoundException;
import com.embl.personservice.model.People;
import com.embl.personservice.model.PersonInputRequest;
import com.embl.personservice.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class PersonService {

    private PersonRepository personRepository;

    public PersonService(@Autowired PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person saveOrUpdatePerson(PersonInputRequest personInputRequest) {
        return personRepository.save(buildPerson(personInputRequest));
    }

    public Person updatePerson(Long id, PersonInputRequest personUpdateRequest) throws PersonNotFoundException {
        Person person = getPersonById(id);
        personUpdateRequest.setId(person.getId());
        buildPersonForUpdate(person, personUpdateRequest);
        return personRepository.save(person);
    }

    public Person getPersonById(Long id) throws PersonNotFoundException {
        return personRepository.findById(id).orElseThrow(PersonNotFoundException::new);
    }

    public People getAllPersons() throws NoPeopleFoundException {
        People people = new People();
        List<Person> list = (List<Person>) personRepository.findAll();
        if (list.isEmpty()) {
            throw new NoPeopleFoundException();
        }
        people.setPerson((List<Person>) personRepository.findAll());
        return people;
    }

    public void deletePersonById(Long id) throws PersonNotFoundException {
        personRepository.delete(getPersonById(id));
    }

    private Person buildPerson(PersonInputRequest personInputRequest) {
        return Person.builder()
                .id(personInputRequest.getId())
                .first_name(personInputRequest.getFirst_name())
                .last_name(personInputRequest.getLast_name())
                .favourite_colour(personInputRequest.getFavourite_colour())
                .age(personInputRequest.getAge())
                .build();
    }

    private void buildPersonForUpdate(Person person, PersonInputRequest personUpdateRequest) {
        if (isValidAge(personUpdateRequest)) {
            person.setAge(personUpdateRequest.getAge());
        }

        if (!StringUtils.isEmpty(personUpdateRequest.getFirst_name())) {
            person.setFirst_name(personUpdateRequest.getFirst_name());
        }

        if (!StringUtils.isEmpty(personUpdateRequest.getLast_name())) {
            person.setLast_name(personUpdateRequest.getLast_name());
        }

        if (!StringUtils.isEmpty(personUpdateRequest.getFavourite_colour())) {
            person.setFavourite_colour(personUpdateRequest.getFavourite_colour());
        }
    }

    private boolean isValidAge(PersonInputRequest personUpdateRequest) {
        return !StringUtils.isEmpty(personUpdateRequest.getAge()) &&
                String.valueOf(personUpdateRequest.getAge()).matches("[0-9]*\\.?[0-9]*");
    }
}
