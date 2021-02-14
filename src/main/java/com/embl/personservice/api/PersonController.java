package com.embl.personservice.api;

import com.embl.personservice.exceptions.NoPeopleFoundException;
import com.embl.personservice.exceptions.PersonNotFoundException;
import com.embl.personservice.model.PersonInputRequest;
import com.embl.personservice.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = PersonController.BASE_URL)
@CrossOrigin
public class PersonController {

    public static final String BASE_URL = "/api/person";
    private static Logger logger = LoggerFactory.getLogger(PersonController.class);
    private PersonService personService;

    public PersonController(@Autowired PersonService personService) {
        this.personService = personService;
    }


    @PostMapping
    public ResponseEntity<?> pushPersonInfo(@Valid @RequestBody PersonInputRequest person, BindingResult bindingResult) {
        if (person == null || bindingResult.getErrorCount() > 0) {
            logger.warn("Invalid person request object");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrors(bindingResult));
        }
        logger.info("Creating person");
        return ResponseEntity.status(HttpStatus.CREATED).body(personService.saveOrUpdatePerson(person));
    }


    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updatePersonInfo(@PathVariable("id") Long id,
                                              @RequestBody PersonInputRequest person,
                                              BindingResult bindingResult) throws PersonNotFoundException {
        if (person == null || bindingResult.getErrorCount() > 0) {
            logger.warn("Invalid person request object");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(buildErrors(bindingResult));
        }
        logger.info("Updating person");
        return ResponseEntity.status(HttpStatus.OK).body(personService.updatePerson(id, person));
    }

    @DeleteMapping(value = "/{id}")
    public void deletePerson(@PathVariable("id") Long id) throws PersonNotFoundException {
        logger.info("Deleting person with id " + id);
        personService.deletePersonById(id);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getPersonById(@PathVariable("id") Long id) throws PersonNotFoundException {
        logger.info("Find person by id " + id);
        return ResponseEntity.status(HttpStatus.OK).body(personService.getPersonById(id));
    }

    @GetMapping
    public ResponseEntity<?> listAllPersons() throws NoPeopleFoundException {
        logger.info("Get all persons ");
        return ResponseEntity.status(HttpStatus.OK).body(personService.getAllPersons());
    }


    private List<String> buildErrors(BindingResult bindingResult) {
        List<String> errors = new ArrayList<>();
        bindingResult.getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + " : " + errorMessage);
        });
        return errors;
    }

}
