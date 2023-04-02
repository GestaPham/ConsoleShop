package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

public class DigitalGiftProduct extends GiftProduct {
    public DigitalGiftProduct(String name, String description, int quantityAvailable, double price, String message) {
        super(name, description, quantityAvailable, price, message);
    }

    public DigitalGiftProduct(String name, String description, int quantityAvailable, double price) {
        super(name, description, quantityAvailable, price);
    }

    @Override
    public String getProductType() {
        return "DIGITAL";
    }
}