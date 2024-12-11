package com.onlineticketingsystem.ticketingsystem.model;

import lombok.Data;

@Data //set getesr and setars and to string method
public class Ticket  {
    //@JsonProperty
    private int ticketId;
    //@JsonProperty
    private String eventID;
    //@JsonProperty
    private int ticketPrice;

    public Ticket(int ticketId, String eventID, int ticketPrice) {
        this.ticketId = ticketId;
        this.eventID = eventID;
        this.ticketPrice = ticketPrice;
    }
}