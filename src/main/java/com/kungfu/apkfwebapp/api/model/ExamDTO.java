package com.kungfu.apkfwebapp.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kungfu.apkfwebapp.domain.Member;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDate;

@Data
public class ExamDTO {
    private int id;
    private LocalDate date;
    private int grade;
    private Member member;
    private boolean pending;
    private boolean approved;

    @JsonProperty("exam_url")
    private String examUrl;
}
