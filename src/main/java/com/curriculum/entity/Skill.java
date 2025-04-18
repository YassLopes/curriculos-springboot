package com.curriculum.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(nullable = false)
    private String name;

    private String level; // Beginner, Intermediate, Advanced, Expert
    private String category; // Technical, Soft Skills, etc.
} 