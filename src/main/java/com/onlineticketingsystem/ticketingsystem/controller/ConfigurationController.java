package com.onlineticketingsystem.ticketingsystem.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private int totalTicketAmount;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    @PostMapping("/setconfiguration")
    public ResponseEntity<String> setConfiguration(@RequestBody ConfigurationRequest configurationRequest) {
        try {
            this.totalTicketAmount = configurationRequest.getField1();
            this.ticketReleaseRate = configurationRequest.getField2();
            this.customerRetrievalRate = configurationRequest.getField3();
            this.maxTicketCapacity = configurationRequest.getField4();

            System.out.printf("Configuration updated successfully:%nTotal Ticket Amount: %d%nTicket Release Rate: %d%nCustomer Retrieval Rate: %d%nMax Ticket Capacity: %d%n",
                    this.totalTicketAmount, this.ticketReleaseRate, this.customerRetrievalRate, this.maxTicketCapacity);

            return ResponseEntity.ok("Configuration updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Failed to update configuration.");
        }
    }

    static class ConfigurationRequest {
        private int field1;
        private int field2;
        private int field3;
        private int field4;

        public int getField1() { return field1; }
        public void setField1(int field1) { this.field1 = field1; }
        public int getField2() { return field2; }
        public void setField2(int field2) { this.field2 = field2; }
        public int getField3() { return field3; }
        public void setField3(int field3) { this.field3 = field3; }
        public int getField4() { return field4; }
        public void setField4(int field4) { this.field4 = field4; }
    }
}
