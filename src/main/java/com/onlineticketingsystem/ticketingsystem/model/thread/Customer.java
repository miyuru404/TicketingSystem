package com.onlineticketingsystem.ticketingsystem.model.thread;


import com.onlineticketingsystem.ticketingsystem.service.ConfigurationService;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer extends Thread {


    private  int customerRetrievalRate;
    private  int totalTicketCanBuy;
    private int totalTicketCount = 0;

    private static final Logger logger = LoggerFactory.getLogger(Customer.class);
    private final TicketPoolService ticketPoolService;
    private final ConfigurationService config; // Dependency Injection


    // Constructor with dependency injected
    public Customer(TicketPoolService ticketPoolService ,ConfigurationService config) {
        this.ticketPoolService = ticketPoolService;
        this.config = config;

    }

    @Override
    public void run() {
        this.customerRetrievalRate = config.getConfig().getCustomerRetrievalRate(); // Get the value from the config
        this.totalTicketCanBuy = config.getConfig().getMaxTicketCapacity();
        try {
            while (totalTicketCount < totalTicketCanBuy) {
                // Attempt to buy a ticket from the shared pool.
                Ticket ticket = ticketPoolService.buyTicket();
                if (ticket != null) {
                    totalTicketCount++;//if attempt pass
                    logger.info("Customer bought ticket: {} ", ticket);

                } else {//if attempt fail.
                    logger.warn("No tickets available for Customer to buy.");

                }
                // Wait before attempting to buy the next ticket
                //used configuration value
                Thread.sleep(customerRetrievalRate);
            }
            logger.info("Customer has reached their ticket limit. Total tickets bought: {}", totalTicketCount);
        } catch (InterruptedException e) {
            logger.error("Customer thread interrupted.");
            Thread.currentThread().interrupt(); // Re-set the interrupted status
        }
    }
}
