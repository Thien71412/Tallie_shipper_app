package com.example.tallieshipper.models;

import java.io.Serializable;


public class Order implements Serializable {

    // /api/orders/<id>
    String id;

    // /api/orders/newly_created
    String _id;
    Integer userId;
    Integer productId;
    String createdAt;
    String recipientName;
    String recipientPhone;
    String deliverTo;
    String estimatedDateArrival;
    boolean hasTaken = false;
    boolean isDelivering = false;
    boolean isDelivered = false;

    public Order(String id, String createdAt) {
        this.id = id;
        this.createdAt = createdAt;
    }

    public Order(String _id, String createdAt, String estimatedDateArrival, Integer userId, Integer productId,
                 String recipientName, String recipientPhone, String deliverTo) {
        this._id = _id;
        this.createdAt = createdAt;
        this.productId = productId;
        this.estimatedDateArrival = estimatedDateArrival;
        this.userId = userId;
        this.recipientName = recipientName;
        this.recipientPhone = recipientPhone;
        this.deliverTo = deliverTo;

    }

    public Order(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public CharSequence getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getDeliverTo() {
        return deliverTo;
    }

    public void setDeliverTo(String deliverTo) {
        this.deliverTo = deliverTo;
    }

    public String getestimatedDateArrival() {
        return estimatedDateArrival;
    }

    public void setestimatedDateArrival(String estimatedDateArrival) {
        this.estimatedDateArrival = estimatedDateArrival;
    }

    public boolean isHasTaken() {
        return hasTaken;
    }

    public void setHasTaken(boolean hasTaken) {
        this.hasTaken = hasTaken;
    }

    public boolean isDelivering() {
        return isDelivering;
    }

    public void setDelivering(boolean delivering) {
        isDelivering = delivering;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }


}
