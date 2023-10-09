package com.kungfu.apkfwebapp.api.mapper;

import com.kungfu.apkfwebapp.api.model.MemberDTO;
import com.kungfu.apkfwebapp.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MemberMapperTest {

    public static final String FIRSTNAME = "Jake";
    public static final String LASTNAME = "Finn";
    public static final LocalDate BIRTHDATE = LocalDate.of(1990, 10, 5);
    public static final LocalDate START_DATE = LocalDate.of(2020, 5, 5);
    public static final String PHONE_NUMBER = "987654321";
    public static final String EMAIL = "jakefinn@email.com";
    public static final String ADDRESS = "Rua de baixo, 123";
    public static final String CITY = "Terra dos Piás";
    public static final String ZIPCODE = "99987-789";
    public static final Phase PHASE = Phase.First;
    public static final TrainingGroup TRAINING_GROUP = new TrainingGroup();
    public static final Set<Exam> EXAMS = new HashSet<>();
    public static final boolean GROUP_LEADER = false;
    public static final User USER = new User();

    MemberMapper memberMapper = MemberMapper.INSTANCE;

    @Test
    void memberToMemberDTO() {
        //given
        Member member = new Member();
        member.setFirstName(FIRSTNAME);
        member.setLastName(LASTNAME);
        member.setBirthDate(BIRTHDATE);
        member.setStartDate(START_DATE);
        member.setPhoneNumber(PHONE_NUMBER);
        member.setEmail(EMAIL);
        member.setAddress(ADDRESS);
        member.setCity(CITY);
        member.setZipCode(ZIPCODE);
        member.setPhase(PHASE);
        member.setTrainingGroup(TRAINING_GROUP);
        member.setExams(EXAMS);
        member.setUser(USER);

        //when
        MemberDTO memberDTO = memberMapper.memberToMemberDTO(member);

        //then
        assertEquals(member.getFirstName(), memberDTO.getFirstName());
        assertEquals(member.getLastName(), memberDTO.getLastName());
        assertEquals(member.getBirthDate(), memberDTO.getBirthDate());
        assertEquals(member.getStartDate(), memberDTO.getStartDate());
        assertEquals(member.getPhoneNumber(), memberDTO.getPhoneNumber());
        assertEquals(member.getEmail(), memberDTO.getEmail());
        assertEquals(member.getAddress(), memberDTO.getAddress());
        assertEquals(member.getCity(), memberDTO.getCity());
        assertEquals(member.getZipCode(), memberDTO.getZipCode());
        assertEquals(member.getPhase(), memberDTO.getPhase());
        assertEquals(member.getTrainingGroup(), memberDTO.getTrainingGroup());
        assertEquals(member.getExams(), memberDTO.getExams());
        assertEquals(member.getUser(), memberDTO.getUser());
    }

    @Test
    void memberDTOToMember() {
        //given
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setFirstName(FIRSTNAME);
        memberDTO.setLastName(LASTNAME);
        memberDTO.setBirthDate(BIRTHDATE);
        memberDTO.setStartDate(START_DATE);
        memberDTO.setPhoneNumber(PHONE_NUMBER);
        memberDTO.setEmail(EMAIL);
        memberDTO.setAddress(ADDRESS);
        memberDTO.setCity(CITY);
        memberDTO.setZipCode(ZIPCODE);
        memberDTO.setPhase(PHASE);
        memberDTO.setTrainingGroup(TRAINING_GROUP);
        memberDTO.setExams(EXAMS);
        memberDTO.setUser(USER);

        //when
        Member member = memberMapper.memberDTOToMember(memberDTO);

        //then
        assertEquals(member.getFirstName(), memberDTO.getFirstName());
        assertEquals(member.getLastName(), memberDTO.getLastName());
        assertEquals(member.getBirthDate(), memberDTO.getBirthDate());
        assertEquals(member.getStartDate(), memberDTO.getStartDate());
        assertEquals(member.getPhoneNumber(), memberDTO.getPhoneNumber());
        assertEquals(member.getEmail(), memberDTO.getEmail());
        assertEquals(member.getAddress(), memberDTO.getAddress());
        assertEquals(member.getCity(), memberDTO.getCity());
        assertEquals(member.getZipCode(), memberDTO.getZipCode());
        assertEquals(member.getPhase(), memberDTO.getPhase());
        assertEquals(member.getTrainingGroup(), memberDTO.getTrainingGroup());
        assertEquals(member.getExams(), memberDTO.getExams());
        assertEquals(member.getUser(), memberDTO.getUser());
    }
}