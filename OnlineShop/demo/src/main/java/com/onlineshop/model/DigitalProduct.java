package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

 public class DigitalProduct extends Product{
    public DigitalProduct(String name, String description, int quantityAvailable, double price) {
        super(name, description, quantityAvailable, price);
    }

    @Override
    public String getProductType() {
        return "DIGITAL";
    }
}