/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121_part1;

/**
 *
 * @author RC_Student_Lab
 */



import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

public class Message {

    static List getSentMessages() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static int getTotalMessagesSent() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageContent;
    private String messageHash;
    
    // Constructor
    public Message(int messageNumber, String recipient, String messageContent) {
        this.messageID = generateMessageID();
        this.messageNumber = messageNumber;
        this.recipient = recipient;
        this.messageContent = messageContent;
        this.messageHash = generateMessageHash();
    }
    
    public String generateMessageID() {
        return UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    public static boolean checkRecipientNumber(String recipient) {
        if (recipient == null || recipient.length() > 15 || recipient.length() < 10) {
            return false;
        }
        
        // Check if starts with + and the rest are digits
        if (recipient.startsWith("+")) {
            String digits = recipient.substring(1);
            return digits.matches("\\d+");
        }
        return false;
    }
    
    public String generateMessageHash() {
        if (messageContent == null || messageContent.trim().isEmpty()) {
            return "MSG#" + messageNumber + "##";
        }
        
        String[] words = messageContent.trim().split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        return "MSG#" + messageNumber + "#" + firstWord + "#" + lastWord;
    }
    
    public static boolean validateMessageContent(String message) {
        return message != null && message.length() <= 250;
    }
    
    public void storeMessageInJSON() {
        try {
            JSONObject messageJson = new JSONObject();
            messageJson.put("messageID", this.messageID);
            messageJson.put("messageNumber", this.messageNumber);
            messageJson.put("recipient", this.recipient);
            messageJson.put("messageContent", this.messageContent);
            messageJson.put("messageHash", this.messageHash);
            messageJson.put("timestamp", System.currentTimeMillis());
            
            // Read existing messages
            JSONArray messagesArray;
            if (Files.exists(Paths.get("messages.json"))) {
                String content = new String(Files.readAllBytes(Paths.get("messages.json")));
                messagesArray = new JSONArray(content);
            } else {
                messagesArray = new JSONArray();
            }
            
            // Add new message
            messagesArray.put(messageJson);
            
            // Write back to file
            try (FileWriter file = new FileWriter("messages.json")) {
                file.write(messagesArray.toString(4));
            }
            
        } catch (IOException e) {
            javax.swing.JOptionPane.showMessageDialog(null,
                "Error storing message: " + e.getMessage(),
                "Storage Error",
                javax.swing.JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Getters
    public String getMessageID() { return messageID; }
    public int getMessageNumber() { return messageNumber; }
    public String getRecipient() { return recipient; }
    public String getMessageContent() { return messageContent; }
    public String getMessageHash() { return messageHash; }
    
    @Override
    public String toString() {
        return String.format(
            "Message Number: %d\nMessage ID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s",
            messageNumber, messageID, messageHash, recipient, messageContent
        );
    }

    String sendMessageOption(int option) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}