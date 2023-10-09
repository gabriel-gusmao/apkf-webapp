package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.mapper.MemberMapper;
import com.kungfu.apkfwebapp.api.mapper.TrainingGroupMapper;
import com.kungfu.apkfwebapp.api.model.TrainingGroupDTO;
import com.kungfu.apkfwebapp.api.model.TrainingGroupListDTO;
import com.kungfu.apkfwebapp.domain.TrainingGroup;
import com.kungfu.apkfwebapp.repositories.MemberRepository;
import com.kungfu.apkfwebapp.repositories.TrainingGroupRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class TrainingGroupServiceImplTest {

    public static final String NAME_1 = "Phoenix Vermelha";
    public static final String CITY_1 = "Campinas";
    public static final int ID_1 = 1;
    public static final String NAME_2 = "Grou Vermelho";
    public static final String CITY_2 = "São Carlos";
    public static final int ID_2 = 2;

    @Mock
    TrainingGroupRepository trainingGroupRepository;

    TrainingGroupMapper trainingGroupMapper = TrainingGroupMapper.INSTANCE;

    MemberRepository memberRepository;

    MemberMapper memberMapper = MemberMapper.INSTANCE;

    TrainingGroupService trainingGroupService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        trainingGroupService = new TrainingGroupServiceImpl(trainingGroupMapper, trainingGroupRepository,
                                                            memberMapper, memberRepository);
    }

    @Test
    void getAllTrainingGroups() {
        TrainingGroup trainingGroup1 = new TrainingGroup();
        trainingGroup1.setName(NAME_1);
        trainingGroup1.setCity(CITY_1);
        trainingGroup1.setId(ID_1);
        TrainingGroup trainingGroup2 = new TrainingGroup();
        trainingGroup2.setName(NAME_2);
        trainingGroup2.setCity(CITY_2);
        trainingGroup2.setId(ID_2);

        when(trainingGroupRepository.findAll()).thenReturn(Arrays.asList(trainingGroup1, trainingGroup2));

        //when
        TrainingGroupListDTO trainingGroupListDTO = trainingGroupService.getAllTrainingGroups();

        //then
        assertEquals(2, trainingGroupListDTO.getGroups().size());
    }

    @Test
    void getTrainingGroupById() {
        //given
        TrainingGroup trainingGroup1 = new TrainingGroup();
        trainingGroup1.setName(NAME_1);
        trainingGroup1.setCity(CITY_1);
        trainingGroup1.setId(ID_1);

        when(trainingGroupRepository.findById(anyInt())).thenReturn(Optional.ofNullable(trainingGroup1));

        //when
        TrainingGroupDTO trainingGroupDTO = trainingGroupService.getTrainingGroupById(1);

        //then
        assertEquals(NAME_1, trainingGroupDTO.getName());
    }

//
//    @Test
//    void getAllMembersByTrainingGroupId() {
//    }

    @Test
    void createNewTrainingGroup() {
        //given
        TrainingGroupDTO trainingGroupDTO = new TrainingGroupDTO();
        trainingGroupDTO.setName(NAME_1);
        trainingGroupDTO.setCity(CITY_1);

        TrainingGroup savedTrainingGroup = new TrainingGroup();
        savedTrainingGroup.setName(trainingGroupDTO.getName());
        savedTrainingGroup.setCity(trainingGroupDTO.getCity());
        savedTrainingGroup.setId(1);

        when(trainingGroupRepository.save(any(TrainingGroup.class))).thenReturn(savedTrainingGroup);

        //when
        TrainingGroupDTO savedDTO = trainingGroupService.createNewTrainingGroup(trainingGroupDTO);

        //then
        assertEquals(trainingGroupDTO.getName(), savedDTO.getName());
        assertEquals(trainingGroupDTO.getCity(), savedDTO.getCity());
        assertEquals("/training_group/1", savedDTO.getTrainingGroupUrl());
    }

    @Test
    void saveTrainingGroupByDTO() {
        //given
        TrainingGroupDTO trainingGroupDTO = new TrainingGroupDTO();
        trainingGroupDTO.setName(NAME_1);
        trainingGroupDTO.setCity(CITY_1);

        TrainingGroup savedTrainingGroup = new TrainingGroup();
        savedTrainingGroup.setName(trainingGroupDTO.getName());
        savedTrainingGroup.setCity(trainingGroupDTO.getCity());
        savedTrainingGroup.setId(1);

        when(trainingGroupRepository.save(any(TrainingGroup.class))).thenReturn(savedTrainingGroup);

        //when
        TrainingGroupDTO savedDTO = trainingGroupService.saveTrainingGroupByDTO(1, trainingGroupDTO);

        //then
        assertEquals(trainingGroupDTO.getName(), savedDTO.getName());
        assertEquals(trainingGroupDTO.getCity(), savedDTO.getCity());
        assertEquals("/training_group/1", savedDTO.getTrainingGroupUrl());
    }

//    @Test
//    void patchTrainingGroup() {
//    }

    @Test
    void deleteTrainingGroupById() {

        int id = 1;

        trainingGroupRepository.deleteById(id);

        verify(trainingGroupRepository, times(1)).deleteById(anyInt());
    }
}