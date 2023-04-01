package com.onlineshop.view;

/**
 * @author Pham Hoang Long - S3938007
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import com.onlineshop.controller.ProductController;
import com.onlineshop.controller.ShoppingCartController;
import com.onlineshop.model.DigitalGiftProduct;
import com.onlineshop.model.PhysicalGiftProduct;
import com.onlineshop.model.PhysicalProduct;
import com.onlineshop.model.Product;
import com.onlineshop.model.ShoppingCart;

public class ShoppingCartView {
    private final UserInputView userInput;
    private final ShoppingCartController shoppingCartController;
    private final ProductController productController;
    private final ProductView productView;

    public ShoppingCartView(ShoppingCartController shoppingCartController, ProductController productController, ProductView productView){
        userInput = new UserInputView();
        this.shoppingCartController = shoppingCartController;
        this.productController = productController;
        this.productView = productView;
    }

    public void displayAllCartsSortedByWeight(){
        ConsoleView.clearConsole();
        ArrayList<ShoppingCart> shoppingCartList = shoppingCartController.getAllShoppingCartSortedByWeight();
        System.out.println("Your Shopping Carts: ");
        for (ShoppingCart cart : shoppingCartList){
            System.out.println(cart.getName()+" | "+"Total Weight: "+
                    cart.getTotalWeight()+" | "+"Cart Amount: "+cart.cartAmount()+" | Product Count: "+cart.getItemCount());
        }
        System.out.println();
    }

    public void createCart(){
        String name = "Cart "+(shoppingCartController.getCartCount()+1);
        boolean created = shoppingCartController.createCart(name);
        ConsoleView.currentCart = name;
        ConsoleView.clearConsole();
        if (created){
            System.out.println("Cart created!");
            System.out.println();
        }else{
            System.out.println("An error has occurred!");
            System.out.println();
        }
    }

    public void addItemToCurrentCart(){
        if (ConsoleView.currentCart == null){
            boolean yes = userInput.getUserInputYesOrNo("You dont have a cart, create one? (Y/N): ");
            if (yes){
                createCart();
            }
        }else{
            String prodcutName = userInput.getUserInputString("Enter product name: ");
            Boolean isGift = userInput.getUserInputYesOrNo("Are you using the product as a gift? (Y/N): ");
            String message;
            if (isGift){
                message = userInput.getUserInputString("Enter a message for the gift: ");
            }else{
                message = "";
            }
            boolean confirmAction = userInput.getUserInputYesOrNo("Confirm action (Y/N):");
            ConsoleView.clearConsole();
            if (confirmAction){
                boolean added = shoppingCartController.addItemToCart(ConsoleView.currentCart, prodcutName, isGift, message);
                if (added){
                    System.out.println("Item has been added to the current cart!");
                }else{
                    System.out.println("An error has occurred! Item either not exist or already in your current cart!");
                }
            }
        }
    }

    public void removeItemFromCurrentCart(String currentCart){
        if (currentCart == null){
            boolean yes = userInput.getUserInputYesOrNo("You dont have a cart, create one? (Y/N): ");
            if (yes){
                createCart();
            }
        }else{
            String prodcutName = userInput.getUserInputString("Enter product name that you want to remove: ");
            boolean confirmAction = userInput.getUserInputYesOrNo("Confirm action (Y/N): ");
            ConsoleView.clearConsole();
            if (confirmAction){
                boolean removed = shoppingCartController.removeItemFromCart(ConsoleView.currentCart, prodcutName);
                if(removed){
                    System.out.println("Item has been removed!");
                    System.out.println();
                }else{
                    System.out.println("An error has occurred! Item may already have been deleted!");
                    System.out.println();
                }
            }
        }
    }

    public void displayCurrentCartDetails(){
        ConsoleView.clearConsole();
        
        ShoppingCart cart = shoppingCartController.getShoppingCart(ConsoleView.currentCart);
        HashMap<String, Product> productList = productController.getAllProduct();
        HashSet<String> itemsList = cart.getItemsList();

        if (itemsList.isEmpty()){
            System.out.println("Your cart is empty!");
            System.out.println();
        }else{
            System.out.println("Your cart items: ");
        }
        for (String item : itemsList){
            Product product = productList.get(item);
            Product giftProduct = cart.getGiftItem(item);

            if (giftProduct == null){
                productView.displayProductDetail(item);
            }else{
                if (cart.getGiftItem(item) instanceof DigitalGiftProduct){
                    System.out.println(product.toString()+" (Gift)"+" | Description: "+
                        product.getDescription()+" | Price: "+product.getPrice()+
                        " | Gift message: "+((DigitalGiftProduct) giftProduct).getMessage());
                }else{
                    System.out.println(product.toString()+" | Description: "+
                        product.getDescription()+" (Gift)"+" | Price: "+product.getPrice()+
                            " | Weight: "+((PhysicalProduct) product).getWeight()+" | Gift message: "+((PhysicalGiftProduct) giftProduct).getMessage());
                }
            }
        }
    }

    public void displayCurrentCart(){
        if(ConsoleView.currentCart == null){
            System.out.println("Current cart: null");
            System.out.println();
        }else{
            ShoppingCart cart = shoppingCartController.getShoppingCart(ConsoleView.currentCart);
            System.out.println("Current cart: "+cart.getName()+" | "+"Total Weight: "+
                        cart.getTotalWeight()+" | "+"Cart Amount: "+cart.cartAmount()+" | Product Count: "+cart.getItemCount());
            System.out.println();
        }
    }

    public void setCurrentCart(){
        ConsoleView.clearConsole();
        this.displayAllCartsSortedByWeight();
        String cartName = userInput.getUserInputString("Enter cart name: ");
        ConsoleView.clearConsole();
        ShoppingCart cart = shoppingCartController.getShoppingCart(cartName);
        if (cart != null){
            ConsoleView.currentCart = cartName;
            System.out.println("Current cart is now "+cartName);
            System.out.println();
        }else{
            System.out.println("Cart does not exist!");
            System.out.println();
        }
    }
}