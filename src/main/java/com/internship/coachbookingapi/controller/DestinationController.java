package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Departure;
import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.model.DestinationModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IDepartureService;
import com.internship.coachbookingapi.service.IDestinationService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/destinations")
public class DestinationController {
    private final IDestinationService destinationService;
    private final IDepartureService departureService;
    private final ModelMapper modelMapper;

    public DestinationController(IDestinationService destinationService, IDepartureService departureService, ModelMapper modelMapper) {
        this.destinationService = destinationService;
        this.departureService = departureService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list destinations")
    @GetMapping
    public ResponseEntity<?> getAllDestinations() {
        List<Destination> destinations = destinationService.findAll();
        List<DestinationModel> destinationModels = destinations
                .stream()
                .map(destination -> modelMapper.map(destination, DestinationModel.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .error(false)
                .status(200)
                .message("Get all destinations successfully")
                .data(destinationModels)
                .build());
    }

    @Operation(summary = "Get destination by slug")
    @GetMapping("/{slug}")
    public ResponseEntity<?> getDestinationBySlug(@PathVariable String slug) {
        Destination destination = destinationService.findBySlug(slug).orElseThrow(() -> new RuntimeException("Destination not found"));
        DestinationModel destinationModel = modelMapper.map(destination, DestinationModel.class);
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get destination successfully")
                .data(destinationModel)
                .build());
    }

    @Operation(summary = "Get list destinations by departure")
    @GetMapping("/departures/{slug}")
    public ResponseEntity<?> getDestinationsByDeparture(@PathVariable String slug) {
        Departure departure = departureService.findBySlug(slug).orElseThrow(() -> new RuntimeException("Departure not found"));
        List<Destination> destinations = departure.getDestinations();
        List<DestinationModel> destinationModels = destinations
                .stream()
                .map(destination -> modelMapper.map(destination, DestinationModel.class))
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .error(false)
                .status(200)
                .message("Get all destinations from " + departure.getName() + " successfully")
                .data(destinationModels)
                .build());
    }

    @Operation(summary = "Create list destinations for departure")
    @PostMapping("/dep_des")
    public ResponseEntity<?> createLine_Seats() throws IOException {
        FileInputStream file = new FileInputStream("./src/main/resources/route.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        for (Row row : sheet) {
            Cell depCell = row.getCell(0);
            Cell desCell = row.getCell(1);

            if (desCell != null) {
                String departureSlug = formatter.formatCellValue(depCell);
                String destinationSlug = formatter.formatCellValue(desCell);
                Departure departure = departureService.findBySlug(departureSlug).orElseThrow(() -> new RuntimeException("Could not find departure"));
                Destination destination = destinationService.findBySlug(destinationSlug).orElseThrow(() -> new RuntimeException("Could not find destination"));
                departure.getDestinations().add(destination);
                destination.getDepartures().add(departure);
                departureService.save(departure);
                destinationService.save(destination);
            }
        }

        workbook.close();
        file.close();

        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Create seats for line successfully")
                .build());
    }
}
