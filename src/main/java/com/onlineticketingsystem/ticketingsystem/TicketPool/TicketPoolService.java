package com.onlineticketingsystem.ticketingsystem.TicketPool;


import com.onlineticketingsystem.ticketingsystem.Ticket.Ticket;
import com.onlineticketingsystem.ticketingsystem.CLI.TicketingSystemConfigurationControl;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.Queue;

@Service
@Getter
public class TicketPoolService {
    private int maximumTicketCapacity;
    private Queue<Ticket> ticketQueue;


    public TicketPoolService(TicketingSystemConfigurationControl config) {
        maximumTicketCapacity = config.getMaxTicketCapacity();
        this.ticketQueue = new LinkedList<Ticket>();
    }
    public void addTicket(Ticket ticket) {
        ticketQueue.add(ticket);
    }
    public Ticket getTicket() {
        return ticketQueue.poll();
    }
}