package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

public class DigitalGiftProduct extends GiftProduct {
    public DigitalGiftProduct(String name, String description, int quantityAvailable, double price, String message) {
        super(name, description, quantityAvailable, price, message);
    }

    @Override
    public String getProductType() {
        return "DIGITAL";
    }
}