package com.ahuggins.message_queue_demo.services;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ahuggins.message_queue_demo.dtos.PaymentDto;

@Service
public class PaymentService {
    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Exchange exchange;

    @Value("${routing-key}")
    private String routingKey;
    
    public void processPayment(String username, double amount){
        template.convertAndSend(exchange.getName(), routingKey, new PaymentDto(username, amount));
    }
}
