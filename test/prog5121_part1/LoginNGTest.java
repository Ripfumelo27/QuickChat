/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/EmptyTestNGTest.java to edit this template
 */
package prog5121_part1;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;
import org.testng.annotations.Test;

/**
 *
 * @author RC_Student_lab
 */
public class LoginNGTest {
    
    public LoginNGTest() {
        
        // Test the registration and login system
        Registration registration = new Registration();
        registration.userInput();
        
        // You can also test individual methods directly:
        Login testLogin = new Login("test_user", "Password123!", "John", "Doe");
        
        // Test username validation
        System.out.println("Testing username 'My_1': " + testLogin.checkUserName("My_1")); // Should return true
        System.out.println("Testing username 'Agent!!!!!': " + testLogin.checkUserName("Agent!!!!!")); // Should return false
        
        // Test password complexity
        System.out.println("Testing password 'Ch&&sec@ke99!': " + testLogin.checkPasswordComplexity("Ch&&sec@ke99!")); // Should return true
        System.out.println("Testing password 'password!': " + testLogin.checkPasswordComplexity("password!")); // Should return false
        
        // Test cellphone validation
        System.out.println("Testing cellphone '+27821234567': " + testLogin.checkCellPhoneNumber("+27821234567")); // Should return true
        System.out.println("Testing cellphone 'QB95520': " + testLogin.checkCellPhoneNumber("QB95520")); // Should return false
    }

    /**
     * Test of loginUser method, of class Login.
     */
    @Test
    public void testLoginUser() {
        System.out.println("loginUser");
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.loginUser();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of returnLoginStatus method, of class Login.
     */
    @Test
    public void testReturnLoginStatus() {
        System.out.println("returnLoginStatus");
        boolean loginSuccess = false;
        Login instance = null;
        String expResult = "";
        String result = instance.returnLoginStatus(loginSuccess);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkUserName method, of class Login.
     */
    @Test
    public void testCheckUserName() {
        System.out.println("checkUserName");
        String username = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkUserName(username);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkPasswordComplexity method, of class Login.
     */
    @Test
    public void testCheckPasswordComplexity() {
        System.out.println("checkPasswordComplexity");
        String password = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkPasswordComplexity(password);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkCellPhoneNumber method, of class Login.
     */
    @Test
    public void testCheckCellPhoneNumber() {
        System.out.println("checkCellPhoneNumber");
        String cellphoneNumber = "";
        Login instance = null;
        boolean expResult = false;
        boolean result = instance.checkCellPhoneNumber(cellphoneNumber);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerUser method, of class Login.
     */
    @Test
    public void testRegisterUser() {
        System.out.println("registerUser");
        String username = "";
        String password = "";
        Login instance = null;
        String expResult = "";
        String result = instance.registerUser(username, password);
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredUsername method, of class Login.
     */
    @Test
    public void testGetStoredUsername() {
        System.out.println("getStoredUsername");
        Login instance = null;
        String expResult = "";
        String result = instance.getStoredUsername();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStoredPassword method, of class Login.
     */
    @Test
    public void testGetStoredPassword() {
        System.out.println("getStoredPassword");
        Login instance = null;
        String expResult = "";
        String result = instance.getStoredPassword();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Login.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Login instance = null;
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Login.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Login instance = null;
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(result, expResult);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}


    

    