package com.onlineticketingsystem.ticketingsystem.service;

import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemConfiguration;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.thread.Vendor;
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

    private int ticketCounter = 0;


    public TicketPoolService( TicketingSystemConfiguration config) {
        this.ticketQueue = new LinkedList<Ticket>();
        this.config = config;
        this.maximumTicketCapacity = config.getMaxTicketCapacity();
    }


    public synchronized void addTicket(Ticket ticket , int vendorID) {
        while (getTicketQueue().size() >= getMaximumTicketCapacity()) {
            try {
                wait();
                System.out.println("Ticket pool is full.Wait before add a new tickets...");
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        ticketQueue.add(ticket);
        System.out.println("Vendor " + vendorID + " added ticket: " + ticket+ "Tote available tickets= "+ getTicketQueue().size());
        ticketCounter++;
        notifyAll();

    }

    public synchronized Ticket buyTicket() {
        while (getTicketQueue().isEmpty()) {
            try {
                wait();
                System.out.println("Ticket pool is empty.Wait before buy another ticket...");
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
