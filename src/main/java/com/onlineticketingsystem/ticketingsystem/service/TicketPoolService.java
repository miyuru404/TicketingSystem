package com.onlineticketingsystem.ticketingsystem.service;

import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

import java.util.Queue;

@Service
@Getter
public class TicketPoolService {
    private final Queue<Ticket> ticketQueue;
    private int maximumTicketCapacity;
    private final ConfigurationService config;
    private int ticketCounter = 0;
    private static final Logger logger = LoggerFactory.getLogger(TicketPoolService.class);


    public TicketPoolService(ConfigurationService config ) {

        this.ticketQueue = new LinkedList<Ticket>();
        this.config = config;

    }

    public synchronized void addTicket(Ticket ticket , int vendorID) {
        this.maximumTicketCapacity = config.getConfig().getMaxTicketCapacity();
        while (getTicketQueue().size() >= getMaximumTicketCapacity()) {
            try {
                wait();
                logger.warn("Ticket pool is full. Wait before adding new tickets...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        ticketQueue.add(ticket);
        //System.out.println("Vendor " + vendorID + " added ticket: " + ticket+ "Tote available tickets= "+ getTicketQueue().size());
        ticketCounter++;
        notifyAll();

    }

    public synchronized Ticket buyTicket() {
        while (getTicketQueue().isEmpty()) {
            try {
                wait();
                logger.warn("Ticket pool is empty.Wait before buy another ticket...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        ticketCounter--;
        notifyAll();
        return ticketQueue.poll();

    }

    public Queue<Ticket> getAllTickets() {
        return getTicketQueue();
    }
}
