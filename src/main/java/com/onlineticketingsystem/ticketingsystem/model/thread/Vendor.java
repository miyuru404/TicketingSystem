package com.onlineticketingsystem.ticketingsystem.model.thread;

import com.onlineticketingsystem.ticketingsystem.model.Configuration;
import com.onlineticketingsystem.ticketingsystem.service.ConfigurationService;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Getter
public class Vendor extends Thread {

    private final int vendorID;
    private final TicketPoolService ticketPoolService;
    private final ConfigurationService config; // Dependency Injection for config
    private static final Logger logger = LoggerFactory.getLogger(Vendor.class);
    private int ticketReleaseRate;
    private int totalTicketCanSell;
    private int totalTicketSold = 0;
    private int ticketId = 1; // Unique Ticket ID for this Vendor
    private final int ticketPrice = 1000; // Fixed ticket price

    /**
     * Vendor constructor with configuration and pool service
     */
    public Vendor(int vendorID, TicketPoolService ticketPoolService ,ConfigurationService config) {
        this.vendorID = vendorID;
        this.ticketPoolService = ticketPoolService;
        this.config = config;

    }

    @Override
    public void run() {
        this.ticketReleaseRate = config.getConfig().getTicketReleaseRate();
        this.totalTicketCanSell = config.getConfig().getTotalTicketAmount();
        try {
            while (totalTicketSold < totalTicketCanSell) {
                // Generate a unique Event ID (uses vendorID and ticketId)
                String eventID = "V" + vendorID + "EVT" + ticketId;

                // Create a new ticket with ID, event, and price
                Ticket ticket = new Ticket(ticketId, eventID, ticketPrice);

                // Add ticket to the shared ticket pool
                ticketPoolService.addTicket(ticket, vendorID);


                logger.info("Vendor {} added ticket: {}", vendorID, ticket);

                // Update the ticket counters
                ticketId++;
                totalTicketSold++;

                // Sleep before releasing the next ticket
                Thread.sleep(ticketReleaseRate);
            }

            logger.info("Vendor {} has reached the ticket limit. Total tickets sold: {}", vendorID, totalTicketSold);


        } catch (InterruptedException e) {
            logger.error("Vendor {} thread interrupted.", vendorID);
            Thread.currentThread().interrupt(); // Re-set interrupted status
        }
    }
}
