package com.onlineticketingsystem.ticketingsystem.controller;

import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import org.springframework.web.bind.annotation.*;
import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemRunner;


import java.util.Queue;

@RestController
@RequestMapping("/api/ticket-pool")
public class TicketPoolController {

    private final TicketPoolService ticketPoolService;
    private final TicketingSystemRunner ticketingSystemRunner;

    public TicketPoolController(TicketPoolService ticketPoolService , TicketingSystemRunner ticketingSystemRunner) {
        this.ticketPoolService = ticketPoolService;
        this.ticketingSystemRunner = ticketingSystemRunner;
    }

    /**
     * Start the system with vendor and customer threads
     */
    @PostMapping("/start")
    public String startSystem() {
        return ticketingSystemRunner.startSystem();
    }

    /**
     * Stop the system by interrupting all customer and vendor threads
     */
    @PostMapping("/stop")
    public String stopSystem() {
        return ticketingSystemRunner.stopSystem();
    }


    /**
     * Add ticket to the pool
     */
    @PostMapping("/add-ticket")
    public void addTicket(@RequestBody Ticket ticket) {
        ticketPoolService.addTicket(ticket);
    }

    /**
     * Buy ticket from the pool
     */
    @GetMapping("/buy-ticket")
    public Ticket buyTicket() {
        return ticketPoolService.buyTicket();
    }

    /**
     * Show all tickets in the pool
     */
    @GetMapping("/show-all-tickets")
    public Queue<Ticket> getAllTickets() {
        return ticketPoolService.getAllTickets();
    }
}
