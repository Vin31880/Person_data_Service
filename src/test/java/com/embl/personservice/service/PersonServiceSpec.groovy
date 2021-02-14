package com.embl.personservice.service

import com.embl.personservice.entity.Person
import com.embl.personservice.exceptions.PersonNotFoundException
import com.embl.personservice.model.PersonInputRequest
import com.embl.personservice.repository.PersonRepository
import spock.lang.Specification

class PersonServiceSpec extends Specification {

    PersonRepository personRepository;
    PersonService personService;
    PersonInputRequest personInputRequest;
    Person personToSave

    void setup() {
        personRepository = Mock(PersonRepository.class)
        personService = new PersonService(personRepository);
        personInputRequest = new PersonInputRequest()
        personInputRequest.first_name = "TEST_NAME"
        personInputRequest.last_name = "TEST_LAST_NAME"
        personInputRequest.age = 32
        personInputRequest.favourite_colour = "red"

        personToSave = Person.builder()
                .first_name(personInputRequest.first_name)
                .last_name(personInputRequest.last_name)
                .age(personInputRequest.age)
                .favourite_colour(personInputRequest.favourite_colour)
                .build()
    }

    def "find person by id"() {
        given:
        Person person = new Person()
        person.setId(1L);
        personRepository.findById(1L) >> Optional.of(person)

        when:
        Person result = personService.getPersonById(1L)

        then:
        result.id == person.id
    }

    def "fail: find person by id"() {
        given:
        Person person = new Person()
        person.setId(1L);
        personRepository.findById(1L) >> Optional.ofNullable(null)

        when:
        personService.getPersonById(1L)

        then:
        thrown(PersonNotFoundException)
    }

    def "add or update person"() {
        given:
        Person person = Person.builder()
                .id(1L)
                .first_name(personInputRequest.first_name)
                .last_name(personInputRequest.last_name)
                .age(personInputRequest.age)
                .favourite_colour(personInputRequest.favourite_colour)
                .build()

        when:
        Person result = personService.saveOrUpdatePerson(personInputRequest)

        then:
        result == person
        1 * personRepository.save(personToSave) >> person
    }
}
