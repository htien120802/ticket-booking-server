package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.model.DepartureModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IDepartureService;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/departures")
public class DepartureController {
    private final IDepartureService departureService;
    private final ModelMapper modelMapper;

    @Autowired
    public DepartureController(IDepartureService departureService, ModelMapper modelMapper) {
        this.departureService = departureService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list depatures")
    @GetMapping
    public ResponseEntity<?> getAllDepartures() {
        List<Departure> departures = departureService.findAll();
        List<DepartureModel> departureModels = modelMapper.map(departures, new TypeToken<List<DepartureModel>>() {
        }.getType());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all departures successfully")
                .data(departureModels)
                .build());
    }

    @Operation(summary = "Get departures by slug")
    @GetMapping("/{slug}")
    public ResponseEntity<?> getDepartureBySlug(@PathVariable String slug) {
        Departure departure = departureService.findBySlug(slug).orElseThrow(() -> new RuntimeException("Departure not found"));
        DepartureModel departureModel = modelMapper.map(departure, DepartureModel.class);
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get departure successfully")
                .data(departureModel)
                .build());
    }
}
