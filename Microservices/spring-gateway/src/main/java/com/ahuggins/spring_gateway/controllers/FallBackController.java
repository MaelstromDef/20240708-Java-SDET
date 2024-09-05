package com.ahuggins.spring_gateway.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class FallBackController {
    @GetMapping("/cache/accounts")
    public String cachedAccounts() {
        return "Cached Accounts: Courtesy of CircuitBreaker.";
    }
    
    @GetMapping("/cache/payments")
    public String cachedPayments() {
        return "Cached Payments: Courtesy of CircuitBreaker.";
    }
}
