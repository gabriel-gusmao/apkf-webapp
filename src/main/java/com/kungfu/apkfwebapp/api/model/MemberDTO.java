package com.kungfu.apkfwebapp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kungfu.apkfwebapp.domain.TrainingGroup;
import com.kungfu.apkfwebapp.domain.Phase;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MemberDTO {
    private int id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private LocalDate startDate;
    private String phoneNumber;
    private String address;
    private String city;
    private String zipCode;
    private Phase phase;
    private TrainingGroup trainingGroup;
    private boolean isGroupLeader;

    @JsonProperty("member_url")
    private String memberUrl;
}
