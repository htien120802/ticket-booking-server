package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Line;
import com.internship.coachbookingapi.entity.LineSeat;
import com.internship.coachbookingapi.entity.LineSeatKey;
import com.internship.coachbookingapi.entity.Seat;
import com.internship.coachbookingapi.model.LineSeatModel;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.ILineSeatService;
import com.internship.coachbookingapi.service.ILineService;
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
@RequestMapping("/api/v1/seats")
public class LineSeatController {
    private final ILineSeatService lineSeatService;
    private final ILineService lineService;
    private final ModelMapper modelMapper;

    @Autowired
    public LineSeatController(ILineSeatService lineSeatService, ILineService lineService, ModelMapper modelMapper) {
        this.lineSeatService = lineSeatService;
        this.lineService = lineService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Get list coaches of line")
    @GetMapping("/lines/{lineId}")
    public ResponseEntity<?> getSeatsByLine(@PathVariable UUID lineId) {
        Line line = lineService.findById(lineId).orElseThrow(() -> new RuntimeException("Line not found"));
        List<LineSeatModel> lineSeatModels = line.getLineSeats()
                .stream()
                .map(lineSeat -> {
                    LineSeatModel lineSeatModel = modelMapper.map(lineSeat, LineSeatModel.class);
//                    lineSeatModel.setSeat_id(lineSeat.getCompositeKey().getSeat_id());
//                    lineSeatModel.setLine_id(lineSeat.getCompositeKey().getLine_id());
                    return lineSeatModel;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel
                .builder()
                .status(200)
                .error(false)
                .message("Get seats by line successfully")
                .data(lineSeatModels)
                .build());
    }

    @Operation(summary = "Create list coaches")
    @PostMapping("/lines")
    public ResponseEntity<?> createLine_Seats() throws IOException {
        FileInputStream file = new FileInputStream("./src/main/resources/route.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(0);

        DataFormatter formatter = new DataFormatter();

        for (Row row : sheet) {
            Cell lineCell = row.getCell(0);
//            Cell availableCell = row.getCell(2);

            if (lineCell != null) {
                LineSeat lineSeat = new LineSeat();
                UUID lineId = UUID.fromString(formatter.formatCellValue(lineCell));
//                boolean isAvailable = Boolean.parseBoolean(formatter.formatCellValue(availableCell));
                Line line = lineService.findById(lineId).orElseThrow(() -> new RuntimeException("Could not find line"));
                List<Seat> seats = line.getCoach().getSeats();
                // 1 line -> 1 coach -> 1 type, 1 coach -> 1 seat
                for (Seat seat : seats) {
                    LineSeatKey lineSeatKey = new LineSeatKey(lineId, seat.getId());
                    lineSeat.setLineSeatKey(lineSeatKey);
                    lineSeat.setSeat(seat);
                    lineSeat.setLine(line);
                    lineSeat.setTicket(null);
                    lineSeat.set_available(true);
                    lineSeatService.save(lineSeat);
                }
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
