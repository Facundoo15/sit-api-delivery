package com.example.demo.entitites;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class Order {

    private String id;
    private String customerName;
    private String customerEmail;
    private String status;
    private LocalTime creationTime;
    private LocalTime estimatedDeliveryTime;
    private List<Food> listFood;

    public Order(String id, String customerName, String customerEmail, List<Food> listFood) {
        this.id = id;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.status = "CONFIRMADO";
        this.listFood = listFood;
    }
}
