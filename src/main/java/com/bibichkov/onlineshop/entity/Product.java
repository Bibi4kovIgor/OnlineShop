package com.bibichkov.onlineshop.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {

    public Product(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    private Long id;
    private String name;
    private double price;


}
