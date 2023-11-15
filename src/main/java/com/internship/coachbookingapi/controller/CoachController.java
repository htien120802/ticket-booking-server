package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.Coach;
import com.internship.coachbookingapi.entity.CoachType;
import com.internship.coachbookingapi.model.CoachDto;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.service.ICoachService;
import com.internship.coachbookingapi.service.ICoachTypeService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/coaches")
public class CoachController {
    private final ICoachService coachService;
    private final ICoachTypeService coachTypeService;
    private final ModelMapper modelMapper;

    @Autowired
    public CoachController(ICoachService coachService, ICoachTypeService coachTypeService, ModelMapper modelMapper) {
        this.coachService = coachService;
        this.coachTypeService = coachTypeService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Add list route")
    @PostMapping
    public ResponseEntity<?> createCoachTypes() throws IOException {
        FileInputStream file = new FileInputStream("./src/main/resources/route.xlsx");
        Workbook workbook = new XSSFWorkbook(file);
        Sheet sheet = workbook.getSheetAt(1);


        for (Row row : sheet) {
            // Get coachTypeId
            Cell idCell = row.getCell(0);

            if (idCell != null) {
                UUID typeId = UUID.fromString(idCell.getStringCellValue());
                CoachType coachType = coachTypeService.findById(typeId).orElse(null);
                Coach coach = new Coach();
                coach.setCoachType(coachType);
                coachService.save(coach);
            }
        }

        workbook.close();
        file.close();

        List<Coach> coaches = coachService.findAll();
        List<CoachDto> coachModels = coaches.stream().map(coach -> modelMapper.map(coach, CoachDto.class)).collect(Collectors.toList());
        return ResponseEntity.ok(ResponseModel.builder()
                .status(200)
                .error(false)
                .message("Get all coaches successfully")
                .data(coachModels)
                .build());
    }
}
