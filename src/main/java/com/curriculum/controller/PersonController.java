package com.curriculum.controller;

import com.curriculum.entity.Person;
import com.curriculum.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/persons")
public class PersonController {
    private static final Logger logger = LoggerFactory.getLogger(PersonController.class);
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        logger.info("Getting all persons");
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> getPersonById(@PathVariable Long id) {
        logger.info("Getting person with id: {}", id);
        return personService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody Person person) {
        logger.info("Creating new person: {}", person);
        Person savedPerson = personService.save(person);
        return ResponseEntity.ok(savedPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Long id, @RequestBody Person person) {
        logger.info("Updating person with id {}: {}", id, person);
        if (!personService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        person.setId(id);
        return ResponseEntity.ok(personService.save(person));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Long id) {
        logger.info("Deleting person with id: {}", id);
        if (!personService.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personService.deleteById(id);
        return ResponseEntity.ok().build();
    }
} 