package com.curriculum.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private Person person;

    @Column(nullable = false)
    private String institution;

    @Column(nullable = false)
    private String degree;

    @Column(nullable = false)
    private String fieldOfStudy;

    @Column(nullable = false)
    private LocalDate startDate;

    private LocalDate endDate;

    private Double gpa;

    @Column(columnDefinition = "TEXT")
    private String description;
} 