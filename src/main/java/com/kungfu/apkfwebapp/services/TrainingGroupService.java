package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.model.TrainingGroupDTO;
import com.kungfu.apkfwebapp.api.model.TrainingGroupListDTO;

public interface TrainingGroupService {

    TrainingGroupListDTO getAllTrainingGroups();

    TrainingGroupDTO getTrainingGroupByName(String name);

    TrainingGroupListDTO getTrainingGroupsByCity(String city);

    TrainingGroupDTO getTrainingGroupById(int id);

    TrainingGroupDTO createNewTrainingGroup(TrainingGroupDTO trainingGroupDTO);

    TrainingGroupDTO saveTrainingGroupByDTO(int id, TrainingGroupDTO trainingGroupDTO);

    TrainingGroupDTO patchTrainingGroup(int id, TrainingGroupDTO trainingGroupDTO);

    void deleteTrainingGroupById(int id);
}
