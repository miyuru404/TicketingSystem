package com.onlineticketingsystem.ticketingsystem.Thread;


import com.onlineticketingsystem.ticketingsystem.CLI.TicketingSystemConfigurationControl;
import com.onlineticketingsystem.ticketingsystem.Ticket.Ticket;
import com.onlineticketingsystem.ticketingsystem.TicketPool.TicketPoolService;

public class Vendor extends Thread {
    private final TicketPoolService ticketPoolService;
    private final int ticketReleaseRate;

    public Vendor(TicketPoolService ticketPoolService , TicketingSystemConfigurationControl config) {
        this.ticketPoolService = ticketPoolService;
        this.ticketReleaseRate=config.getTicketReleaseRate();
    }

    @Override
    public void run() {
        try {
            int ticketId = 1; //
            while (true) {
                String eventName = "Event-" + ticketId;
                int ticketPrice = 100 + ticketId * 10;
                Ticket ticket = new Ticket(ticketId, eventName, ticketPrice);
                ticketPoolService.addTicket(ticket);
                System.out.println("Vendor added ticket: " + ticket);
                ticketId++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor thread interrupted.");
        }
    }
}
