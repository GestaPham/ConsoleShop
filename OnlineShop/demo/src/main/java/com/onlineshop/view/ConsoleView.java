package com.onlineshop.view;

/**
 * @author Pham Hoang Long - S3938007
 */

import java.io.IOException;


public class ConsoleView {
    public static String currentCart;
    private final UserInputView userInput;
    private final ShoppingCartView shoppingCartView;
    private final ProductView productView;

    public ConsoleView(ShoppingCartView shoppingCartView, ProductView productView){
        
        userInput = new UserInputView();
        this.shoppingCartView = shoppingCartView;
        this.productView = productView;
        currentCart = null;
    }

    public static void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (IOException | InterruptedException ex) {}
    }


    public void displayMainMenu() {
        System.out.println("===== Shopping Service =====");
        productView.displayAllProducts();
        shoppingCartView.displayCurrentCart();
        System.out.println("1. Product");
        System.out.println("2. Shopping Cart");
        System.out.println("0. Exit");
    }

    private void displayProductMenu() {
        int choice;
        do {
            System.out.println("===== Product Service =====");
            System.out.println("Our product list:");
            productView.displayAllProducts();
            shoppingCartView.displayCurrentCart();
            System.out.println("1. Create new product");
            System.out.println("2. Edit product");
            System.out.println("3. Delete a product");
            System.out.println("4. View a product");
            System.out.println("0. Return");
            choice = userInput.getUserInputInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    productView.createProduct();
                    break;
                case 2:
                    productView.editProduct();
                    break;
                case 3:
                    productView.deleteProduct();
                    break;
                case 4:
                    productView.viewProduct();
                    break;
                case 0:
                    System.out.println("Returning to main menu.");
                    ConsoleView.clearConsole();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);
    }

    private void displayShoppingCartMenu() {
        int choice;
        do {
            System.out.println("===== Shopping Cart =====");
            System.out.println("Product list: ");
            productView.displayAllProducts();
            shoppingCartView.displayCurrentCart();
            System.out.println("1. Display all carts");
            System.out.println("2. Create new cart");
            System.out.println("3. Add item to current cart");
            System.out.println("4. Remove item from cart");
            System.out.println("5. Show current cart details");
            System.out.println("6. Switch current cart");
            System.out.println("0. Return");
            choice = userInput.getUserInputInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    shoppingCartView.displayAllCartsSortedByWeight();
                    break;
                case 2:
                    shoppingCartView.createCart();
                    break;
                case 3:
                    shoppingCartView.addItemToCurrentCart();
                    break;
                case 4:
                    shoppingCartView.removeItemFromCurrentCart(currentCart);
                    break;
                case 5:
                    shoppingCartView.displayCurrentCartDetails();
                    break;
                case 6:
                    shoppingCartView.setCurrentCart();
                    break;
                case 0:
                    System.out.println("Returning to main menu.");
                    ConsoleView.clearConsole();
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);
    }

    // application run
    public void run() {
        int choice;
        do {
            ConsoleView.clearConsole();
            displayMainMenu();
            choice = userInput.getUserInputInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    clearConsole();
                    displayProductMenu();
                    break;
                case 2:
                    clearConsole();
                    displayShoppingCartMenu();
                    break;
                case 0:
                    System.out.println("Exiting the shopping service.");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);
    }
}