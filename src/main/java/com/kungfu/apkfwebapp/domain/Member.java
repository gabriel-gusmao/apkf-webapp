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
    private String email;
    private String address;
    private String city;
    private String zipCode;

    @Enumerated(EnumType.STRING)
    private Phase phase;

    @ManyToOne
    @JoinTable(name = "member_training_group",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "training_group_id"))
    private TrainingGroup trainingGroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private Set<Exam> exams = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
