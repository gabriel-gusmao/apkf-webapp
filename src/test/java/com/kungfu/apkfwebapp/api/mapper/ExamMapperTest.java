package com.kungfu.apkfwebapp.api.mapper;

import com.kungfu.apkfwebapp.api.model.ExamDTO;
import com.kungfu.apkfwebapp.domain.Exam;
import com.kungfu.apkfwebapp.domain.Member;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ExamMapperTest {

    public static final LocalDate DATE = LocalDate.of(2006,10,25);
    public static final int GRADE = 70;
    public static final Member MEMBER = new Member();

    ExamMapper examMapper = ExamMapper.INSTANCE;

    @Test
    void examToExamDTO() {
        //given
        Exam exam = new Exam();
        exam.setDate(DATE);
        exam.setGrade(GRADE);
        exam.setMember(MEMBER);
        exam.setPending(false);
        exam.setApproved(true);

        //when
        ExamDTO examDTO = examMapper.examToExamDTO(exam);

        //then
        assertEquals(exam.getDate(), examDTO.getDate());
        assertEquals(exam.getGrade(), examDTO.getGrade());
        assertEquals(exam.getMember(), examDTO.getMember());
        assertEquals(exam.isPending(), examDTO.isPending());
        assertEquals(exam.isApproved(), examDTO.isApproved());
    }

    @Test
    void examDTOToExam() {
        //given
        ExamDTO examDTO = new ExamDTO();
        examDTO.setDate(DATE);
        examDTO.setGrade(GRADE);
        examDTO.setMember(MEMBER);
        examDTO.setPending(false);
        examDTO.setApproved(true);

        //when
        Exam exam = examMapper.examDTOToExam(examDTO);

        //then
        assertEquals(exam.getDate(), examDTO.getDate());
        assertEquals(exam.getGrade(), examDTO.getGrade());
        assertEquals(exam.getMember(), examDTO.getMember());
        assertEquals(exam.isPending(), examDTO.isPending());
        assertEquals(exam.isApproved(), examDTO.isApproved());
    }
}