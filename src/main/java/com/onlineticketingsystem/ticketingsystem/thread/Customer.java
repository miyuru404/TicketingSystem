package com.onlineticketingsystem.ticketingsystem.thread;

import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemConfiguration;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;

public class Customer extends Thread {

    private final TicketPoolService ticketPoolService;
    private final TicketingSystemConfiguration config; // Dependency Injection
    private final int customerRetrievalRate;
    private final int totalTicketCanBuy;
    private int totalTicketCount = 0;

    // Constructor with injected TicketPoolService and TicketingSystemConfiguration
    public Customer(TicketPoolService ticketPoolService, TicketingSystemConfiguration config) {
        this.ticketPoolService = ticketPoolService;
        this.config = config;
        this.customerRetrievalRate = config.getCustomerRetrievalRate(); // Get the value from the config
        this.totalTicketCanBuy = config.getTotalTickets(); // Get the total tickets
    }

    @Override
    public void run() {
        try {
            while (totalTicketCount < totalTicketCanBuy) {
                // Attempt to buy a ticket from the shared pool
                Ticket ticket = ticketPoolService.buyTicket();
                if (ticket != null) {
                    totalTicketCount++;
                    System.out.println("Customer bought ticket: " + ticket + " | Total tickets bought: " + totalTicketCount);
                } else {
                    System.out.println("No tickets available for Customer to buy.");
                }

                // Wait before attempting to buy the next ticket
                Thread.sleep(customerRetrievalRate);
            }
            System.out.println("Customer has reached their ticket limit. Total tickets bought: " + totalTicketCount);
        } catch (InterruptedException e) {
            System.out.println("Customer thread interrupted.");
            Thread.currentThread().interrupt(); // Re-set the interrupted status
        }
    }
}
