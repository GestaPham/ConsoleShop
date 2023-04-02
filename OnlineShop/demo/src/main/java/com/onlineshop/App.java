package com.onlineshop;

/**
 * @author Pham Hoang Long - S3938007
 */

 import com.onlineshop.view.*;
 import com.onlineshop.controller.*;
 import com.onlineshop.model.*;
 public class App {
     public static void main(String[] args) throws Exception {   
         ProductService productService = new ProductService();
         ShoppingCartService shoppingCartService = new ShoppingCartService(productService);
 
         ProductController productController = new ProductController(productService, shoppingCartService);
         ShoppingCartController shoppingCartController = new ShoppingCartController(productService, shoppingCartService);
 
         ProductView productView = new ProductView(productController);
         ShoppingCartView shoppingCartView = new ShoppingCartView(shoppingCartController, productController, productView);
 
         // Main application
         ConsoleView consoleView = new ConsoleView(shoppingCartView, productView);
         consoleView.run();
     }
 }
