package com.onlineticketingsystem.ticketingsystem.model.thread;

import com.onlineticketingsystem.ticketingsystem.service.ConfigurationService;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Getter
public class Vendor extends Thread {

    private final int vendorID;

    private int ticketReleaseRate;
    private int totalTicketCanSell;//total ticket amount can sell.
    private int totalTicketSold = 0;//counter for count total tickets.
    private int ticketId = 1;//flag for generate ticket ID
    private final int ticketPrice = 1000;

    private final TicketPoolService ticketPoolService; // Dependency Injection
    private final ConfigurationService config; // Dependency Injection

    private static final Logger logger = LoggerFactory.getLogger(Vendor.class);


    public Vendor(int vendorID, TicketPoolService ticketPoolService ,ConfigurationService config) {
        this.vendorID = vendorID;
        this.ticketPoolService = ticketPoolService;
        this.config = config;

    }

    @Override
    public void run() {
        this.ticketReleaseRate = config.getConfig().getTicketReleaseRate();//get configuration value from dependency injection.
        this.totalTicketCanSell = config.getConfig().getTotalTicketAmount();//get configuration value from dependency injection.
        try {
            while (totalTicketSold < totalTicketCanSell) {// check whether vendor reach the maxsize ticket it can sell.
                // Generate a unique Event ID (uses vendorID and ticketId)
                String eventID = "V" + vendorID + "EVT" + ticketId;
                Ticket ticket = new Ticket(ticketId, eventID, ticketPrice);
                // Add ticket to the shared ticket pool
                ticketPoolService.addTicket(ticket, vendorID);

                //use logger.
                logger.info("Vendor {} added ticket: {}", vendorID, ticket);

                ticketId++;
                totalTicketSold++;
                // Sleep before releasing the next ticket
                //use ticketReleaseRate form configurations
                Thread.sleep(ticketReleaseRate);
            }
            logger.info("Vendor {} has reached the ticket limit. Total tickets sold:  {}", vendorID, totalTicketSold);

        } catch (InterruptedException e) {
            logger.error("Vendor {} thread interrupted.", vendorID);
            Thread.currentThread().interrupt(); // Re-set interrupted status
        }
    }
}
