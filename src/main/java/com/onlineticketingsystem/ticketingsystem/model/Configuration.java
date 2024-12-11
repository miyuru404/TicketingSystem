package com.onlineticketingsystem.ticketingsystem.model;

import lombok.*;
import org.springframework.stereotype.Service;


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
    @Override
    public String toString() {
        return "Configuration{" +
                "totalTicketAmount=" + totalTicketAmount +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }

}
