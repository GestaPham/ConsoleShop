package com.onlineshop.view;

/**
 * @author Pham Hoang Long - S3938007
 */
    
import java.util.Scanner;

public class UserInputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getUserChoice() {
        System.out.print("Enter your choice: ");
        return scanner.nextInt();
    }

    public String getUserInputString(String promt){
        System.out.print(promt);
        return scanner.nextLine();
    }

    public double getUserInputDouble(String prompt) {
        double input;
        System.out.print(prompt);
        while (true) {
            if (!scanner.hasNextDouble()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            } else {
                input = scanner.nextDouble();
                if (input < 0) {
                    System.out.print("Invalid input. Please enter a non-negative number: ");
                } else {
                    break;
                }
            }
        }
        return input;
    }

    public int getUserInputInt(String prompt) {
        int input;
        System.out.print(prompt);
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.print("Invalid input. Please enter a number: ");
                scanner.next();
            } else {
                input = scanner.nextInt();
                if (input < 0) {
                    System.out.print("Invalid input. Please enter a non-negative number: ");
                } else {
                    break;
                }
            }
        }
        return input;
    }

    public boolean getUserInputYesOrNo(String prompt){
        String input;
        System.out.print(prompt);
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                return true;
            } else if (input.equalsIgnoreCase("N")) {
                return false;
            } else {
                System.out.println("Invalid input. Please enter Y or N.");
            }
        }
    }
}