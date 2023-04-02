package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

 public abstract class GiftProduct extends Product {
    private String message;
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GiftProduct(String name, String description, int quantityAvailable, double price, String message) {
        super(name, description, quantityAvailable, price);
        this.message = message;
    }

    public GiftProduct(String name, String description, int quantityAvailable, double price) {
        super(name, description, quantityAvailable, price);
        this.message = " ";
    }
}