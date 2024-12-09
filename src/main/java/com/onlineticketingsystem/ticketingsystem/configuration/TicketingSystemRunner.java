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
    private final TicketingSystemConfiguration config;
    private boolean systemRunning = false;
    private final List<Thread> vendorThreads = new ArrayList<>();
    private final List<Thread> customerThreads = new ArrayList<>();

    public TicketingSystemRunner(TicketPoolService ticketPoolService, TicketingSystemConfiguration config) {
        this.ticketPoolService = ticketPoolService;
        this.config = config;
    }

    public synchronized String startSystem() {
        if (systemRunning) {
            return "System is already running!";
        }

        systemRunning = true;

        // Start Vendor Threads
        Vendor vendor1 = new Vendor(1, ticketPoolService, config);
        Vendor vendor2 = new Vendor(2, ticketPoolService, config);
        vendorThreads.add(vendor1);
        vendorThreads.add(vendor2);

        // Start Customer Threads
        Customer customer1 = new Customer(ticketPoolService, config);
        Customer customer2 = new Customer(ticketPoolService, config);
        customerThreads.add(customer1);
        customerThreads.add(customer2);

        vendor1.start();
        vendor2.start();
        customer1.start();
        customer2.start();

        return "System started with 2 vendors and 2 customers!";
    }

    public synchronized String stopSystem() {
        if (!systemRunning) {
            return "System is not running!";
        }

        // Stop all vendor threads
        for (Thread vendorThread : vendorThreads) {
            vendorThread.interrupt();
        }
        vendorThreads.clear();

        // Stop all customer threads
        for (Thread customerThread : customerThreads) {
            customerThread.interrupt();
        }
        customerThreads.clear();

        systemRunning = false;
        return "System stopped successfully!";
    }
}
