package com.curriculum.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "persons")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String phone;

    private String address;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Education> educations = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Experience> experiences = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Skill> skills = new ArrayList<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Language> languages = new ArrayList<>();

    // Helper methods to manage bidirectional relationships
    public void addEducation(Education education) {
        educations.add(education);
        education.setPerson(this);
    }

    public void removeEducation(Education education) {
        educations.remove(education);
        education.setPerson(null);
    }

    public void addExperience(Experience experience) {
        experiences.add(experience);
        experience.setPerson(this);
    }

    public void removeExperience(Experience experience) {
        experiences.remove(experience);
        experience.setPerson(null);
    }

    public void addSkill(Skill skill) {
        skills.add(skill);
        skill.setPerson(this);
    }

    public void removeSkill(Skill skill) {
        skills.remove(skill);
        skill.setPerson(null);
    }

    public void addLanguage(Language language) {
        languages.add(language);
        language.setPerson(this);
    }

    public void removeLanguage(Language language) {
        languages.remove(language);
        language.setPerson(null);
    }
} 