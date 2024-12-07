package com.onlineticketingsystem.ticketingsystem.CLI;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@Getter
public class TicketingSystemConfigurationControl {

    private int totalTickets;
    private int ticketReleaseRate;
    protected int customerRetrievalRate;

    private  int maxTicketCapacity;

    public TicketingSystemConfigurationControl() {
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