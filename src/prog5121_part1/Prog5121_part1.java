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
import java.util.Arrays;

public class Prog5121_part1 {
    private static List<Message> messages = new ArrayList<>();
    private static boolean isLoggedIn = false;
    private static int maxMessages = 0;
    private static int messagesSent = 0;
    
    // Arrays for Part 2 requirements
    private static ArrayList<String> sentMessages = new ArrayList<>();
    private static ArrayList<String> disregardedMessages = new ArrayList<>();
    private static ArrayList<String> sharedMessages = new ArrayList<>();
    private static ArrayList<String> messageBlank = new ArrayList<>();
    private static ArrayList<String> messageHeader = new ArrayList<>();
    private static ArrayList<String> messageID = new ArrayList<>();
    
    public static void main(String[] args) {
        // Populate with test data
        populateTestData();
        
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
    
    private static void populateTestData() {
        // Test Data Message 1
        sentMessages.add("Did you get the code?");
        messageID.add("MSG001");
        messageHeader.add("Sender: User, Recipient: +5743057080");
        
        // Test Data Message 2
        sentMessages.add("Where are you? You are late! I have asked you to be on time.");
        messageID.add("MSG002");
        messageHeader.add("Sender: User, Recipient: +27818886667");
        
        // Test Data Message 3
        disregardedMessages.add("In essence, I am at your door.");
        messageID.add("MSG003");
        messageHeader.add("Sender: User, Recipient: +27818886667");
        
        // Test Data Message 4
        sentMessages.add("It is driven time!");
        messageID.add("MSG004");
        messageHeader.add("Sender: User, Recipient: 0381834567");
        
        // Test Data Message 5
        sentMessages.add("No, I am leaving without you.");
        messageID.add("MSG005");
        messageHeader.add("Sender: User, Recipient: +27818886667");
        
        // Initialize shared messages and message blank
        sharedMessages.addAll(sentMessages);
        messageBlank.addAll(sharedMessages);
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
        String[] options = {"Send Messages", "Show Recent Messages", "Message Reports", "Logout"};
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
            case 2: // Message Reports
                showReportMenu();
                break;
            case 3: // Logout
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
    
    private static void showReportMenu() {
        String[] options = {
            "Display Senders and Recipients", 
            "Display Longest Message", 
            "Search Message by ID",
            "Search Messages by Recipient",
            "Delete Message by Hash",
            "Display Full Report",
            "Back to Main Menu"
        };
        
        int choice = JOptionPane.showOptionDialog(null,
            "Message Report Options:",
            "Message Reports",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        
        switch (choice) {
            case 0: // Display Senders and Recipients
                displaySendersAndRecipients();
                break;
            case 1: // Display Longest Message
                displayLongestMessage();
                break;
            case 2: // Search Message by ID
                searchMessageByID();
                break;
            case 3: // Search Messages by Recipient
                searchMessagesByRecipient();
                break;
            case 4: // Delete Message by Hash
                deleteMessageByHash();
                break;
            case 5: // Display Full Report
                displayFullReport();
                break;
            case 6: // Back to Main Menu
                // Do nothing, return to main menu
                break;
            default:
                // Do nothing
        }
    }
    
    // Part 2 Required Methods
    
    private static void displaySendersAndRecipients() {
        StringBuilder report = new StringBuilder();
        report.append("=== Senders and Recipients of All Sent Messages ===\n\n");
        
        for (int i = 0; i < messageHeader.size(); i++) {
            report.append("Message ID: ").append(messageID.get(i)).append("\n");
            report.append(messageHeader.get(i)).append("\n");
            report.append("------------------------\n");
        }
        
        JOptionPane.showMessageDialog(null,
            report.toString(),
            "Senders and Recipients Report",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void displayLongestMessage() {
        if (sentMessages.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                "No sent messages available.",
                "Longest Message",
                JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        String longestMessage = "";
        for (String message : sentMessages) {
            if (message.length() > longestMessage.length()) {
                longestMessage = message;
            }
        }
        
        JOptionPane.showMessageDialog(null,
            "Longest Sent Message:\n\n\"" + longestMessage + "\"\n\nLength: " + longestMessage.length() + " characters",
            "Longest Message",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    private static void searchMessageByID() {
        String searchID = JOptionPane.showInputDialog(null,
            "Enter Message ID to search for:",
            "Search Message by ID",
            JOptionPane.QUESTION_MESSAGE);
        
        if (searchID == null || searchID.trim().isEmpty()) {
            return;
        }
        
        StringBuilder result = new StringBuilder();
        boolean found = false;
        
        for (int i = 0; i < messageID.size(); i++) {
            if (messageID.get(i).equalsIgnoreCase(searchID.trim())) {
                result.append("Message Found:\n\n");
                result.append("Message ID: ").append(messageID.get(i)).append("\n");
                result.append("Header: ").append(messageHeader.get(i)).append("\n");
                if (i < sentMessages.size()) {
                    result.append("Message: ").append(sentMessages.get(i)).append("\n");
                }
                found = true;
                break;
            }
        }
        
        if (!found) {
            result.append("No message found with ID: ").append(searchID);
        }
        
        JOptionPane.showMessageDialog(null,
            result.toString(),
            "Message Search Result",
            found ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
    }
    
    private static void searchMessagesByRecipient() {
        String recipient = JOptionPane.showInputDialog(null,
            "Enter recipient phone number to search for:",
            "Search Messages by Recipient",
            JOptionPane.QUESTION_MESSAGE);
        
        if (recipient == null || recipient.trim().isEmpty()) {
            return;
        }
        
        StringBuilder result = new StringBuilder();
        result.append("Messages sent to: ").append(recipient).append("\n\n");
        boolean found = false;
        
        for (int i = 0; i < messageHeader.size(); i++) {
            if (messageHeader.get(i).contains(recipient.trim())) {
                result.append("Message ID: ").append(messageID.get(i)).append("\n");
                result.append("Header: ").append(messageHeader.get(i)).append("\n");
                if (i < sentMessages.size()) {
                    result.append("Message: ").append(sentMessages.get(i)).append("\n");
                }
                result.append("------------------------\n");
                found = true;
            }
        }
        
        if (!found) {
            result.append("No messages found for recipient: ").append(recipient);
        }
        
        JOptionPane.showMessageDialog(null,
            result.toString(),
            "Recipient Search Result",
            found ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.WARNING_MESSAGE);
    }
    
    private static void deleteMessageByHash() {
        // For this implementation, we'll use message ID as hash for simplicity
        String messageHash = JOptionPane.showInputDialog(null,
            "Enter Message ID to delete:",
            "Delete Message",
            JOptionPane.QUESTION_MESSAGE);
        
        if (messageHash == null || messageHash.trim().isEmpty()) {
            return;
        }
        
        boolean found = false;
        for (int i = 0; i < messageID.size(); i++) {
            if (messageID.get(i).equalsIgnoreCase(messageHash.trim())) {
                // Remove from all arrays
                if (i < sentMessages.size()) sentMessages.remove(i);
                if (i < disregardedMessages.size()) disregardedMessages.remove(i);
                if (i < sharedMessages.size()) sharedMessages.remove(i);
                if (i < messageBlank.size()) messageBlank.remove(i);
                if (i < messageHeader.size()) messageHeader.remove(i);
                messageID.remove(i);
                
                found = true;
                break;
            }
        }
        
        if (found) {
            JOptionPane.showMessageDialog(null,
                "Message with ID '" + messageHash + "' has been successfully deleted.",
                "Message Deleted",
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null,
                "No message found with ID: " + messageHash,
                "Delete Failed",
                JOptionPane.WARNING_MESSAGE);
        }
    }
    
    private static void displayFullReport() {
        StringBuilder report = new StringBuilder();
        report.append("=== FULL MESSAGE REPORT ===\n\n");
        
        report.append("SENT MESSAGES:\n");
        report.append("Total: ").append(sentMessages.size()).append("\n");
        for (int i = 0; i < sentMessages.size(); i++) {
            report.append(i + 1).append(". ").append(sentMessages.get(i)).append("\n");
        }
        
        report.append("\nDISREGARDED MESSAGES:\n");
        report.append("Total: ").append(disregardedMessages.size()).append("\n");
        for (int i = 0; i < disregardedMessages.size(); i++) {
            report.append(i + 1).append(". ").append(disregardedMessages.get(i)).append("\n");
        }
        
        report.append("\nMESSAGE IDs:\n");
        for (int i = 0; i < messageID.size(); i++) {
            report.append("ID: ").append(messageID.get(i)).append(" - ").append(messageHeader.get(i)).append("\n");
        }
        
        report.append("\nARRAY STATISTICS:\n");
        report.append("Sent Messages Array Size: ").append(sentMessages.size()).append("\n");
        report.append("Disregarded Messages Array Size: ").append(disregardedMessages.size()).append("\n");
        report.append("Shared Messages Array Size: ").append(sharedMessages.size()).append("\n");
        report.append("Message Blank Array Size: ").append(messageBlank.size()).append("\n");
        report.append("Message Header Array Size: ").append(messageHeader.size()).append("\n");
        report.append("Message ID Array Size: ").append(messageID.size()).append("\n");
        
        JOptionPane.showMessageDialog(null,
            report.toString(),
            "Full Message Report",
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    // Rest of your existing methods (registerUser, loginUser, sendMessages, etc.)
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
                    
                    // Add to arrays
                    sentMessages.add(messageContent);
                    messageID.add(message.getMessageID());
                    messageHeader.add("Sender: User, Recipient: " + recipient);
                    sharedMessages.add(messageContent);
                    messageBlank.add(messageContent);
                    
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
                        // Add to disregarded messages
                        disregardedMessages.add(messageContent);
                        messageID.add(message.getMessageID());
                        messageHeader.add("Sender: User, Recipient: " + recipient);
                        
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
                    
                    // Add to arrays
                    sentMessages.add(messageContent);
                    messageID.add(message.getMessageID());
                    messageHeader.add("Sender: User, Recipient: " + recipient);
                    sharedMessages.add(messageContent);
                    messageBlank.add(messageContent);
                    
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