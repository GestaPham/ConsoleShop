package com.onlineshop.controller;

/**
 * @author Pham Hoang Long - S3938007
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import com.onlineshop.model.DigitalGiftProduct;
import com.onlineshop.model.DigitalProduct;
import com.onlineshop.model.PhysicalGiftProduct;
import com.onlineshop.model.PhysicalProduct;
import com.onlineshop.model.Product;
import com.onlineshop.model.ProductService;
import com.onlineshop.model.ShoppingCart;
import com.onlineshop.model.ShoppingCartService;


public class ShoppingCartController {
    private final ProductService productService;
    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ProductService productService, ShoppingCartService shoppingCartService){
        this.productService = productService;
        this.shoppingCartService = shoppingCartService;
    }

    public boolean createCart(String cartName){
        return shoppingCartService.addShoppingCart(cartName);
    }

    public boolean addItemToCart(String cartName, String productName, boolean isGift, String message){
        Product product = productService.getProduct(productName);
        ShoppingCart cart = shoppingCartService.geShoppingCart(cartName);

        if (product == null){
            return false;
        }
        if (cart == null){
            return false;
        }

        boolean added = cart.addItem(productName);
        if (added){
            if (isGift){
                if (product instanceof DigitalProduct){
                    DigitalGiftProduct giftProduct = new DigitalGiftProduct(product.getName(),
                            product.getDescription(), product.getQuantityAvailable(), product.getPrice(), message);

                    cart.addGiftItem(productName, giftProduct);
                }
                else{
                    PhysicalGiftProduct giftProduct = new PhysicalGiftProduct(product.getName(), product.getDescription(), 
                        product.getQuantityAvailable(), product.getPrice(), ((PhysicalProduct)product).getWeight(), message);

                    cart.addGiftItem(productName, giftProduct);
                }
            }
        }else{
            return false;
        }
        return true;
    }

    public boolean removeItemFromCart(String cartName, String productName){
        Product product = productService.getProduct(productName);
        ShoppingCart cart = shoppingCartService.geShoppingCart(cartName);

        if (product == null){
            return false;
        }
        if (cart == null){
            return false;
        }

        boolean removed = cart.removeItem(productName);
        if (removed){
            if (cart.getGiftItem(productName) != null){
                cart.removeGiftItem(productName);
            }
        }else{
            return false;
        }
        return true;
    }

    public ShoppingCart getShoppingCart(String cartName){
        return shoppingCartService.geShoppingCart(cartName);
    }

    public ArrayList<ShoppingCart> getAllShoppingCartSortedByWeight(){
        ArrayList<ShoppingCart> sortedCart = new ArrayList<>();
        HashMap<String, ShoppingCart> myCart = shoppingCartService.getAllShoppingCart();
        for(ShoppingCart cart : myCart.values()){
            sortedCart.add(cart);
        }
        Collections.sort(sortedCart);
        return sortedCart;
    }

    public int getCartCount(){
        return shoppingCartService.getCartCount();
    }

    public HashSet<String> getProductList(String cartName){
        ShoppingCart cart = shoppingCartService.geShoppingCart(cartName);
        return cart.getItemsList();
    }
}