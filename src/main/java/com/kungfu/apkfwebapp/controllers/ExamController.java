package com.kungfu.apkfwebapp.controllers;

import com.kungfu.apkfwebapp.api.model.ExamDTO;
import com.kungfu.apkfwebapp.api.model.ExamListDTO;
import com.kungfu.apkfwebapp.services.ExamService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(ExamController.BASE_URL)
public class ExamController {

    public static final String BASE_URL = "/exam";

    private final ExamService examService;

    public ExamController(ExamService examService) {
        this.examService = examService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ExamListDTO getListOfExams() {
        return new ExamListDTO(examService.getAllExams().getExams());
    }

    @GetMapping("/pending")
    @ResponseStatus(HttpStatus.OK)
    public ExamListDTO getPendingExams() {
        return new ExamListDTO(examService.getPendingExams().getExams());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExamDTO getExamById(@PathVariable int id) {
        return examService.getExamById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ExamDTO createNewExam(@RequestBody ExamDTO examDTO) {
        return examService.createNewExam(examDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExamDTO updateExam(@PathVariable int id, @RequestBody ExamDTO examDTO) {
        return examService.saveExamByDTO(id, examDTO);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExamDTO patchExam(@PathVariable int id, @RequestBody ExamDTO examDTO) {
        return examService.patchExam(id, examDTO);
    }

    @PatchMapping("/evaluate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ExamDTO evaluateExam(@PathVariable int id, @RequestBody ExamDTO examDTO) {
        return examService.evaluateExam(id, examDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteExam(@PathVariable int id) {
        examService.deleteExamById(id);
    }
}
