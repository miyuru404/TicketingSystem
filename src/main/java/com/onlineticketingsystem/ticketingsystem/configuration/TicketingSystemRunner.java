package com.onlineticketingsystem.ticketingsystem.configuration;

import com.onlineticketingsystem.ticketingsystem.service.TicketPoolService;
import com.onlineticketingsystem.ticketingsystem.thread.Customer;
import com.onlineticketingsystem.ticketingsystem.thread.Vendor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TicketingSystemRunner {

    private final TicketPoolService ticketPoolService;
    private final TicketingSystemConfiguration config; // Injected configuration
    private boolean systemRunning = false;
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();

    // Constructor injection for ticket pool and configuration
    public TicketingSystemRunner(TicketPoolService ticketPoolService, TicketingSystemConfiguration config) {
        this.ticketPoolService = ticketPoolService;
        this.config = config;

    }

    // Start the ticketing system
    public String startSystem() {
        synchronized (this) {
            if (systemRunning) {
                return "System is already running!";
            }

            systemRunning = true;

            // Start Vendor Threads
            for (int i = 1; i <= 2; i++) {
                Vendor vendor = new Vendor(i, ticketPoolService, config); // Pass config to Vendor
                vendor.setPriority(Thread.MAX_PRIORITY); // Set vendor thread priority
                vendorThreads.add(vendor);
                vendor.start();
            }

            // Start Customer Threads
            for (int i = 1; i <= 2; i++) {
                Customer customer = new Customer(ticketPoolService, config);
                customerThreads.add(customer);
                customer.start();
            }
        }

        return "System started with 2 vendors and 2 customers!";
    }

    // Stop the ticketing system
    public String stopSystem() {
        synchronized (this) {
            if (!systemRunning) {
                return "System is not running!";
            }

            // Stop all vendor threads
            for (Thread vendorThread : vendorThreads) {
                vendorThread.interrupt(); // Interrupt vendor thread
                try {
                    vendorThread.join(); // Wait for vendor thread to finish
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle interruption
                }
            }
            vendorThreads.clear(); // Clear vendor thread list

            // Stop all customer threads
            for (Thread customerThread : customerThreads) {
                customerThread.interrupt(); // Interrupt customer thread
                try {
                    customerThread.join(); // Wait for customer thread to finish
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Handle interruption
                }
            }
            customerThreads.clear(); // Clear customer thread list

            systemRunning = false; // Set system to not running
        }

        return "System stopped successfully!";
    }
}
