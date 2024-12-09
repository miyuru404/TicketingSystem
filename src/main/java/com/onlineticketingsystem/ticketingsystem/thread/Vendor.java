package com.onlineticketingsystem.ticketingsystem.thread;


import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemConfiguration;
import com.onlineticketingsystem.ticketingsystem.model.Ticket;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;

public class Vendor extends Thread {
    private  int vendorID;
    private final TicketPoolService ticketPoolService;
    private  int ticketReleaseRate;

    private static int ticketId = 1;

    public Vendor(int vendorID, TicketPoolService ticketPoolService , TicketingSystemConfiguration config) {
        this.vendorID=vendorID;
        this.ticketPoolService = ticketPoolService;
        this.ticketReleaseRate=config.getTicketReleaseRate();
    }

    @Override
    public void run() {
        try {

            while (true) {
                //int ticketId;
                String eventID =  "V"+vendorID+"EVT"+vendorID;
                int ticketPrice = 1000 ;
                Ticket ticket = new Ticket(ticketId, eventID, ticketPrice);
                ticketPoolService.addTicket(ticket);
                System.out.println("Vendor " + vendorID + " added ticket: " + ticket);
                ticketId++;
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor thread interrupted.");
        }
    }
}