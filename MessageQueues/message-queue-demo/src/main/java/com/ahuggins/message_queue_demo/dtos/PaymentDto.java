package com.ahuggins.message_queue_demo.dtos;

public class PaymentDto {
    private String username;
    private double amount;
    public PaymentDto(){}
    public PaymentDto(String username, double amount){
        this.username = username;
        this.amount = amount;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
}
