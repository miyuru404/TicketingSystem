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

}
