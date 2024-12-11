package com.onlineticketingsystem.ticketingsystem.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Scanner;

@Service
@Setter
public class TicketingSystemConfiguration {

    @Getter
    private static int totalTickets;
    @Getter
    private static int ticketReleaseRate;
    @Getter
    private static int customerRetrievalRate;
    @Getter
    private static int maxTicketCapacity;

    public TicketingSystemConfiguration() {
        Scanner scanner = new Scanner(System.in);

        totalTickets = getInput(scanner, "Enter Total Tickets: ");
        ticketReleaseRate = getInput(scanner, "Enter Ticket Release Rate (ms): ");
        customerRetrievalRate = getInput(scanner, "Enter Customer Retrieval Rate (ms): ");
        maxTicketCapacity = getInput(scanner, "Enter Maximum Ticket Capacity: ");
        printConfiguration();
        saveConfigurationToJson();

    }

    private int getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next(); // Clear invalid input

        }
        return scanner.nextInt();
    }

    private void printConfiguration() {
        System.out.println("\nTicketing System Configuration:");
        System.out.println("--------------------------------");
        System.out.println("Total Tickets: " + TicketingSystemConfiguration.getTotalTickets());
        System.out.println("Ticket Release Rate: " + TicketingSystemConfiguration.getTicketReleaseRate());
        System.out.println("Customer Retrieval Rate: " + TicketingSystemConfiguration.getCustomerRetrievalRate());
        System.out.println();
    }

    public void saveConfigurationToJson() {
        // Create a Gson instance
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        TicketingSystemConfig config = new TicketingSystemConfig(totalTickets,ticketReleaseRate,customerRetrievalRate,maxTicketCapacity);
        // Serialize the TicketingSystemConfiguration object directly
        String jsonConfig = gson.toJson(config);  //
        // Define the JSON file path
        File configFile = new File("config.json");

        try {
            if (!configFile.exists()) {
                // If the file does not exist, create a new one
                configFile.createNewFile();
                System.out.println("New configuration file created: config.json");
            }
            try (FileWriter writer = new FileWriter(configFile)) {
                writer.write(jsonConfig);  // Write the JSON string to the file
                System.out.println("Configuration saved to config.json");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static class TicketingSystemConfig {
        private final int totalTickets;
        private final int ticketReleaseRate;
        private final int customerRetrievalRate;
        private final int maxTicketCapacity;

        // Constructor
        public TicketingSystemConfig(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
            this.totalTickets = totalTickets;
            this.ticketReleaseRate = ticketReleaseRate;
            this.customerRetrievalRate = customerRetrievalRate;
            this.maxTicketCapacity = maxTicketCapacity;
        }

        // Getters can be added if needed
    }

}
