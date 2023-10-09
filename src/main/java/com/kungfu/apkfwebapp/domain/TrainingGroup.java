package com.kungfu.apkfwebapp.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

//    @OneToOne
//    @JoinTable(name = "group_leaders",
//            joinColumns = @JoinColumn(name = "training_group_id"),
//            inverseJoinColumns = @JoinColumn(name = "member_id"))
//    private Member groupLeader;

    @OneToMany(mappedBy = "trainingGroup")
    private Set<Member> members = new HashSet<>();

}
