package com.onlineticketingsystem.ticketingsystem;

import com.onlineticketingsystem.ticketingsystem.Thread.Customer;
import com.onlineticketingsystem.ticketingsystem.Service.TicketPoolService;
import com.onlineticketingsystem.ticketingsystem.CLI.TicketingSystemConfigurationControl;
import com.onlineticketingsystem.ticketingsystem.Thread.Vendor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TicketingSystemApplication {

    public static void main(String[] args) {
        // Start Spring Boot application and get the application context
        ApplicationContext context = SpringApplication.run(TicketingSystemApplication.class, args);


        // Get TicketPoolService bean from Spring context
        TicketPoolService ticketPoolService = context.getBean(TicketPoolService.class);
        TicketingSystemConfigurationControl customerRetrievalRate= context.getBean(TicketingSystemConfigurationControl.class);
        TicketingSystemConfigurationControl ticketReleaseRate=context.getBean(TicketingSystemConfigurationControl.class);

        //int customerRetrievalRate = maxticketCapacity.getMaxTicketCapacity();


        // Start vendor and customer threads
        Vendor vendor1 = new Vendor(ticketPoolService,ticketReleaseRate);
        Vendor vendor2 = new Vendor(ticketPoolService,ticketReleaseRate);
        //Vendor vendor3 = new Vendor(ticketPoolService,ticketReleaseRate);
        // vendor4 = new Vendor(ticketPoolService,ticketReleaseRate);

        Customer customer1 = new Customer(ticketPoolService, customerRetrievalRate);
        Customer customer2 = new Customer(ticketPoolService, customerRetrievalRate);

        vendor1.start();
        vendor2.start();
        //vendor3.start();
        //vendor4.start();
        customer1.start();
        customer2.start();
        //new Thread(customer1).start();
        //new Thread(customer2).start();
    }
}