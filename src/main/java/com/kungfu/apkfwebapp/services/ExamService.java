package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.model.ExamDTO;
import com.kungfu.apkfwebapp.api.model.ExamListDTO;

import java.time.LocalDate;

public interface ExamService {

    ExamListDTO getAllExams();

    ExamListDTO getPendingExams();

    ExamDTO getExamById(int id);

    ExamDTO createNewExam(ExamDTO examDTO);

    ExamDTO saveExamByDTO(int id, ExamDTO examDTO);

    ExamDTO patchExam(int id, ExamDTO examDTO);

    ExamDTO evaluateExam(int id, ExamDTO examDTO);

    void deleteExamById(int id);
}
