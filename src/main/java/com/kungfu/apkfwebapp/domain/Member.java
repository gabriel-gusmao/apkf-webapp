package com.kungfu.apkfwebapp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate startDate;
    private String phoneNumber;
    private String address;
    private String city;
    private String zipCode;

    @Enumerated(EnumType.STRING)
    private Phase phase;

    @ManyToOne(targetEntity = TrainingGroup.class, fetch = FetchType.EAGER)
    private TrainingGroup trainingGroup;

    @OneToMany(targetEntity = Exam.class, fetch = FetchType.EAGER)
    private Set<Exam> exams = new HashSet<>();

    private boolean groupLeader = false;

}
