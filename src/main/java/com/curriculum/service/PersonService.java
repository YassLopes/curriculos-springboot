package com.curriculum.service;

import com.curriculum.entity.Person;
import com.curriculum.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    @Transactional
    public Person save(Person person) {
        // Handle relationships
        if (person.getEducations() != null) {
            person.getEducations().forEach(education -> education.setPerson(person));
        }
        if (person.getExperiences() != null) {
            person.getExperiences().forEach(experience -> experience.setPerson(person));
        }
        if (person.getSkills() != null) {
            person.getSkills().forEach(skill -> skill.setPerson(person));
        }
        if (person.getLanguages() != null) {
            person.getLanguages().forEach(language -> language.setPerson(person));
        }
        
        return personRepository.save(person);
    }

    @Transactional
    public void deleteById(Long id) {
        personRepository.deleteById(id);
    }
} 