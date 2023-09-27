package com.kungfu.apkfwebapp.api.mapper;

import com.kungfu.apkfwebapp.api.model.ExamDTO;
import com.kungfu.apkfwebapp.domain.Exam;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ExamMapper {

    ExamMapper INSTANCE = Mappers.getMapper(ExamMapper.class);

    ExamDTO examToExamDTO(Exam exam);

    Exam examDTOToExam(ExamDTO examDTO);
}
