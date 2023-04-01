package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

import java.util.HashMap;

public class ProductService {
    private HashMap<String, Product> productList;
    
    public ProductService(){
        this.productList = new HashMap<>();
    }

    public boolean addProduct(Product product) {
        if (productList.containsKey(product.getName())) {
            return false;
        }
        productList.put(product.getName(), product);
        return true;
    }

    public boolean removeProduct(String productName) {
        if (productList.containsKey(productName)){
            productList.remove(productName);
            return true;
        }
        
        return false;
    }

    public Product getProduct(String productName) {
        return productList.get(productName);
    }

    public HashMap<String, Product> getAllProduct(){
        return this.productList;
    }
}