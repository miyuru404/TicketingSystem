package com.onlineticketingsystem.ticketingsystem.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Configuration {

    private  int totalTicketAmount;
    private  int ticketReleaseRate;
    private  int customerRetrievalRate;
    private  int maxTicketCapacity ;

    public Configuration( int totalTicketAmount, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTicketAmount = totalTicketAmount;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }
    public void initializeConfiguration(int maximumTicketCapacity, int customerRetrievalRate, int ticketReleaseRate, int totalTickets) {
        setTicketReleaseRate(ticketReleaseRate);
        setCustomerRetrievalRate(customerRetrievalRate);
        setTicketReleaseRate(ticketReleaseRate);
        setTotalTicketAmount(totalTickets);
        //log.info("Configuration Details :\n MaximumTicketCapacity: {} \n,  CustomerRetrievalRate: {}\n, TicketReleaseRate: {}\n, TotalTickets {}\n",
                //getMaximumTicketCapacity(),getCustomerRetrievalRate(),getTicketReleaseRate(),getTotalTickets());

    }
}
