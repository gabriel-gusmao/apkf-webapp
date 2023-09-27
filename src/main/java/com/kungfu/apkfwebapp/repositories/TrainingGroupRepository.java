package com.kungfu.apkfwebapp.repositories;

import com.kungfu.apkfwebapp.domain.TrainingGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainingGroupRepository extends JpaRepository<TrainingGroup, Integer> {

    TrainingGroup findByName(String name);

}
