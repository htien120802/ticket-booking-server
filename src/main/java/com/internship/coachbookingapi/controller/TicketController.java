package com.internship.coachbookingapi.controller;

import com.internship.coachbookingapi.entity.*;
import com.internship.coachbookingapi.model.ResponseModel;
import com.internship.coachbookingapi.model.TicketModel;
import com.internship.coachbookingapi.service.*;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
public class TicketController {
    private final ITicketService ticketService;
    private final ILineService lineService;
    private final ILineSeatService lineSeatService;
    private final IPickUpService pickUpService;
    private final IDropOffService dropOffService;
    private final ModelMapper modelMapper;

    public TicketController(ITicketService ticketService, ILineService lineService, ILineSeatService lineSeatService, IPickUpService pickUpService, IDropOffService dropOffService, ModelMapper modelMapper) {
        this.ticketService = ticketService;
        this.lineService = lineService;
        this.lineSeatService = lineSeatService;
        this.pickUpService = pickUpService;
        this.dropOffService = dropOffService;
        this.modelMapper = modelMapper;
    }

    @Operation(summary = "Book ticket")
    @PostMapping
    public ResponseEntity<?> createTicket(@RequestBody TicketModel ticketModel) {
        Ticket ticket = modelMapper.map(ticketModel, Ticket.class);
        List<Passenger> passengers = ticket.getPassengers();
        List<LineSeat> lineSeats = ticket.getLineSeats();
        List<Line> lines = ticket.getLines();
        List<PickUp> pickUps = ticket.getPickUps();
        List<DropOff> dropOffs = ticket.getDropOffs();

        ticket.setPassengers(new ArrayList<>());
        ticket.setLineSeats(new ArrayList<>());
        ticket.setLines(new ArrayList<>());
        ticket.setPickUps(new ArrayList<>());
        ticket.setDropOffs(new ArrayList<>());

        // Passenger
        for (int i = 0; i < passengers.size(); i++) {
            passengers.get(i).setTicket(ticket);
            ticket.getPassengers().add(passengers.get(i));
        }

        ticket = ticketService.save(ticket);

        // LineSeats
        for (int i = 0; i < lineSeats.size(); i++) {
            LineSeat lineSeat = lineSeatService.findById(lineSeats.get(i).getLineSeatKey()).orElseThrow(() -> new RuntimeException("Seat of line not found"));
            lineSeat.setTicket(ticket);
            lineSeat.set_available(false);
            lineSeatService.save(lineSeat);
            ticket.getLineSeats().add(lineSeat);
            ticket = ticketService.save(ticket);

        }

        // PickUp
        for (int i = 0; i < pickUps.size(); i++) {
            PickUp pickUp = pickUpService.findById(pickUps.get(i).getId()).orElseThrow(() -> new RuntimeException("PickUp not found"));
            pickUp.getTickets().add(ticket);
            ticket.getPickUps().add(pickUp);
            pickUpService.save(pickUps.get(i));
        }

        // DropOff
        for (int i = 0; i < dropOffs.size(); i++) {
            DropOff dropOff = dropOffService.findById(dropOffs.get(i).getId()).orElseThrow(() -> new RuntimeException("DropOff not found"));
            dropOff.getTickets().add(ticket);
            ticket.getDropOffs().add(dropOff);
            dropOffService.save(dropOff);
        }

        // Lines
        for (int i = 0; i < lines.size(); i++) {
            Line line = lineService.findById(lines.get(i).getId()).orElseThrow(() -> new RuntimeException("Line not found"));
            line.getTickets().add(ticket);
            ticket.getLines().add(line);
            lineService.save(line);
        }

        ticket = ticketService.save(ticket);

        TicketModel newTicketModel = modelMapper.map(ticket, TicketModel.class);
        return ResponseEntity.ok(ResponseModel.builder()
                .error(false)
                .status(200)
                .message("Successfully book a ticket")
                .data(newTicketModel)
                .build());
    }
}
