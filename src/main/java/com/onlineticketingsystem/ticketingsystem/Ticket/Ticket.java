package com.onlineticketingsystem.ticketingsystem.Ticket;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data //set getesr and setars and to string method
public class Ticket  {
    @JsonProperty//json data to java data
    private int ticketId;
    @JsonProperty
    private String eventName;
    @JsonProperty
    private int ticketPrice;

    public Ticket(int ticketId, String eventName, int ticketPrice) {
        this.ticketId = ticketId;
        this.eventName = eventName;
        this.ticketPrice = ticketPrice;
    }
}