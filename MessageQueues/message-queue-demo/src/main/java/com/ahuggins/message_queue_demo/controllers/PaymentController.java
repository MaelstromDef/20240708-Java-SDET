package com.ahuggins.message_queue_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahuggins.message_queue_demo.services.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    PaymentService service;

    @GetMapping
    public void getPayments(@RequestParam String username, @RequestParam double amount){
        service.processPayment(username, amount);
    }
}
