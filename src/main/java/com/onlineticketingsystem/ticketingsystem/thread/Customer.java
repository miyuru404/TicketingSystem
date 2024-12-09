package com.onlineticketingsystem.ticketingsystem.thread;

import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemConfiguration;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;

public class Customer extends Thread {

    private final TicketPoolService ticketPoolService;//private final int customerRetrievalRate; ;
    private final int customerRetrievalRate;


    public Customer(TicketPoolService ticketPoolService, TicketingSystemConfiguration config) {
        this.ticketPoolService = ticketPoolService;
        this.customerRetrievalRate=config.getCustomerRetrievalRate();

    }

    @Override
    public void run() {
        try {
            while (true) {
                Ticket ticket = ticketPoolService.buyTicket(); // Get ticket from the pool
                if (ticket != null) {
                    System.out.println("Customer bought ticket: " + ticket);
                } else {
                    System.out.println("No tickets available. Waiting...");
                }
                Thread.sleep(customerRetrievalRate); // Simulate time taken to process purchase
            }
        } catch (InterruptedException e) {
            System.out.println("Customer thread interrupted.");
        }
    }
}