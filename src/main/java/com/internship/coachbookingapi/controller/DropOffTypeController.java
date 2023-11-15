package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.DropOffType;
import com.internship.coachbookingapi.model.DropOffTypeModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IDropOffTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dropofftypes")
public class DropOffTypeController {
    private final IDropOffTypeService dropOffTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public DropOffTypeController(IDropOffTypeService dropOffTypeService, ModelMapper modelMapper) {
        this.dropOffTypeService = dropOffTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list drop off types")
    @GetMapping
    public ResponseEntity<?> getAllDropOffTypes() {
        List<DropOffType> dropOffTypes = dropOffTypeService.findAll();
        List<DropOffTypeModel> dropOffTypeModels = dropOffTypes.stream().map(dropOffType -> modelMapper.map(dropOffType, DropOffTypeModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("List all types of drop off successfully")
                .data(dropOffTypeModels)
                .build());
    }

    @Operation(summary = "Create drop off types")
    @PostMapping
    public ResponseEntity<?> createDropOffType(@RequestParam String name) {
        DropOffType dropOffType = new DropOffType();
        dropOffType.setName(name);
        dropOffTypeService.save(dropOffType);
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("Create types of drop off successfully")
                .build());
    }
}
