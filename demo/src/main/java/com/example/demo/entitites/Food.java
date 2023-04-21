package com.example.demo.entitites;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Food {

    private String name;
    private String description;
    private double price;
    
}
