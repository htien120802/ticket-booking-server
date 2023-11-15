package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.PickUpType;
import com.internship.coachbookingapi.model.PickUpTypeModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IPickUpTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pickuptypes")
public class PickUpTypeController {
    private final IPickUpTypeService pickUpTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public PickUpTypeController(IPickUpTypeService pickUpTypeService, ModelMapper modelMapper) {
        this.pickUpTypeService = pickUpTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get pick up types")
    @GetMapping
    public ResponseEntity<?> getAllPickUpTypes() {
        List<PickUpType> pickUpTypes = pickUpTypeService.findAll();
        List<PickUpTypeModel> pickUpTypeModels = pickUpTypes.stream().map(pickUpType -> modelMapper.map(pickUpType, PickUpTypeModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("List all types of pick up successfully")
                .data(pickUpTypeModels)
                .build());
    }

    @Operation(summary = "Create pick up type")
    @PostMapping
    public ResponseEntity<?> createPickUpType(@RequestParam String name) {
        PickUpType pickUpType = new PickUpType();
        pickUpType.setName(name);
        pickUpTypeService.save(pickUpType);
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("Create types of pick up successfully")
                .build());
    }
}
