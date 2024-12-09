package com.onlineticketingsystem.ticketingsystem.service;

import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemConfiguration;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

import java.util.Queue;

@Service
@Getter
public class TicketPoolService {
    private final Queue<Ticket> ticketQueue;
    private final int maximumTicketCapacity;
    private final TicketingSystemConfiguration config;


    public TicketPoolService( TicketingSystemConfiguration config) {
        this.ticketQueue = new LinkedList<Ticket>();
        this.config = config;
        this.maximumTicketCapacity = config.getMaxTicketCapacity();
    }


    public synchronized void addTicket(Ticket ticket) {
        while (getTicketQueue().size() >= getMaximumTicketCapacity()) {
            try {
                System.out.println("Waiting to add ticket...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        ticketQueue.add(ticket);
        notifyAll();
        System.out.println("Ticket added by - " + Thread.currentThread().getName() + " - current size is - " + getTicketQueue().size());
    }

    public synchronized Ticket buyTicket() {
        while (getTicketQueue().isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        notifyAll();
        System.out.println("Ticket bought by - " + Thread.currentThread().getName() + " - current size is - " + getTicketQueue().size());
        return ticketQueue.poll();
    }

    public Queue<Ticket> getAllTickets() {
        return getTicketQueue();
    }
}
