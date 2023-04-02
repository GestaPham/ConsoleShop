package com.onlineshop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * @author Pham Hoang Long - S3938007
 */

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.onlineshop.controller.ProductController;
import com.onlineshop.model.DigitalGiftProduct;
import com.onlineshop.model.DigitalProduct;
import com.onlineshop.model.PhysicalProduct;
import com.onlineshop.model.ProductService;
import com.onlineshop.model.ShoppingCart;
import com.onlineshop.model.ShoppingCartService;

/**
 * Unit test for simple App.
 */
public class AppTest 
{   

    @Test
    public void testToString() {
        DigitalProduct game = new DigitalProduct("Game", "This is a digital game", 5, 100.00);
        PhysicalProduct iphone = new PhysicalProduct("Iphone", "This is an iphone", 3, 50.00, 10.0);
        assertEquals("DIGITAL - Game", game.toString());
        assertEquals("PHYSICAL - Iphone", iphone.toString());
    }

    @Test
    public void testAddItemToCart() {
        ProductService productService = new ProductService();
        ShoppingCart cart = new ShoppingCart("Cart 1", productService);
        DigitalProduct game = new DigitalProduct("Game", "This is a digital game", 5, 100.00);
        PhysicalProduct iphone = new PhysicalProduct("Iphone", "This is an iphone", 3, 50.00, 10.0);
        productService.addProduct(game);
        productService.addProduct(iphone);

        cart.addItem(game.getName());
        cart.addItem(iphone.getName());
        

        assertTrue(cart.getCartItems().contains(game.getName()));

        assertEquals(4, game.getQuantityAvailable());
        assertEquals(2, iphone.getQuantityAvailable());
    }

    @Test
    public void testRemoveItemFromCart() {
        ProductService productService = new ProductService();
        ShoppingCart cart = new ShoppingCart("Cart 1", productService);
        DigitalProduct game = new DigitalProduct("Game", "This is a game", 5, 100.00);
        PhysicalProduct iphone = new PhysicalProduct("Iphone", "This is an Iphone", 3, 50.00, 10.0);
        productService.addProduct(game);
        productService.addProduct(iphone);

        cart.addItem(game.getName());
        cart.addItem(iphone.getName());
        cart.removeItem(game.getName());
        
        assertFalse(cart.getCartItems().contains(game.getName()));
        assertTrue(cart.getCartItems().contains(iphone.getName()));

        assertEquals(5, game.getQuantityAvailable());
        assertEquals(2, iphone.getQuantityAvailable());
    }
    
    @Test
        public void testCartAmount() {
            ProductService productService = new ProductService();
            ShoppingCart cart = new ShoppingCart("Cart 1", productService);
            DigitalProduct game = new DigitalProduct("Game", "This is a game", 5, 100.00);
            PhysicalProduct iphone = new PhysicalProduct("Iphone", "This is an Iphone", 3, 50.00, 10.0);
            productService.addProduct(game);
            productService.addProduct(iphone);

            cart.addItem(game.getName());
            cart.addItem(iphone.getName());

            double expectedAmount = game.getPrice() + iphone.getPrice() + (iphone.getWeight() * 0.1);
            assertEquals(expectedAmount, cart.cartAmount(), 0.01);
    }

    @Test
    public void testGiftProduct() {
       DigitalGiftProduct game = new DigitalGiftProduct("game", "This is a game", 5, 100.00);
       String message = "Happy Birthday!";
       game.setMessage(message);
       assertEquals(message, game.getMessage());
    }

    @Test
    public void testRemoveGiftProduct(){
        ProductService productService = new ProductService();
        ShoppingCart cart = new ShoppingCart("Cart 1", productService);
        DigitalGiftProduct game = new DigitalGiftProduct("game", "This is a game", 5, 100.00);
        productService.addProduct(game);

        cart.addItem(game.getName());
        cart.addGiftItem(game.getName(), game);
        cart.removeItem(game.getName());

        assertEquals(null, cart.getGiftItem(game.getName()));
    }

    @Test
    public void testCompareTwoShoppingCart(){
        ProductService productService = new ProductService();
        ShoppingCart cart1 = new ShoppingCart("Cart 1", productService);
        ShoppingCart cart2 = new ShoppingCart("Cart 2", productService);
        PhysicalProduct iphone = new PhysicalProduct("Iphone", "This is an Iphone", 3, 1000.00, 10.0);
        PhysicalProduct android = new PhysicalProduct("Android", "This is an Android", 3, 500.00, 50.0);
        productService.addProduct(iphone);
        productService.addProduct(android);

        cart1.addItem("Iphone");
        cart2.addItem("Android");

        assertTrue(cart1.compareTo(cart2)<0);
    }

    @Test
    public void testDeleteProductWhileInCart(){
        ProductService productService = new ProductService();
        ShoppingCartService shoppingCartService = new ShoppingCartService(productService);
        ProductController productController = new ProductController(productService, shoppingCartService);
        ShoppingCart cart = new ShoppingCart("Cart 1", productService);
        DigitalGiftProduct game = new DigitalGiftProduct("game", "This is a game", 5, 100.00);

        productService.addProduct(game);
        shoppingCartService.addShoppingCart(cart.getName());

        cart.addItem(game.getName());
        productController.deleteProduct(cart.getName(), game.getName());

        assertFalse(shoppingCartService.getAllShoppingCart().containsKey("game"));
    }

    @Test
    public void testEditProduct(){
        ProductService productService = new ProductService();
        ShoppingCartService shoppingCartService = new ShoppingCartService(productService);
        ProductController productController = new ProductController(productService, shoppingCartService);
        ShoppingCart cart = new ShoppingCart("Cart 1", productService);
        DigitalGiftProduct game = new DigitalGiftProduct("game", "This is a game", 5, 100);
        productService.addProduct(game);
        shoppingCartService.addShoppingCart(cart.getName());

        cart.addItem(game.getName());
        assertEquals(100, cart.cartAmount(), 0.1);

        productController.editDigitalProduct(game.getName(), "This is not a game", 10, 1000);
        assertEquals(10, game.getQuantityAvailable());
        assertEquals(1000, cart.cartAmount(), 0.1);
    }
}
