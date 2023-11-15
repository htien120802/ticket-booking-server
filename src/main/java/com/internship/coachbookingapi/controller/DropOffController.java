package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Destination;
import com.internship.coachbookingapi.entity.DropOff;
import com.internship.coachbookingapi.entity.DropOffType;
import com.internship.coachbookingapi.model.DropOffModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.IDestinationService;
import com.internship.coachbookingapi.service.IDropOffService;
import com.internship.coachbookingapi.service.IDropOffTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/dropoffs")
public class DropOffController {
    private final IDropOffService dropOffService;
    private final IDestinationService destinationService;
    private final IDropOffTypeService dropOffTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public DropOffController(IDropOffService dropOffService, IDestinationService destinationService, IDropOffTypeService dropOffTypeService, ModelMapper modelMapper) {
        this.dropOffService = dropOffService;
        this.destinationService = destinationService;
        this.dropOffTypeService = dropOffTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list pick up places by destination and pick up type")
    @GetMapping
    public ResponseEntity<?> getDropOffByDestinationAndDropOffType(@RequestParam UUID destinationId,
                                                                   @RequestParam UUID dropOffTypeId) {
        Destination selectedDestination = destinationService.findById(destinationId).orElseThrow(() -> new RuntimeException("Destination not found"));
        DropOffType dropOffType = dropOffTypeService.findById(dropOffTypeId).orElseThrow(() -> new RuntimeException("Drop off type not found"));
        List<DropOff> dropOffs = dropOffService.findByDestinationAndDropOffType(selectedDestination, dropOffType);
        List<DropOffModel> dropOffModels = dropOffs.stream().map(dropOff -> modelMapper.map(dropOff, DropOffModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("List drop offs successfully")
                .data(dropOffModels)
                .build());
    }

    @Operation(summary = "Create drop off place")
    @PostMapping
    public ResponseEntity<?> createDropOff(@RequestParam UUID destinationId,
                                           @RequestParam UUID dropOffTypeId) throws IOException {
        FileInputStream file = new FileInputStream("./src/main/resources/route.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(6);

        Destination destination = destinationService.findById(destinationId).orElseThrow(() -> new RuntimeException("Destination not found"));
        DropOffType dropOffType = dropOffTypeService.findById(dropOffTypeId).orElseThrow(() -> new RuntimeException("Drop off type not found"));
        DataFormatter formatter = new DataFormatter();

        for (Row row : sheet) {
            Cell locationCell = row.getCell(0);

            if (locationCell != null) {
                String location = formatter.formatCellValue(locationCell);

                DropOff dropOff = new DropOff();
                dropOff.setLocation(location);
                dropOff.setDropOffType(dropOffType);
                dropOff.setDestination(destination);
                dropOffService.save(dropOff);
            }
        }

        workbook.close();
        file.close();

        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Create drop offs successfully")
                .build());
    }
}
