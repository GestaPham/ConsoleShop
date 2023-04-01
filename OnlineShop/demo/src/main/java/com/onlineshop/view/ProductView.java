package com.onlineshop.view;

/**
 * @author Pham Hoang Long - S3938007
 */

import com.onlineshop.controller.ProductController;
import java.util.HashMap;

import com.onlineshop.model.DigitalProduct;
import com.onlineshop.model.PhysicalProduct;
import com.onlineshop.model.Product;

public class ProductView {
    private final UserInputView userInput;
    private final ProductController productController;
    
    public ProductView(ProductController productController){
        userInput = new UserInputView();
        this.productController = productController;
    }

    public void displayAllProducts(){
        HashMap<String, Product> productList = productController.getAllProduct();
        if (productList.isEmpty()){
            System.out.println("There is no products available in the shop!");
        }else{
            for(String key : productList.keySet()){
                System.out.println(productList.get(key).toString());
            }
        }
        System.out.println();
    }

    public void displayProductDetail(String name){
        Product product = productController.getProduct(name);
        if (product == null){
            System.out.println("Product not found!");
        }else{
            if (product instanceof DigitalProduct){
                System.out.println(product.toString()+" | Description: "+
                    product.getDescription()+" | Quantity: "+product.getQuantityAvailable()+" | Price: "+product.getPrice());
            }else{
                System.out.println(product.toString()+" | Description: "+
                    product.getDescription()+" | Quantity: "+product.getQuantityAvailable()+" | Price: "+product.getPrice()+
                        " | Weight: "+((PhysicalProduct) product).getWeight());
            }
            System.out.println();
        }
    }

    public void viewProduct(){
        String name = userInput.getUserInputString("Enter product name: ");
        Product product = productController.getProduct(name);
        if (product == null){
            ConsoleView.clearConsole();
            System.out.println("Product not found!");
            System.out.println();
        }else{
            ConsoleView.clearConsole();
            System.out.println("Product detail: ");
            if (product instanceof DigitalProduct){
                System.out.println(product.toString()+" | Description: "+
                    product.getDescription()+" | Quantity: "+product.getQuantityAvailable()+" | Price: "+product.getPrice());
            }else{
                System.out.println(product.toString()+" | Description: "+
                    product.getDescription()+" | Quantity: "+product.getQuantityAvailable()+" | Price: "+product.getPrice()+
                        " | Weight: "+((PhysicalProduct) product).getWeight());
            }
            System.out.println();
        }
    }

    public void createProduct(){
        boolean isDigital =  userInput.getUserInputYesOrNo("Is the product a Digital Product? (Y/N): ");
        String name = userInput.getUserInputString("Enter product name: ");
        String description = userInput.getUserInputString("Enter product description: ");
        int quantity = userInput.getUserInputInt("Enter product quantity: ");
        double price = userInput.getUserInputDouble("Enter product price: $");
        boolean created;
        if (isDigital){
            created = productController.createDigitalProduct(name, description, quantity, price);
        }else{
            double weight = userInput.getUserInputDouble("Enter product weight: ");
            created = productController.createPhysicalProduct(name, description, quantity, price, weight);
        }
        if (created){
            ConsoleView.clearConsole();
            System.out.println("Product created!");
            System.out.println();
        }else{
            ConsoleView.clearConsole();
            System.out.println("Fail to create product, the product may have already existed!");
            System.out.println();
        }
    }

    public void editProduct(){
        String name = userInput.getUserInputString("Enter product name: ");
        Product product = productController.getProduct(name);

        if(product == null){
            ConsoleView.clearConsole();
            System.out.println("Product not found!");
            System.out.println();
        }else{
            displayProductDetail(name);
            boolean isDigital = (product instanceof DigitalProduct);

            String description = userInput.getUserInputString("Edit product description: ");
            int quantity = userInput.getUserInputInt("Edit product quantity: ");
            double price = userInput.getUserInputDouble("Edit product price: $");
            if (isDigital){
                boolean edited = productController.editDigitalProduct(name, description, quantity, price);
                if (edited){
                    ConsoleView.clearConsole();
                    System.out.println("Product edited!");
                    System.out.println();
                }else{
                    ConsoleView.clearConsole();
                    System.out.println("Failed to edit product!");
                    System.out.println();
                }
            }else{
                double weight = userInput.getUserInputDouble("Edit product weight: ");
                boolean edited = productController.editPhysicalProduct(name, description, quantity, price, weight);
                if (edited){
                    ConsoleView.clearConsole();
                    System.out.println("Product edited!");
                    System.out.println();
                }else{
                    ConsoleView.clearConsole();
                    System.out.println("Failed to edit product!");
                    System.out.println();
                }
            }
        }
    }

    public void deleteProduct(){
        String name = userInput.getUserInputString("Enter product name: ");
        boolean deleted = productController.deleteProduct(name);
        if (deleted){
            ConsoleView.clearConsole();
            System.out.println("Product deleted!");
            System.out.println();
        }else{
            ConsoleView.clearConsole();
            System.out.println("Product not exist or already deleted!");
            System.out.println();
        }
    }
}
