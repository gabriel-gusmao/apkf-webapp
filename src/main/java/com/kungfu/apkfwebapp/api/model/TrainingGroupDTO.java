package com.kungfu.apkfwebapp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kungfu.apkfwebapp.domain.Member;
import lombok.Data;

import java.util.Set;

@Data
public class TrainingGroupDTO {
    private int id;
    private String name;
    private String trainingLocation;
    private String city;
    private Set<Member> members;

    @JsonProperty("training_group_url")
    private String trainingGroupUrl;
}
