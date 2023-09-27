package com.kungfu.apkfwebapp.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDate date;
    private int grade;

    @ManyToOne(targetEntity = Member.class,fetch = FetchType.EAGER)
    private Member member;

    private boolean pending = true;
    private boolean approved = false;

}
