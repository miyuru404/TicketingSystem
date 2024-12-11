package com.onlineticketingsystem.ticketingsystem.service;

import com.onlineticketingsystem.ticketingsystem.model.Configuration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import java.util.InputMismatchException;
import java.util.Scanner;

@Service
@Getter
@Setter
public class ConfigurationService {

    private Configuration config;


    // Constructor initializes Configuration object
    public ConfigurationService() {
        this.config = new Configuration();
    }

    // Main method to get user input and update configuration
    public Configuration getInput() {
        try (Scanner input = new Scanner(System.in)) {
            int config1 = getPositiveIntInput(input, "Enter total ticket amount: ");
            this.config.setTotalTicketAmount(config1);
            int config2 = getPositiveIntInput(input, "Enter ticket release rate: ");
            this.config.setTicketReleaseRate(config2);
            int config3 = getPositiveIntInput(input, "Enter customer retrieval rate: ");
            this.config.setCustomerRetrievalRate(config3);
            int config4 = getPositiveIntInput(input, "Enter maximum ticket capacity: ");
            this.config.setMaxTicketCapacity(config4);

            System.out.println("\nConfiguration updated successfully!");
            System.out.println(config);  // Print out the configuration object details

            return config;
        }
    }

    // Helper method to ensure the input is a positive integer
    private int getPositiveIntInput(Scanner input, String prompt) {
        int number;
        while (true) {
            try {
                System.out.print(prompt);
                number = input.nextInt();

                // Check if the number is positive
                if (number > 0) {
                    return number;  // Return the valid positive integer
                } else {
                    System.out.println("Error: Please enter a number greater than zero.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Invalid input. Please enter a positive integer.");
                input.next();  // Clear the invalid input from the scanner
            }
        }
    }

}
