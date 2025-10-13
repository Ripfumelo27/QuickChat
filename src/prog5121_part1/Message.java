/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121_part1;

/**
 *
 * @author RC_Student_Lab
 */


import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Message {
    private String messageID;
    private int messageNumber;
    private String recipient;
    private String messageContent;
    private String messageHash;
    private static int totalMessagesSent = 0;
    private static List<Message> sentMessages = new ArrayList<>();
    
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
    
    public boolean checkRecipientNumber(String recipient) {
        // Check if starts with + and has no more than 15 characters (international format)
        if (recipient == null || recipient.length() > 15 || !recipient.startsWith("+")) {
            return false;
        }
        
        // Check if the rest are digits
        String digits = recipient.substring(1);
        return digits.matches("\\d+");
    }
    
    public String generateMessageHash() {
        String[] words = messageContent.split("\\s+");
        String firstWord = words.length > 0 ? words[0] : "";
        String lastWord = words.length > 1 ? words[words.length - 1] : firstWord;
        
        return "MSG#" + messageNumber + "#" + firstWord + "#" + lastWord;
    }
    
    public boolean validateMessageContent(String message) {
        return message != null && message.length() <= 250;
    }
    
    public String sendMessageOption(int option) {
        switch (option) {
            case 1: // Send Message
                totalMessagesSent++;
                sentMessages.add(this);
                storeMessageInJSON();
                return "Message successfully sent.";
                
            case 2: // Discard Message
                return "Message discarded.";
                
            case 3: // Store Message
                storeMessageInJSON();
                return "Message successfully saved.";
                
            default:
                return "Invalid option selected.";
        }
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
            System.out.println("Error storing message: " + e.getMessage());
        }
    }
    
    public static List<Message> getSentMessages() {
        return new ArrayList<>(sentMessages);
    }
    
    public static int getTotalMessagesSent() {
        return totalMessagesSent;
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
            "Message ID: %s\nMessage Hash: %s\nRecipient: %s\nMessage: %s",
            messageID, messageHash, recipient, messageContent
        );
    }
}