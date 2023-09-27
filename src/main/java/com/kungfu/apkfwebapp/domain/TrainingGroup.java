package com.kungfu.apkfwebapp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class TrainingGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String trainingLocation;
    private String city;

    @OneToMany(targetEntity = Member.class, fetch = FetchType.EAGER)
    private Set<Member> members = new HashSet<>();

}
