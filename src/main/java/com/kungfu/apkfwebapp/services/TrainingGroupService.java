package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.model.MemberDTO;
import com.kungfu.apkfwebapp.api.model.MemberListDTO;
import com.kungfu.apkfwebapp.api.model.TrainingGroupDTO;
import com.kungfu.apkfwebapp.api.model.TrainingGroupListDTO;
import com.kungfu.apkfwebapp.domain.Member;

import java.util.Set;

public interface TrainingGroupService {

    TrainingGroupListDTO getAllTrainingGroups();

//    TrainingGroupDTO getTrainingGroupByName(String name);
//
//    TrainingGroupListDTO getTrainingGroupsByCity(String city);

    TrainingGroupDTO getTrainingGroupById(int id);

    MemberListDTO getAllMembersByTrainingGroupId(int id);

    TrainingGroupDTO createNewTrainingGroup(TrainingGroupDTO trainingGroupDTO);

    TrainingGroupDTO saveTrainingGroupByDTO(int id, TrainingGroupDTO trainingGroupDTO);

    TrainingGroupDTO patchTrainingGroup(int id, TrainingGroupDTO trainingGroupDTO);

    void deleteTrainingGroupById(int id);
}
