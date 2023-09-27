package com.kungfu.apkfwebapp.api.mapper;

import com.kungfu.apkfwebapp.api.model.TrainingGroupDTO;
import com.kungfu.apkfwebapp.domain.TrainingGroup;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TrainingGroupMapper {

    TrainingGroupMapper INSTANCE = Mappers.getMapper(TrainingGroupMapper.class);

    TrainingGroupDTO trainingGroupToTrainingGroupDTO(TrainingGroup trainingGroup);

    TrainingGroup trainingGroupDTOToTrainingGroup(TrainingGroupDTO trainingGroupDTO);
}
