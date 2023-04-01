package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

 public class PhysicalProduct extends Product{
    private double weight;

    public double getWeight() {
        return this.weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public PhysicalProduct(String name, String description, int quantityAvailable, double price, double weight){
        super(name, description, quantityAvailable, price);
        this.weight = weight;
    }

    @Override
    public String getProductType(){
        return "PHYSICAL";
    }
}