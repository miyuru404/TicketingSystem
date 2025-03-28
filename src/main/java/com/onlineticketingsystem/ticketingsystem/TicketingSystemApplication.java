package com.onlineticketingsystem.ticketingsystem;

import com.onlineticketingsystem.ticketingsystem.configuration.TicketingSystemRunner;
import com.onlineticketingsystem.ticketingsystem.service.ConfigurationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TicketingSystemApplication {

    public static void main(String[] args) {
        // Start Spring Boot application and get the application context
        ApplicationContext context = SpringApplication.run(TicketingSystemApplication.class, args);

        ConfigurationService configurationService = context.getBean(ConfigurationService.class);
        configurationService.getInput();

        TicketingSystemRunner runner = context.getBean(TicketingSystemRunner.class);
        runner.startSystem();








    }
}