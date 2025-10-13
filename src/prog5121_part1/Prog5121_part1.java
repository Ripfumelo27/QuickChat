/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prog5121_part1;

/**
 *
 * @author RC_Student_lab
 */
    



import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Prog5121_part1 {
    private static List<Message> messages = new ArrayList<>();
    private static boolean isLoggedIn = false;
    private static int maxMessages = 0;
    private static int messagesSent = 0;
    
    public static void main(String[] args) {
        showWelcomeMessage();
        
        boolean exit = false;
        
        while (!exit) {
            if (!isLoggedIn) {
                showLoginMenu();
            } else {
                showMainMenu();
            }
        }
    }
    
    private static void showWelcomeMessage() {
        JOptionPane.showMessageDialog(null,
            "Welcome to QuickChat",
            "LinkedIn",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void showLoginMenu() {
        String[] options = {"Register", "Login", "Exit App"};
        int choice = JOptionPane.showOptionDialog(null,
            "Please choose an option:",
            "QuickChat - Login Menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        switch (choice) {
            case 0: // Register
                registerUser();
                break;
            case 1: // Login
                loginUser();
                break;
            case 2: // Exit
                JOptionPane.showMessageDialog(null,
                    "Thanks for using QuickChat. Goodbye!",
                    "Goodbye",
                    JOptionPane.INFORMATION_MESSAGE);
                System.exit(0);
                break;
            default:
                // User closed the dialog
                System.exit(0);
        }
    }
    
    private static void showMainMenu() {
        String[] options = {"Send Messages", "Show Recent Messages", "Logout"};
        int choice = JOptionPane.showOptionDialog(null,
            "QuickChat Messaging System\nWhat would you like to do?",
            "Main Menu",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        switch (choice) {
            case 0: // Send Messages
                sendMessages();
                break;
            case 1: // Show Recent Messages
                showRecentMessages();
                break;
            case 2: // Logout
                isLoggedIn = false;
                messages.clear();
                maxMessages = 0;
                messagesSent = 0;
                JOptionPane.showMessageDialog(null,
                    "Successfully logged out.",
                    "Logout",
                    JOptionPane.INFORMATION_MESSAGE);
                break;
            default:
                // Do nothing, will show menu again
        }
    }
    
    private static void registerUser() {
        Registration registration = new Registration();
        registration.userInput();
        JOptionPane.showMessageDialog(null,
            "Registration completed successfully!",
            "Registration Success",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void loginUser() {
        // For demo purposes, simple login
        String username = JOptionPane.showInputDialog(null,
            "Enter your username:",
            "Login",
            JOptionPane.QUESTION_MESSAGE);
            
        String password = JOptionPane.showInputDialog(null,
            "Enter your password:",
            "Login",
            JOptionPane.QUESTION_MESSAGE);
        
        if (username != null && password != null && 
            !username.isEmpty() && !password.isEmpty()) {
            isLoggedIn = true;
            JOptionPane.showMessageDialog(null,
                "Login successful! Welcome to QuickChat.",
                "Login Success",
                JOptionPane.INFORMATION_MESSAGE);
            
            setMaxMessagesWithJOption();
        } else {
            JOptionPane.showMessageDialog(null,
                "Login failed. Please try again.",
                "Login Failed",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private static void setMaxMessagesWithJOption() {
        String input = JOptionPane.showInputDialog(null,
            "How many messages would you like to send?",
            "Message Limit",
            JOptionPane.QUESTION_MESSAGE);
        
        try {
            if (input != null) {
                maxMessages = Integer.parseInt(input);
                JOptionPane.showMessageDialog(null,
                    "You can send up to " + maxMessages + " messages.",
                    "Message Limit Set",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                maxMessages = 5;
                JOptionPane.showMessageDialog(null,
                    "No input received. Setting default to 5 messages.",
                    "Default Set",
                    JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException e) {
            maxMessages = 5;
            JOptionPane.showMessageDialog(null,
                "Invalid number. Setting default to 5 messages.",
                "Error",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private static void sendMessages() {
        if (messagesSent >= maxMessages) {
            JOptionPane.showMessageDialog(null,
                "You have reached your message limit of " + maxMessages + " messages.",
                "Limit Reached",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        boolean continueSending = true;
        
        while (continueSending && messagesSent < maxMessages) {
            // Get recipient number
            String recipient = null;
            boolean validRecipient = false;
            
            while (!validRecipient) {
                recipient = JOptionPane.showInputDialog(null,
                    "Enter recipient phone number (international format, e.g., +27123456789):",
                    "Recipient Number",
                    JOptionPane.QUESTION_MESSAGE);
                
                if (recipient == null) {
                    // User cancelled
                    return;
                }
                
                if (Message.checkRecipientNumber(recipient)) {
                    validRecipient = true;
                    JOptionPane.showMessageDialog(null,
                        "Cell phone number successfully registered.",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Cell phone number is incorrectly formatted or does not contain international code.\nPlease correct the number and try again.",
                        "Invalid Number",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Get message content
            String messageContent = null;
            boolean validMessage = false;
            
            while (!validMessage) {
                messageContent = JOptionPane.showInputDialog(null,
                    "Enter your message (max 250 characters):",
                    "Message Content",
                    JOptionPane.QUESTION_MESSAGE);
                
                if (messageContent == null) {
                    // User cancelled
                    return;
                }
                
                if (Message.validateMessageContent(messageContent)) {
                    validMessage = true;
                    JOptionPane.showMessageDialog(null,
                        "Message ready to send.",
                        "Message Ready",
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,
                        "Message exceeds 250 characters, please reduce size.",
                        "Message Too Long",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Create message object
            int messageNumber = messagesSent + 1;
            Message message = new Message(messageNumber, recipient, messageContent);
            
            // Show send options
            String[] options = {"Send Message", "Disregard Message", "Store Message"};
            int option = JOptionPane.showOptionDialog(null,
                "Choose what to do with the message:",
                "Message Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
            
            switch (option) {
                case 0: // Send Message
                    messages.add(message);
                    messagesSent++;
                    String messageDetails = "Message Details:\n\n" + message.toString();
                    JOptionPane.showMessageDialog(null,
                        messageDetails,
                        "Message Sent Successfully",
                        JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                case 1: // Disregard Message
                    int deleteChoice = JOptionPane.showConfirmDialog(null,
                        "Press OK to delete message or Cancel to keep it.",
                        "Delete Message",
                        JOptionPane.OK_CANCEL_OPTION);
                    
                    if (deleteChoice == JOptionPane.OK_OPTION) {
                        JOptionPane.showMessageDialog(null,
                            "Message deleted successfully.",
                            "Message Deleted",
                            JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null,
                            "Message kept.",
                            "Message Kept",
                            JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                    
                case 2: // Store Message
                    messages.add(message);
                    messagesSent++;
                    JOptionPane.showMessageDialog(null,
                        "Message successfully stored.",
                        "Message Stored",
                        JOptionPane.INFORMATION_MESSAGE);
                    break;
                    
                default:
                    // User closed dialog, do nothing
            }
            
            // Check if limit reached
            if (messagesSent >= maxMessages) {
                JOptionPane.showMessageDialog(null,
                    "Message limit reached!\nTotal messages sent: " + messagesSent,
                    "Limit Reached",
                    JOptionPane.INFORMATION_MESSAGE);
                continueSending = false;
            } else {
                // Ask if user wants to send another message
                int continueChoice = JOptionPane.showConfirmDialog(null,
                    "You have " + (maxMessages - messagesSent) + " messages remaining.\nDo you want to send another message?",
                    "Continue Sending?",
                    JOptionPane.YES_NO_OPTION);
                
                continueSending = (continueChoice == JOptionPane.YES_OPTION);
            }
        }
    }
    
    private static void showRecentMessages() {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "No messages sent yet.",
                "Recent Messages",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            StringBuilder messageList = new StringBuilder();
            messageList.append("=== Recently Sent Messages ===\n\n");
            messageList.append("Total messages: ").append(messages.size()).append("\n\n");
            
            for (int i = 0; i < messages.size(); i++) {
                messageList.append("Message ").append(i + 1).append(":\n");
                messageList.append(messages.get(i).toString()).append("\n");
                messageList.append("------------------------\n\n");
            }
            
            JOptionPane.showMessageDialog(null,
                messageList.toString(),
                "Recent Messages",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
}