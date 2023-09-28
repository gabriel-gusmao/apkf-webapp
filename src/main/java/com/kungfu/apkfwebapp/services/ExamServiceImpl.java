package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.mapper.ExamMapper;
import com.kungfu.apkfwebapp.api.model.ExamDTO;
import com.kungfu.apkfwebapp.api.model.ExamListDTO;
import com.kungfu.apkfwebapp.controllers.ExamController;
import com.kungfu.apkfwebapp.domain.Exam;
import com.kungfu.apkfwebapp.domain.Phase;
import com.kungfu.apkfwebapp.repositories.ExamRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    private final ExamMapper examMapper;
    private final ExamRepository examRepository;

    public ExamServiceImpl(ExamMapper examMapper, ExamRepository examRepository) {
        this.examMapper = examMapper;
        this.examRepository = examRepository;
    }

    @Override
    public ExamListDTO getAllExams() {
        List<ExamDTO> examDTOS = examRepository
                .findAll()
                .stream()
                .map(exam -> {
                    ExamDTO examDTO = examMapper.examToExamDTO(exam);
                    examDTO.setExamUrl(getExamUrl(exam.getId()));
                    return examDTO;
                })
                .collect(Collectors.toList());
        return new ExamListDTO(examDTOS);
    }

    @Override
    public ExamListDTO getPendingExams() {
        List<ExamDTO> examDTOS = examRepository
                .findAll()
                .stream()
                .filter(Exam::isPending)
                .map(exam -> {
                    ExamDTO examDTO = examMapper.examToExamDTO(exam);
                    examDTO.setExamUrl(getExamUrl(exam.getId()));
                    return examDTO;
                })
                .collect(Collectors.toList());
        return new ExamListDTO(examDTOS);
    }

    @Override
    public ExamListDTO getExamsByDate(LocalDate date) {
        List<ExamDTO> examDTOS = examRepository
                .findAll()
                .stream()
                .filter(s -> s.getDate().isEqual(date))
                .map(exam -> {
                    ExamDTO examDTO = examMapper.examToExamDTO(exam);
                    examDTO.setExamUrl(getExamUrl(exam.getId()));
                    return examDTO;
                })
                .collect(Collectors.toList());
        return new ExamListDTO(examDTOS);
    }

    @Override
    public ExamDTO getExamById(int id) {
        return examRepository.findById(id)
                .map(examMapper::examToExamDTO)
                .map(examDTO -> {
                    examDTO.setExamUrl(getExamUrl(id));
                    return examDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ExamDTO createNewExam(ExamDTO examDTO) {
        return saveAndReturnDTO(examMapper.examDTOToExam(examDTO));
    }

    @Override
    public ExamDTO saveExamByDTO(int id, ExamDTO examDTO) {

        Exam examToSave = examMapper.examDTOToExam(examDTO);
        examToSave.setId(id);

        return saveAndReturnDTO(examToSave);
    }

    @Override
    public ExamDTO patchExam(int id, ExamDTO examDTO) {
        return examRepository.findById(id)
                .map(exam -> {
                    if (examDTO.getMember() != null) {
                        exam.setMember(examDTO.getMember());
                    }
                    if (examDTO.getDate() != null) {
                        exam.setDate(examDTO.getDate());
                    }

                    return saveAndReturnDTO(exam);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public ExamDTO evaluateExam(int id, int grade) {
        return examRepository.findById(id)
                .map(exam -> {
                    Phase currentPhase = exam.getMember().getPhase();
                    if (exam.getGrade() < 60 && grade >= 60) {
                        exam.setGrade(grade);
                        exam.setApproved(true);
                        //upgrade phase
                        exam.getMember().setPhase(currentPhase.getNextPhase());
                    }
                    if (exam.getGrade() < 60 && grade < 60) {
                        exam.setGrade(grade);
                    }
                    if (exam.getGrade() >= 60 && grade >= 60) {
                        exam.setGrade(grade);
                    }
                    if (exam.getGrade() >= 60 && grade < 60) {
                        exam.setGrade(grade);
                        exam.setApproved(false);
                        //downgrade phase
                        exam.getMember().setPhase(currentPhase.getPrevPhase());
                    }
                    exam.setPending(false);

                    return saveAndReturnDTO(exam);
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteExamById(int id) {
        examRepository.deleteById(id);
    }

    private String getExamUrl(int id) {
        return ExamController.BASE_URL + "/" + id;
    }

    private ExamDTO saveAndReturnDTO(Exam exam) {
        Exam savedExam = examRepository.save(exam);

        ExamDTO returnDTO = examMapper.examToExamDTO(savedExam);

        returnDTO.setExamUrl(getExamUrl(savedExam.getId()));

        return returnDTO;
    }
}
