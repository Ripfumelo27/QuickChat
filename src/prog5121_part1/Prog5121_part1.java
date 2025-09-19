/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog5121_part1;

import java.util.Scanner;

/**
 *
 * @author RC_Student_lab
 */
public class Prog5121_part1 {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Registration registration = new Registration();
        Login userLogin = null;
        
        System.out.println("Hi there welcome to the LogIn App");
        System.out.println("=================================");
        
        boolean exit = false;
        
        while (!exit) {
            System.out.println("Please choose one of the options below:");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit App");
            System.out.print("Enter your choice (1-3): ");
            
            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    // Register new user
                    registration.userInput();
                    userLogin = registration.getUserLogin();
                    break;
                    
                case "2":
                    // Login existing user
                    if (userLogin == null) {
                        System.out.println("No user registered yet. Please register first.");
                    } else {
                        boolean loginSuccess = userLogin.loginUser();
                        String loginMessage = userLogin.returnLoginStatus(loginSuccess);
                        System.out.println(loginMessage);
                    }
                    break;
                    
                case "3":
                    // Exit application
                    System.out.println("Danko for using the Login App. uhambe kahle!");
                    exit = true;
                    break;
                    
                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                    break;
            }
        }
        
        scanner.close();
    }
}
        
    
    

