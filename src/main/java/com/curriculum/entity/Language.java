package com.curriculum.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "languages")
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String proficiency; // Basic, Intermediate, Advanced, Fluent, Native
} 