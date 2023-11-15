package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Passenger;
import com.internship.coachbookingapi.model.PassengerModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IPassengerService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/passengers")
public class PassengerController {
    private final IPassengerService passengerService;
    private final ModelMapper modelMapper;

    public PassengerController(IPassengerService passengerService, ModelMapper modelMapper) {
        this.passengerService = passengerService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public ResponseEntity<?> savePassengers(@RequestBody PassengerModel passengerModel) {
        Passenger passenger = modelMapper.map(passengerModel, Passenger.class);
        passengerService.save(passenger);
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("Create passenger successfully")
                .build());
    }
}
