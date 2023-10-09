package com.kungfu.apkfwebapp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kungfu.apkfwebapp.domain.Exam;
import com.kungfu.apkfwebapp.domain.TrainingGroup;
import com.kungfu.apkfwebapp.domain.Phase;
import com.kungfu.apkfwebapp.domain.User;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;
import java.util.Set;

@Data
public class MemberDTO {
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
    private Phase phase;
    private TrainingGroup trainingGroup;
    private Set<Exam> exams;
    private User user;

    @JsonProperty("member_url")
    private String memberUrl;
}
