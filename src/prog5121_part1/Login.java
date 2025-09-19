/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121_part1;
import java.util.Scanner;
/**
 *
 * @author RC_Student_lab
 */
public class Login {
    
    private String storedUsername;
    private String storedPassword;
    private String firstName;
    private String lastName;
    
    // Constructor
    public Login(String username, String password, String firstName, String lastName) {
        this.storedUsername = username;
        this.storedPassword = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    /**
     * Method to verify login credentials
     * @return true if login successful, false otherwise
     */
    public boolean loginUser() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Please enter your username:");
        String enteredUsername = scanner.nextLine();
        
        System.out.println("Please enter your password:");
        String enteredPassword = scanner.nextLine();
        
        // Verify if credentials match stored values
        return enteredUsername.equals(storedUsername) && enteredPassword.equals(storedPassword);
    }
    
    /**
     * Method to return login status message
     * @return appropriate login message based on authentication result
     */
    public String returnLoginStatus(boolean loginSuccess) {
        if (loginSuccess) {
            return "Welcome " + firstName + "." + lastName + " It is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
    /**
     * Method to check username format
     * @param username the username to check
     * @return true if username contains underscore and is no more than 5 characters
     */
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }
    
    /**
     * Method to check password complexity
     * @param password the password to check
     * @return true if password meets complexity requirements
     */
    public boolean checkPasswordComplexity(String password) {
        boolean hasMinLength = password.length() >= 8;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*()_\\-].*");
        
        return hasMinLength && hasUppercase && hasDigit && hasSpecialChar;
    }
    
    /**
     * Method to check cellphone number format
     * @param cellphoneNumber the phone number to check
     * @return true if phone number has correct format with country code
     */
    public boolean checkCellPhoneNumber(String cellphoneNumber) {
        // Check if starts with +27 (South Africa country code) and has correct length
        return cellphoneNumber.startsWith("+27") && cellphoneNumber.length() == 12;
    }
    
    /**
     * Method to register user and return appropriate messages
     * @param username the username to register
     * @param password the password to register
     * @return registration status message
     */
    public String registerUser(String username, String password) {
        if (!checkUserName(username)) {
            return "Username is incorrectly formatted. Please ensure it contains an underscore and is no more than 5 characters long.";
        }
        
        if (!checkPasswordComplexity(password)) {
            return "Password does not meet complexity requirements. Please ensure it has at least 8 characters, a capital letter, a number, and a special character.";
        }
        
        return "User has been registered successfully.";
    }
    
    // Getters for stored user data
    public String getStoredUsername() {
        return storedUsername;
    }
    
    public String getStoredPassword() {
        return storedPassword;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
}
    

