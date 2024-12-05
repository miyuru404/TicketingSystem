package com.onlineticketingsystem.ticketingsystem.controller;

import com.onlineticketingsystem.ticketingsystem.Ticket.Ticket;
import com.onlineticketingsystem.ticketingsystem.TicketPool.TicketPoolService;
import org.springframework.web.bind.annotation.*;
import java.util.Queue;

@RestController
@RequestMapping("/api/ticket-pool")

public class TicketPoolController {
    private TicketPoolService ticketPool;

    public TicketPoolController(TicketPoolService ticketPool) {
        this.ticketPool = ticketPool;
    }
    @PostMapping("/add-ticket")
    public synchronized void addTicket(@RequestBody Ticket ticket) {
        while (ticketPool.getTicketQueue().size() >= ticketPool.getMaximumTicketCapacity()){
            try {
                System.out.println("wait");
                wait();

            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage()); // For Client-Server application

            }
        }
        this.ticketPool.addTicket(ticket);
        notifyAll(); // Notify all the waiting threads
        System.out.println("Ticket added by - " + Thread.currentThread().getName() + " - current size is - " + ticketPool.getTicketQueue().size());
    }
    @GetMapping("/buy-ticket")
    public synchronized Ticket buyTicket(){
        while (ticketPool.getTicketQueue().isEmpty()){
            try {
                wait(); // If queue is empty add Customer to waiting status
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        notifyAll();
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + ticketPool.getTicketQueue().size() + " - Ticket is - " + ticketPool.getTicketQueue().peek());
        return ticketPool.getTicket();

    }
    @GetMapping("/show-all-tickets")
    public Queue<Ticket> getAllTickets() {
        return ticketPool.getTicketQueue();
    }
}

