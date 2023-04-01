package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

public class PhysicalGiftProduct extends GiftProduct {
    private double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public PhysicalGiftProduct(String name, String description, int quantityAvailable, double price, double weight, String message) {
        super(name, description, quantityAvailable, price, message);
        this.weight = weight;
    }

    @Override
    public String getProductType() {
        return "PHYSICAL";
    }
}