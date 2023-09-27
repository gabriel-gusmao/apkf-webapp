package com.kungfu.apkfwebapp.services;

import com.kungfu.apkfwebapp.api.mapper.TrainingGroupMapper;
import com.kungfu.apkfwebapp.api.model.TrainingGroupDTO;
import com.kungfu.apkfwebapp.api.model.TrainingGroupListDTO;
import com.kungfu.apkfwebapp.controllers.TrainingGroupController;
import com.kungfu.apkfwebapp.domain.TrainingGroup;
import com.kungfu.apkfwebapp.repositories.TrainingGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainingGroupServiceImpl implements TrainingGroupService {

    private final TrainingGroupMapper trainingGroupMapper;
    private final TrainingGroupRepository trainingGroupRepository;

    public TrainingGroupServiceImpl(TrainingGroupMapper trainingGroupMapper, TrainingGroupRepository trainingGroupRepository) {
        this.trainingGroupMapper = trainingGroupMapper;
        this.trainingGroupRepository = trainingGroupRepository;
    }

    @Override
    public TrainingGroupListDTO getAllTrainingGroups() {
        List<TrainingGroupDTO> trainingGroupDTOS = trainingGroupRepository
                .findAll()
                .stream()
                .map(trainingGroup -> {
                    TrainingGroupDTO trainingGroupDTO = trainingGroupMapper.trainingGroupToTrainingGroupDTO(trainingGroup);
                    trainingGroupDTO.setTrainingGroupUrl(getTrainingGroupUrl(trainingGroup.getId()));
                    return trainingGroupDTO;
                })
                .collect(Collectors.toList());
        return new TrainingGroupListDTO(trainingGroupDTOS);
    }

    @Override
    public TrainingGroupDTO getTrainingGroupByName(String name) {
        return trainingGroupMapper.trainingGroupToTrainingGroupDTO(trainingGroupRepository.findByName(name));
    }

    @Override
    public TrainingGroupListDTO getTrainingGroupsByCity(String city) {
        List<TrainingGroupDTO> trainingGroupDTOS = trainingGroupRepository
                .findAll()
                .stream()
                .filter(s -> s.getCity().equals(city))
                .map(trainingGroup -> {
                    TrainingGroupDTO trainingGroupDTO = trainingGroupMapper.trainingGroupToTrainingGroupDTO(trainingGroup);
                    trainingGroupDTO.setTrainingGroupUrl(getTrainingGroupUrl(trainingGroup.getId()));
                    return trainingGroupDTO;
                })
                .collect(Collectors.toList());
        return new TrainingGroupListDTO(trainingGroupDTOS);
    }

    @Override
    public TrainingGroupDTO getTrainingGroupById(int id) {
        return trainingGroupRepository.findById(id)
                .map(trainingGroupMapper::trainingGroupToTrainingGroupDTO)
                .map(trainingGroupDTO -> {
                    trainingGroupDTO.setTrainingGroupUrl(getTrainingGroupUrl(id));
                    return trainingGroupDTO;
                })
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public TrainingGroupDTO createNewTrainingGroup(TrainingGroupDTO trainingGroupDTO) {
        return saveAndReturnDTO(trainingGroupMapper.trainingGroupDTOToTrainingGroup(trainingGroupDTO));
    }

    @Override
    public TrainingGroupDTO saveTrainingGroupByDTO(int id, TrainingGroupDTO trainingGroupDTO) {

        TrainingGroup trainingGroupToSave = trainingGroupMapper.trainingGroupDTOToTrainingGroup(trainingGroupDTO);
        trainingGroupToSave.setId(id);

        return saveAndReturnDTO(trainingGroupToSave);
    }

    @Override
    public TrainingGroupDTO patchTrainingGroup(int id, TrainingGroupDTO trainingGroupDTO) {
       return trainingGroupRepository.findById(id)
               .map(trainingGroup -> {
                   if (trainingGroupDTO.getName() != null) {
                       trainingGroup.setName(trainingGroupDTO.getName());
                   }
                   if (trainingGroupDTO.getTrainingLocation() != null) {
                       trainingGroup.setTrainingLocation(trainingGroupDTO.getTrainingLocation());
                   }
                   if (trainingGroupDTO.getCity() != null) {
                       trainingGroup.setCity(trainingGroupDTO.getCity());
                   }

                   return saveAndReturnDTO(trainingGroup);
               })
               .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public void deleteTrainingGroupById(int id) {
        trainingGroupRepository.deleteById(id);
    }

    private String getTrainingGroupUrl(int id) {
        return TrainingGroupController.BASE_URL + "/" + id;
    }

    private TrainingGroupDTO saveAndReturnDTO(TrainingGroup trainingGroup) {
        TrainingGroup savedTrainingGroup = trainingGroupRepository.save(trainingGroup);

        TrainingGroupDTO returnDTO = trainingGroupMapper.trainingGroupToTrainingGroupDTO(savedTrainingGroup);

        returnDTO.setTrainingGroupUrl(getTrainingGroupUrl(savedTrainingGroup.getId()));

        return returnDTO;
    }
}
