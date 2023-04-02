package com.onlineshop.model;

/**
 * @author Pham Hoang Long - S3938007
 */

import java.util.HashMap;
import java.util.HashSet;

public class ShoppingCart implements Comparable<ShoppingCart> {
    private String cartName;
    private HashSet<String> myProducts;
    private HashMap<String, Product> giftItems;
    private ProductService productService;

    public ShoppingCart(String name, ProductService productService){
        this.cartName = name;
        this.myProducts = new HashSet<String>();
        this.giftItems = new HashMap<>();
        this.productService = productService;
    }

    public String getName(){
        return this.cartName;
    }

    public boolean addItem(String productName){
        Product product = productService.getProduct(productName);

        if (product == null){
            return false;
        }
        if (this.myProducts.contains(product.getName()) || product.getQuantityAvailable() <= 0){
            return false;
        }
        
        product.setQuantityAvailable(product.getQuantityAvailable()-1);;
        this.myProducts.add(product.getName());
        return true;
    }

    public boolean addGiftItem(String productName, Product product){
        giftItems.put(productName, product);
        return true;
    }

    public boolean removeItem(String productName){
        Product product = productService.getProduct(productName);

        if (product == null){
            return false;
        }
        if (this.myProducts.contains(productName)){
            myProducts.remove(productName);
            product.setQuantityAvailable(product.getQuantityAvailable()+1);
            this.removeGiftItem(productName);
            return true;
        }

        return false;
    }

    public double cartAmount(){
        return getTotalPrice()+getTotalWeight()*0.1;
    }

    public double getTotalWeight(){
        HashMap<String, Product> productList = productService.getAllProduct();
        double totalWeight = 0.0;
        for (String key : productList.keySet()){
            if(myProducts.contains(key)){
                Product product = productList.get(key);
                if (product instanceof PhysicalProduct){
                    totalWeight += ((PhysicalProduct) product).getWeight();
                }
            }
        }
        return totalWeight;
    }

    public double getTotalPrice(){
        HashMap<String, Product> productList = productService.getAllProduct();
        double totalPrice = 0;
        for (String key : productList.keySet()){
            if(myProducts.contains(key)){
                totalPrice += productList.get(key).getPrice();
            }
        }
        return totalPrice;
    }

    public HashSet<String> getCartItems(){
        return this.myProducts;
    }

    public int getItemCount(){
        return myProducts.size();
    }

    public Product getGiftItem(String productName){
        if (giftItems.containsKey(productName)){
            return giftItems.get(productName);
        }
        return null;
    }

    public void removeGiftItem(String productName){
        giftItems.remove(productName);
    }

    public HashSet<String> getItemsList(){
        return this.myProducts;
    }

    @Override
    public int compareTo(ShoppingCart o) {
        return Double.compare(this.getTotalWeight(), o.getTotalWeight());
    }
}