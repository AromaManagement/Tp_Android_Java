package com.example.internalmemory;

import java.io.Serializable;

public class Product implements Serializable {
    private int id;
    private String name;
    private float price;

    public Product(int id, String name, float price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | " + name + " - $" + price;
    }
}