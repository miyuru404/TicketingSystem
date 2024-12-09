package com.onlineticketingsystem.ticketingsystem.configuration;

import lombok.Getter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Scanner;


@Service
@Getter
public class TicketingSystemConfiguration {

    private final int totalTickets;
    private final int ticketReleaseRate;
    protected final int customerRetrievalRate;
    private final int maxTicketCapacity;

    public TicketingSystemConfiguration() {
        Scanner scanner = new Scanner(System.in);

        this.totalTickets = getInput(scanner, "Enter Total Tickets: ");
        this.ticketReleaseRate = getInput(scanner, "Enter Ticket Release Rate (ms): ");
        this.customerRetrievalRate = getInput(scanner, "Enter Customer Retrieval Rate (ms): ");
        this.maxTicketCapacity = getInput(scanner, "Enter Maximum Ticket Capacity: ");
    }

    private int getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

}