package com.kungfu.apkfwebapp.api.mapper;

import com.kungfu.apkfwebapp.api.model.TrainingGroupDTO;
import com.kungfu.apkfwebapp.domain.Member;
import com.kungfu.apkfwebapp.domain.TrainingGroup;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class TrainingGroupMapperTest {

    public static final String NAME = "Yellow Monkey";
    public static final String TRAINING_LOCATION = "Rua de baixo, 7717";
    public static final String CITY = "Terra dos Piás";
    public static final Set<Member> MEMBERS = new HashSet<>();

    TrainingGroupMapper trainingGroupMapper = TrainingGroupMapper.INSTANCE;

    @Test
    void trainingGroupToTrainingGroupDTO() {
        //given
        TrainingGroup trainingGroup = new TrainingGroup();
        trainingGroup.setName(NAME);
        trainingGroup.setTrainingLocation(TRAINING_LOCATION);
        trainingGroup.setCity(CITY);
        trainingGroup.setMembers(MEMBERS);

        //when
        TrainingGroupDTO trainingGroupDTO = trainingGroupMapper.trainingGroupToTrainingGroupDTO(trainingGroup);

        //then
        assertEquals(trainingGroup.getName(), trainingGroupDTO.getName());
        assertEquals(trainingGroup.getTrainingLocation(), trainingGroupDTO.getTrainingLocation());
        assertEquals(trainingGroup.getCity(), trainingGroupDTO.getCity());
        assertEquals(trainingGroup.getMembers(), trainingGroupDTO.getMembers());
    }

    @Test
    void trainingGroupDTOToTrainingGroup() {
        //given
        TrainingGroupDTO trainingGroupDTO = new TrainingGroupDTO();
        trainingGroupDTO.setName(NAME);
        trainingGroupDTO.setTrainingLocation(TRAINING_LOCATION);
        trainingGroupDTO.setCity(CITY);
        trainingGroupDTO.setMembers(MEMBERS);

        //when
        TrainingGroup trainingGroup = trainingGroupMapper.trainingGroupDTOToTrainingGroup(trainingGroupDTO);

        //then
        assertEquals(trainingGroup.getName(), trainingGroupDTO.getName());
        assertEquals(trainingGroup.getTrainingLocation(), trainingGroupDTO.getTrainingLocation());
        assertEquals(trainingGroup.getCity(), trainingGroupDTO.getCity());
        assertEquals(trainingGroup.getMembers(), trainingGroupDTO.getMembers());
    }
}