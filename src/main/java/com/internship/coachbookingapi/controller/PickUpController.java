package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.*;
import com.internship.coachbookingapi.model.PickUpModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IDepartureService;
import com.internship.coachbookingapi.service.IDestinationService;
import com.internship.coachbookingapi.service.IPickUpService;
import com.internship.coachbookingapi.service.IPickUpTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/pickups")
public class PickUpController {
    private final IPickUpService pickUpService;
    private final IDepartureService departureService;
    private final IDestinationService destinationService;
    private final IPickUpTypeService pickUpTypeService;
    private final ModelMapper modelMapper;

    public PickUpController(IPickUpService pickUpService, IDepartureService departureService, IDestinationService destinationService, IPickUpTypeService pickUpTypeService, ModelMapper modelMapper) {
        this.pickUpService = pickUpService;
        this.departureService = departureService;
        this.destinationService = destinationService;
        this.pickUpTypeService = pickUpTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list pick up places by destination and pick up type")
    @GetMapping()
    public ResponseEntity<?> getPickUpByDepartureAndPickUpType(@RequestParam UUID departureId,
                                                               @RequestParam UUID pickUpTypeId) {
        Departure departure = departureService.findById(departureId).orElseThrow(() -> new RuntimeException("Departure not found"));
        PickUpType pickUpType = pickUpTypeService.findById(pickUpTypeId).orElseThrow(() -> new RuntimeException("Pick up type not found"));
        List<PickUp> pickUps = pickUpService.findByDepartureAndPickUpType(departure, pickUpType);
        List<PickUpModel> pickUpModels = pickUps.stream().map(pickUp -> modelMapper.map(pickUp, PickUpModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("List pick ups successfully")
                .data(pickUpModels)
                .build());
    }

    @Operation(summary = "Create pick up place by departure")
    @PostMapping()
    public ResponseEntity<?> createPickUp(@RequestParam UUID departureId) {
        Departure departure = departureService.findById(departureId).orElseThrow(() -> new RuntimeException("Departure not found"));
        Destination destination = destinationService.findBySlug(departure.getSlug()).orElseThrow(() -> new RuntimeException("Destination not found"));
        for (DropOff dropOff : destination.getDropOffs()) {
            PickUp pickUp = new PickUp();
            if (dropOff.getDropOffType().getName().equals("Xuống xe tại trạm")) {
                PickUpType pickUpType = pickUpTypeService.findById(UUID.fromString("c0a80203-8b83-1195-818b-84e8c50c0402")).orElseThrow(() -> new RuntimeException("Pick up type not found"));
                pickUp.setLocation(dropOff.getLocation());
                pickUp.setPickUpType(pickUpType);
                pickUp.setDeparture(departure);
            } else {
                PickUpType pickUpType = pickUpTypeService.findById(UUID.fromString("c0a80203-8b83-1195-818b-84e8fb680403")).orElseThrow(() -> new RuntimeException("Pick up type not found"));
                pickUp.setLocation(dropOff.getLocation());
                pickUp.setPickUpType(pickUpType);
                pickUp.setDeparture(departure);
            }
            pickUpService.save(pickUp);
        }
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("Create pickup successfully")
                .build());
    }


}
