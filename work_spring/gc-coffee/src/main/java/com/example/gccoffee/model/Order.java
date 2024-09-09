package com.example.gccoffee.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private final UUID orderId;
    private final Email email;
    private String address;
    private String postcode;
    private final List<OrderItem> orderItems;
    private OrderState orderState;
    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Order(UUID orderId, Email email, LocalDateTime createdAt, String address, String postcode, List<OrderItem> orderItems, OrderState orderState, LocalDateTime updatedAt) {
        this.orderId = orderId;
        this.email = email;
        this.createdAt = createdAt;
        this.address = address;
        this.postcode = postcode;
        this.orderItems = orderItems;
        this.orderState = orderState;
        this.updatedAt = updatedAt;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Email getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getPostcode() {
        return postcode;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setAddress(String address) {
        this.address = address;
        this.updatedAt = LocalDateTime.now();
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        this.updatedAt = LocalDateTime.now();
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
        this.updatedAt = LocalDateTime.now();
    }
}
