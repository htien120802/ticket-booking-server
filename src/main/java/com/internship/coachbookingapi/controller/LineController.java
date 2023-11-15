package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.*;
import com.internship.coachbookingapi.model.LineModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.*;
import com.internship.coachbookingapi.util.CalPrice;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/lines")
public class LineController {
    private final ILineService lineService;
    private final IDepartureService departureService;
    private final IDestinationService destinationService;
    private final ICoachTypeService coachTypeService;
    private final ICoachService coachService;
    private final ModelMapper modelMapper;

    @Autowired
    public LineController(ILineService lineService, IDepartureService departureService, IDestinationService destinationService, ICoachService coachService, ICoachTypeService coachTypeService, ICoachService coachService1, ModelMapper modelMapper) {
        this.lineService = lineService;
        this.departureService = departureService;
        this.destinationService = destinationService;
        this.coachTypeService = coachTypeService;
        this.coachService = coachService1;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list dates consist line with departure and destination")
    @GetMapping()
    public ResponseEntity<?> getAvailableDates(@RequestParam UUID departureId,
                                               @RequestParam UUID destinationId) {
        Departure departure = departureService.findById(departureId).orElseThrow(() -> new RuntimeException("Departure not found!"));
        Destination destination = destinationService.findById(destinationId).orElseThrow(() -> new RuntimeException("Destination not found!"));
        List<Line> lines = lineService.findByStatusAndDepartureAndDestination(true, departure, destination);
        List<Date> lineDates = lines.stream()
                .map(Line::getDepartureDate)
                .filter(date -> date.after(new java.util.Date()))
                .distinct()
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all available dates containing lines from " + departure.getName() + " to " + destination.getName() + " successfully")
                .data(lineDates)
                .build());
    }

    @Operation(summary = "Get list lines by hour")
    @GetMapping("/date")
    public ResponseEntity<?> getAvailableLinesByDate(@RequestParam UUID departureId,
                                                     @RequestParam UUID destinationId,
                                                     @RequestParam Date departureDate,
                                                     @RequestParam UUID coachTypeId) {
        Departure departure = departureService.findById(departureId).orElseThrow(() -> new RuntimeException("Departure not found!"));
        Destination destination = destinationService.findById(destinationId).orElseThrow(() -> new RuntimeException("Destination not found!"));
        CoachType coachType = coachTypeService.findById(coachTypeId).orElseThrow(() -> new RuntimeException("Coach type not found!"));
        List<Line> lines = lineService.findByStatusAndDepartureAndDestinationAndDepartureDate(true, departure, destination, departureDate);
        // Find by type of coach
        lines = lines.stream().filter(line -> line.getCoach().getCoachType() == coachType).collect(Collectors.toList());
        List<LineModel> lineModels = lines.stream().map(line -> modelMapper.map(line, LineModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all available lines for date " + departureDate.toString() + " successfully")
                .data(lineModels)
                .build());
    }

    @Operation(summary = "Create line")
    @PostMapping
    public ResponseEntity<?> createLines() throws IOException {
        FileInputStream file = new FileInputStream("./src/main/resources/route.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        for (Row row : sheet) {
            Cell statusCell = row.getCell(1);
            Cell timeCell = row.getCell(2);
            Cell dateCell = row.getCell(3);
            Cell departureCell = row.getCell(4);
            Cell destinationCell = row.getCell(5);
            Cell coachCell = row.getCell(6);

            if (statusCell != null) {
                Boolean status = Boolean.valueOf(formatter.formatCellValue(statusCell));
                Time departureTime = Time.valueOf(formatter.formatCellValue(timeCell));
                Date departureDate = Date.valueOf(formatter.formatCellValue(dateCell));
                Departure departure = departureService.findById(UUID.fromString(formatter.formatCellValue(departureCell))).orElseThrow(() -> new RuntimeException("Could not find departure"));
                Destination destination = destinationService.findById(UUID.fromString(formatter.formatCellValue(destinationCell))).orElseThrow(() -> new RuntimeException("Could not find destination"));
                Coach coach = coachService.findById(UUID.fromString(formatter.formatCellValue(coachCell))).orElseThrow(() -> new RuntimeException("Could not find"));

                Line line = new Line();
                line.setStatus(status);
                line.setDepartureTime(departureTime);
                line.setDepartureDate(departureDate);
                line.setDeparture(departure);
                line.setDestination(destination);
                line.setCoach(coach);
                line.setPrice(new CalPrice().getPrice(departure.getSlug(), destination.getSlug(), coach.getId()));
                lineService.save(line);
            }
        }
        workbook.close();
        file.close();

        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Create lines successfully")
                .build());
    }
}
