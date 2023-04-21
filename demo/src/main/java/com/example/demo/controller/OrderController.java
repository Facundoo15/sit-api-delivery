package com.example.demo.controller;

import com.example.demo.Service.OrderService;
import com.example.demo.entitites.Food;
import com.example.demo.entitites.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/menu")
    public ResponseEntity<List<Food>> getAllMenu(){
        return ResponseEntity.ok(orderService.getAllMenu());
    }

    @PostMapping("/menu")
    public ResponseEntity<Food> addFood(@RequestBody Food food){
        this.orderService.addFood(food);
        return new ResponseEntity<>(food, HttpStatus.CREATED);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> addOrder(@RequestBody Order order){
        this.orderService.addOrder(order);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    @GetMapping("/order/{id}")
    public ResponseEntity<Double> getPriceByOrder(@PathVariable String id){
        double price = this.orderService.calculateDeliveryPrice(id);
        return ResponseEntity.ok(price);
    }

    @PutMapping("/order/update/{id}")
    public ResponseEntity<Order> updateStatusOrder(@PathVariable String id){
        Order or = this.orderService.updateStatusOrder(id);
        return ResponseEntity.ok(or);
    }

    @GetMapping("/order/status/{id}")
    public ResponseEntity<String> getStatusOrder(@PathVariable String id){
        String response = this.orderService.getStatusOrder(id);
        return ResponseEntity.ok(response);
    }










}
