package com.onlineticketingsystem.ticketingsystem.controller;

import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import org.springframework.web.bind.annotation.*;

import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemRunner;
import java.util.Queue;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/ticket-pool")
public class TicketPoolController {

    private final TicketPoolService ticketPoolService;
    private final TicketingSystemRunner ticketingSystemRunner;

    public TicketPoolController(TicketPoolService ticketPoolService , TicketingSystemRunner ticketingSystemRunner) {
        this.ticketPoolService = ticketPoolService;
        this.ticketingSystemRunner = ticketingSystemRunner;
    }

    @PostMapping("/start")
    public String startSystem() {
        return ticketingSystemRunner.startSystem();
    }

    @PostMapping("/stop")
    public String stopSystem() {
        return ticketingSystemRunner.stopSystem();
    }

    @PostMapping("/add-ticket")
    public void addTicket(@RequestBody Ticket ticket , int vendorID) {
        ticketPoolService.addTicket(ticket,vendorID);
    }

    @GetMapping("/buy-ticket")
    public Ticket buyTicket() {
        return ticketPoolService.buyTicket();
    }

    @GetMapping("/show-all-tickets")
    public Queue<Ticket> getAllTickets() {
        return ticketPoolService.getAllTickets();
    }
}
