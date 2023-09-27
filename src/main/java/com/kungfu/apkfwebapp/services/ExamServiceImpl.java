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
                        exam.getMember().setPhase(upgradePhase(currentPhase));
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
                        exam.getMember().setPhase(downgradePhase(currentPhase));
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

    private Phase upgradePhase(Phase phase) {
        Phase upgradedPhase = null;
        switch (phase) {
            case Beginner -> upgradedPhase = Phase.First;
            case First -> upgradedPhase = Phase.Second;
            case Second -> upgradedPhase = Phase.Third;
            case Third -> upgradedPhase = Phase.Fourth;
            case Fourth -> upgradedPhase = Phase.Fifth_level_one;
            case Fifth_level_one -> upgradedPhase = Phase.Fifth_level_two;
            case Fifth_level_two -> upgradedPhase = Phase.Fifth_level_three;
            case Fifth_level_three -> upgradedPhase = Phase.Sixth;
            case Sixth -> upgradedPhase = Phase.Seventh;
            case Seventh -> upgradedPhase = Phase.Eighth_level_one;
            case Eighth_level_one -> upgradedPhase = Phase.Eighth_level_two;
            case Eighth_level_two -> upgradedPhase = Phase.Eighth_level_three;
            case Eighth_level_three -> upgradedPhase = Phase.Eighth_level_four;
            case Eighth_level_four -> upgradedPhase = Phase.Eighth_level_five;
            case Eighth_level_five -> upgradedPhase = Phase.Eighth_level_six;
            case Eighth_level_six -> upgradedPhase = Phase.Eighth_level_seven;
            case Eighth_level_seven -> upgradedPhase = Phase.Eighth_level_eight;
            case Eighth_level_eight -> upgradedPhase = Phase.Eighth_level_nine;
            case Eighth_level_nine -> upgradedPhase = Phase.Master;
        }
        return upgradedPhase;
    }

    private Phase downgradePhase(Phase phase) {
        Phase downgradedPhase = null;
        switch (phase) {
            case First -> downgradedPhase = Phase.Beginner;
            case Second -> downgradedPhase = Phase.First;
            case Third -> downgradedPhase = Phase.Second;
            case Fourth -> downgradedPhase = Phase.Third;
            case Fifth_level_one -> downgradedPhase = Phase.Fourth;
            case Fifth_level_two -> downgradedPhase = Phase.Fifth_level_one;
            case Fifth_level_three -> downgradedPhase = Phase.Fifth_level_two;
            case Sixth -> downgradedPhase = Phase.Fifth_level_three;
            case Seventh -> downgradedPhase = Phase.Sixth;
            case Eighth_level_one -> downgradedPhase = Phase.Seventh;
            case Eighth_level_two -> downgradedPhase = Phase.Eighth_level_one;
            case Eighth_level_three -> downgradedPhase = Phase.Eighth_level_two;
            case Eighth_level_four -> downgradedPhase = Phase.Eighth_level_three;
            case Eighth_level_five -> downgradedPhase = Phase.Eighth_level_four;
            case Eighth_level_six -> downgradedPhase = Phase.Eighth_level_five;
            case Eighth_level_seven -> downgradedPhase = Phase.Eighth_level_six;
            case Eighth_level_eight -> downgradedPhase = Phase.Eighth_level_seven;
            case Eighth_level_nine -> downgradedPhase = Phase.Eighth_level_eight;
            case Master -> downgradedPhase = Phase.Eighth_level_nine;
        }
        return downgradedPhase;
    }
}
