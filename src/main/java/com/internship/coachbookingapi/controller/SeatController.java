package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Coach;
import com.internship.coachbookingapi.entity.CoachType;
import com.internship.coachbookingapi.entity.Seat;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.model.SeatModel;
import com.internship.coachbookingapi.service.ICoachTypeService;
import com.internship.coachbookingapi.service.ISeatService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/seats")
public class SeatController {
    private final ISeatService seatService;
    private final ICoachTypeService coachTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public SeatController(ISeatService seatService, ICoachTypeService coachTypeService, ModelMapper modelMapper) {
        this.seatService = seatService;
        this.coachTypeService = coachTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Create list seats")
    @PostMapping
    public ResponseEntity<?> createSeats(@RequestParam UUID coachTypeId, @RequestParam int sheetNo) throws IOException {
        FileInputStream file = new FileInputStream("./src/main/resources/route.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(sheetNo);

        CoachType coachType = coachTypeService.findById(coachTypeId).orElseThrow(() -> new RuntimeException("Could not find coach type"));
        DataFormatter formatter = new DataFormatter();
        for (Coach coach : coachType.getCoaches()) {

            for (Row row : sheet) {
                Cell positionCell = row.getCell(0);
                Cell deckerCell = row.getCell(1);
                Cell isBlankCell = row.getCell(2);

                if (positionCell != null) {
                    String position = formatter.formatCellValue(positionCell);
                    int decker = Integer.parseInt(formatter.formatCellValue(deckerCell));
                    boolean isBlank = Boolean.parseBoolean(formatter.formatCellValue(isBlankCell).toLowerCase());

                    Seat seat = new Seat();
                    seat.set_blank(isBlank);
                    seat.setCoach(coach);
                    seat.setDecker(decker);
                    seat.setPosition(position);
                    seatService.save(seat);
                }
            }
        }

        workbook.close();
        file.close();

        List<Seat> seats = seatService.findAll()
                .stream()
                .filter(s -> s.getCoach().getCoachType().getId().equals(coachTypeId))
                .collect(Collectors.toList());
        List<SeatModel> seatModels = seats.stream().map(seat -> modelMapper.map(seat, SeatModel.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all seats of coaches with specific type successfully")
                .data(seatModels)
                .build());
    }
}
