package com.onlineticketingsystem.ticketingsystem.thread;

import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemConfiguration;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import lombok.Getter;

@Getter
public class Vendor extends Thread {

    private final int vendorID;
    private final TicketPoolService ticketPoolService;
    private final TicketingSystemConfiguration config; // Dependency Injection for config

    private final int ticketReleaseRate;
    private final int totalTicketCanSell;
    private int totalTicketSold = 0;
    private int ticketId = 1; // Unique Ticket ID for this Vendor
    private final int ticketPrice = 1000; // Fixed ticket price

    /**
     * Vendor constructor with configuration and pool service
     */
    public Vendor(int vendorID, TicketPoolService ticketPoolService, TicketingSystemConfiguration config) {
        this.vendorID = vendorID;
        this.ticketPoolService = ticketPoolService;
        this.config = config;
        this.ticketReleaseRate = config.getTicketReleaseRate();
        this.totalTicketCanSell = config.getTotalTickets();
    }

    @Override
    public void run() {
        try {
            while (totalTicketSold < totalTicketCanSell) {
                // Generate a unique Event ID (uses vendorID and ticketId)
                String eventID = "V" + vendorID + "EVT" + ticketId;

                // Create a new ticket with ID, event, and price
                Ticket ticket = new Ticket(ticketId, eventID, ticketPrice);

                // Add ticket to the shared ticket pool
                ticketPoolService.addTicket(ticket, vendorID);

                // Print ticket details for debugging (optional)
                System.out.println("Vendor " + vendorID + " added ticket: " + ticket);

                // Update the ticket counters
                ticketId++;
                totalTicketSold++;

                // Sleep before releasing the next ticket
                Thread.sleep(ticketReleaseRate);
            }

            System.out.println("Vendor " + vendorID + " has reached the ticket limit. Total tickets sold: " + totalTicketSold);

        } catch (InterruptedException e) {
            System.out.println("Vendor " + vendorID + " thread interrupted.");
            Thread.currentThread().interrupt(); // Re-set interrupted status
        }
    }
}
