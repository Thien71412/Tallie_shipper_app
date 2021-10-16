package com.example.tallieshipper.models;

import java.io.Serializable;
import java.util.List;

public class OrderList implements Serializable {

    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
