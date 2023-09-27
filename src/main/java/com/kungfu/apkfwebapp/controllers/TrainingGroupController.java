package com.kungfu.apkfwebapp.controllers;

import com.kungfu.apkfwebapp.api.model.TrainingGroupDTO;
import com.kungfu.apkfwebapp.api.model.TrainingGroupListDTO;
import com.kungfu.apkfwebapp.services.TrainingGroupService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(TrainingGroupController.BASE_URL)
public class TrainingGroupController {

    public static final String BASE_URL = "/traininggroup";

    private final TrainingGroupService trainingGroupService;

    public TrainingGroupController(TrainingGroupService trainingGroupService) {
        this.trainingGroupService = trainingGroupService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public TrainingGroupListDTO getListOfTrainingGroups() {
        return new TrainingGroupListDTO(trainingGroupService.getAllTrainingGroups().getGroups());
    }

    @GetMapping({"/{name}"})
    @ResponseStatus(HttpStatus.OK)
    public TrainingGroupDTO getTrainingGroupByName(@PathVariable String name) {
        return trainingGroupService.getTrainingGroupByName(name);
    }

    @GetMapping({"/{city}"})
    @ResponseStatus(HttpStatus.OK)
    public TrainingGroupListDTO getTrainingGroupsByCity(@PathVariable String city) {
        return new TrainingGroupListDTO(trainingGroupService.getTrainingGroupsByCity(city).getGroups());
    }

    @GetMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TrainingGroupDTO getTrainingGroupById(@PathVariable int id) {
        return trainingGroupService.getTrainingGroupById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrainingGroupDTO createNewTrainingGroup(@RequestBody TrainingGroupDTO trainingGroupDTO) {
        return trainingGroupService.createNewTrainingGroup(trainingGroupDTO);
    }

    @PutMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TrainingGroupDTO updateTrainingGroup(@PathVariable int id, @RequestBody TrainingGroupDTO trainingGroupDTO) {
        return trainingGroupService.saveTrainingGroupByDTO(id, trainingGroupDTO);
    }

    @PatchMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public TrainingGroupDTO patchTrainingGroup(@PathVariable int id, @RequestBody TrainingGroupDTO trainingGroupDTO) {
        return trainingGroupService.patchTrainingGroup(id, trainingGroupDTO);
    }

    @DeleteMapping({"/{id}"})
    @ResponseStatus(HttpStatus.OK)
    public void deleteTrainingGroup(@PathVariable int id) {
        trainingGroupService.deleteTrainingGroupById(id);
    }
}
