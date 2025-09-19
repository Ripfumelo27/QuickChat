/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prog5121_part1;
import java.util.Scanner;

public class Registration {
    private Login userLogin;
    
    public void userInput(){
        String name;
        String surname;
        String username;
        String password;
        String cellphone_number;
        
        Scanner scanner = new Scanner(System.in);
        
        // Prompt user for details
        System.out.println("Please enter your name:");
        name = scanner.nextLine();
        
        System.out.println("Please enter your surname:");
        surname = scanner.nextLine();
        
        // Prompt user to enter username 
        do {
            System.out.println("Please enter your username:");
            username = scanner.nextLine();
        } while (!checkUsername(username));
        
        // Prompt user to enter password 
        do {
            System.out.println("Please enter your password:");
            password = scanner.nextLine();
        } while (!checkPassword(password));
        
        // Prompt user to enter cellphone_number 
        do {
            System.out.println("Please enter your cellphone number (format: +27xxxxxxxxx):");
            cellphone_number = scanner.nextLine();
        } while (!checkCellPhoneNumber(cellphone_number));
        
        // Create Login instance with user data
        userLogin = new Login(username, password, name, surname);
        
        // Test registration message
        String registrationMessage = userLogin.registerUser(username, password);
        System.out.println(registrationMessage);
        
        // Test login functionality
        boolean loginSuccess = userLogin.loginUser();
        String loginMessage = userLogin.returnLoginStatus(loginSuccess);
        System.out.println(loginMessage);
    }
    
    public boolean checkUsername(String username){
        // Username must have an underscore(_) and be no more than five characters
        if(username.contains("_") && username.length() <= 5){
            System.out.println("Username successfully captured");
            return true;
        } else {
            System.out.println("Username is not correctly formatted, please ensure your username contains an underscore and is no more than five characters in length.");
            return false;
        }
    }
    
    public boolean checkPassword(String password){
        boolean hasMinLength = password.length() >= 8;
        
        // Check if password has uppercase (fixed regex)
        boolean hasUppercase = !password.equals(password.toLowerCase());
       
        // Check if password contains a number
        boolean hasDigit = password.matches(".*\\d.*");
        
        // Check if password has special character
        boolean hasSpecialCharacters = password.matches(".*[!@#$%^&*()_\\-].*");
        
        if(hasMinLength && hasUppercase && hasDigit && hasSpecialCharacters){
            System.out.println("Password successfully captured");
            return true;
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.");
            return false;
        }
    }
    
    public boolean checkCellPhoneNumber(String cellphone_number) {
        // Check if starts with +27 and has exactly 12 characters total
        if (cellphone_number.startsWith("+27") && cellphone_number.length() == 12) {
            // Check if the remaining characters are all digits
            String digits = cellphone_number.substring(3);
            if (digits.matches("\\d+")) {
                System.out.println("Cellphone number successfully captured");
                return true;
            }
        }
        
        System.out.println("Cellphone number is incorrectly formatted or does not contain international code, please correct the number and try again.");
        return false;
    }
    
    // Getter for the Login instance
    public Login getUserLogin() {
        return userLogin;
    }
}