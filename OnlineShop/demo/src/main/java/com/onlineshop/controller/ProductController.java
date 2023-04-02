package com.onlineshop.controller;


/**
 * @author Pham Hoang Long - S3938007
 */

import java.util.HashMap;

import com.onlineshop.model.DigitalProduct;
import com.onlineshop.model.PhysicalProduct;
import com.onlineshop.model.Product;
import com.onlineshop.model.ProductService;
import com.onlineshop.model.ShoppingCartService;

public class ProductController {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ProductController(ProductService productService, ShoppingCartService shoppingCartService){
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    public boolean createDigitalProduct(String name, String description, int quantity, double price){
        return productService.addProduct(new DigitalProduct(name, description, quantity, price));
    }

    public boolean createPhysicalProduct(String name, String description, int quantity, double price, double weight){
        return productService.addProduct(new PhysicalProduct(name, description, quantity, price, weight));
    }

    public boolean deleteProduct(String cartName, String productName){
        Product product = getProduct(productName);
        if (product == null){
            return false;
        }

        shoppingCartService.removeItemFromShoppingCart(cartName, productName);
        productService.removeProduct(productName);
        product = null;
        return true;
    }

    public boolean editDigitalProduct(String name, String description, int quantity, double price){
        Product product = getProduct(name);
        if (product == null){
            return false;
        }

        product.setDescription(description);
        product.setPrice(price);
        product.setQuantityAvailable(quantity);

        return true;
    }

    public boolean editPhysicalProduct(String name, String description, int quantity, double price, double weight){
        Product product = productService.getProduct(name);
        if (product == null){
            return false;
        }

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantityAvailable(quantity);
        ((PhysicalProduct) product).setWeight(weight);

        return true;
    }

    public Product getProduct(String productName){
        return productService.getProduct(productName);
    }

    public HashMap<String, Product> getAllProduct(){
        return productService.getAllProduct();
    }
}