package com.kungfu.apkfwebapp.repositories;

import com.kungfu.apkfwebapp.domain.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Integer> {
}
