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
    


import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Prog5121_part1 {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Message> messages = new ArrayList<>();
    private static boolean isLoggedIn = false;
    private static int maxMessages = 0;
    
    public static void main(String[] args) {
        System.out.println("Welcome to LinkedIn");
        System.out.println("==================");
        
        boolean exit = false;
        
        while (!exit) {
            if (!isLoggedIn) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
        
        scanner.close();
    }
    
    private static void showLoginMenu() {
        System.out.println("\nPlease choose one of the options below:");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit App");
        System.out.print("Enter your choice (1-3): ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                registerUser();
                break;
            case "2":
                loginUser();
                break;
            case "3":
                System.out.println("Thanks for using LinkedIn. Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }
    
    private static void showMainMenu() {
        System.out.println("\n=== LinkedIn Messaging ===");
        System.out.println("1. Send Messages");
        System.out.println("2. Show recently sent messages");
        System.out.println("3. Logout");
        System.out.print("Enter your choice (1-3): ");
        
        String choice = scanner.nextLine();
        
        switch (choice) {
            case "1":
                sendMessages();
                break;
            case "2":
                showRecentMessages();
                break;
            case "3":
                isLoggedIn = false;
                messages.clear();
                maxMessages = 0;
                System.out.println("Successfully logged out.");
                break;
            default:
                System.out.println("Invalid choice. Please enter 1, 2, or 3.");
        }
    }
    
    private static void registerUser() {
        Registration registration = new Registration();
        registration.userInput();
        System.out.println("Registration completed successfully!");
    }
    
    private static void loginUser() {
        Registration registration = new Registration();
        // For demo purposes, we'll create a temporary user
        System.out.println("Please enter your username:");
        String username = scanner.nextLine();
        System.out.println("Please enter your password:");
        String password = scanner.nextLine();
        
        // Simple login validation (in real app, this would check against stored credentials)
        if (!username.isEmpty() && !password.isEmpty()) {
            isLoggedIn = true;
            System.out.println("Login successful! Welcome to QuickChat.");
            
            // Set maximum number of messages
            setMaxMessages();
        } else {
            System.out.println("Login failed. Please try again.");
        }
    }
    
    private static void setMaxMessages() {
        System.out.print("How many messages would you like to send? ");
        try {
            maxMessages = Integer.parseInt(scanner.nextLine());
            System.out.println("You can send up to " + maxMessages + " messages.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number. Setting default to 5 messages.");
            maxMessages = 5;
        }
    }
    
    private static void sendMessages() {
        if (messages.size() >= maxMessages) {
            System.out.println("You have reached your message limit of " + maxMessages + " messages.");
            return;
        }
        
        System.out.println("\n=== Send Message ===");
        
        // Get recipient number
        String recipient;
        do {
            System.out.print("Enter recipient phone number (international format, e.g., +27123456789): ");
            recipient = scanner.nextLine();
            
            Message tempMessage = new Message(0, recipient, "test");
            if (!tempMessage.checkRecipientNumber(recipient)) {
                System.out.println("Cell phone number is incorrectly formatted or does not contain international code. Please correct the number and try again.");
            }
        } while (!new Message(0, recipient, "test").checkRecipientNumber(recipient));
        
        System.out.println("Cell phone number successfully registered.");
        
        // Get message content
        String messageContent;
        do {
            System.out.print("Enter your message (max 250 characters): ");
            messageContent = scanner.nextLine();
            
            Message tempMessage = new Message(0, recipient, messageContent);
            if (!tempMessage.validateMessageContent(messageContent)) {
                System.out.println("Message exceeds 250 characters, please reduce size.");
            }
        } while (!new Message(0, recipient, messageContent).validateMessageContent(messageContent));
        
        System.out.println("Message ready to send.");
        
        // Create message object
        int messageNumber = messages.size() + 1;
        Message message = new Message(messageNumber, recipient, messageContent);
        
        // Show send options
        System.out.println("\nChoose an option:");
        System.out.println("1. Send Message");
        System.out.println("2. Discard Message");
        System.out.println("3. Store Message to send later");
        System.out.print("Enter your choice (1-3): ");
        
        
        
        try {
            int option = Integer.parseInt(scanner.nextLine());
            String result = message.sendMessageOption(option);
            System.out.println(result);
            
            if (option == 1) {
                messages.add(message);
                System.out.println("\n=== Message Details ===");
                System.out.println(message);
                System.out.println("=======================");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Invalid option selected.");
        }
        
        // Show total messages sent
        if (messages.size() >= maxMessages) {
            System.out.println("\n=== Summary ===");
            System.out.println("Total messages sent: " + Message.getTotalMessagesSent());
        }
    }
    
    private static void showRecentMessages() {
        System.out.println("\n=== Recently Sent Messages ===");
        List<Message> sentMessages = Message.getSentMessages();
        
        if (sentMessages.isEmpty()) {
            System.out.println("No messages sent yet.");
        } else {
            for (int i = 0; i < sentMessages.size(); i++) {
                System.out.println("\nMessage " + (i + 1) + ":");
                System.out.println(sentMessages.get(i));
                System.out.println("------------------------");
            }
        }
    }
}