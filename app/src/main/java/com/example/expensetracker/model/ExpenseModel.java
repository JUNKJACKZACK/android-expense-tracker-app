package com.example.expensetracker.model;

import java.math.BigDecimal;

public class ExpenseModel {
    private int id;
    private String name;
    private BigDecimal cost;
    private String location;
    private String category;

    // Constructor
    public ExpenseModel(int id, String name, BigDecimal cost, String location, String category) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.location = location;
        this.category = category;
    }

    // Getter and Setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
