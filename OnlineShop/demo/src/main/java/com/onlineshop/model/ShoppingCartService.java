package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

import java.util.HashMap;

public class ShoppingCartService {
    private HashMap<String, ShoppingCart> shoppingCartList;
    private final ProductService productService;

    public ShoppingCartService(ProductService productService){
        this.shoppingCartList = new HashMap<>();
        this.productService = productService;
    }

    public boolean addShoppingCart(String cartName) {
        if (shoppingCartList.containsKey(cartName)) {
            return false;
        }

        shoppingCartList.put(cartName, new ShoppingCart(cartName, productService));
        return true;
    }

    public boolean removeShoppingCart(String cartName){
        if (shoppingCartList.containsKey(cartName)) {
            shoppingCartList.remove(cartName);
        }

        return false;
    }

    public ShoppingCart geShoppingCart(String cartName){
        return shoppingCartList.get(cartName);
    }

    public HashMap<String, ShoppingCart> getAllShoppingCart(){
        return this.shoppingCartList;
    }

    public int getCartCount(){
        return this.shoppingCartList.size();
    }
}