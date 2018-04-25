package Yuconz_HRSystem;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *Test class for SystemController.
 * 
 * @author Tom, Nagendra, Luc, Reehan
 */
public class SystemControllerTest321 
{
    
    public SystemControllerTest321() 
    {
    }
    
    @BeforeClass
    public static void setUpClass() 
    {
    }
    
    @AfterClass
    public static void tearDownClass() 
    {
    }
    
    @Before
    public void setUp() 
    {
    }
    
    @After
    public void tearDown() 
    {
    }

    /**
     * Test of createPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, HREmployee.
     */
    @Test
    public void testCreatePersonalDetailsRecord1()
    {
        System.out.println("createPersonalDetailsRecord1");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("John", "123", false);
        instance.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of createPersonalDetailsRecord method, of class SystemController.
     * Valid username, incorrect password, HREmployee.
     */
    @Test
    public void testCreatePersonalDetailsRecord2() 
    {
        System.out.println("createPersonalDetailsRecord2");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("John", "-", false);
        instance.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of createPersonalDetailsRecord method, of class SystemController.
     * Invalid username.
     */
    @Test
    public void testCreatePersonalDetailsRecord3()
    {
        System.out.println("createPersonalDetailsRecord3");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("-", "-", false);
        instance.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of createPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, HREmployee, Revoked.
     */
    @Test
    public void testCreatePersonalDetailsRecord4()
    {
        System.out.println("createPersonalDetailsRecord4");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("John", "123", true);
        instance.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of createPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, Employee.
     */
    @Test
    public void testCreatePersonalDetailsRecord5()
    {
        System.out.println("createPersonalDetailsRecord5");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("Bob", "123", true);
        instance.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, HREmployee.
     */
    @Test
    public void testAmendPersonalDetailsRecord1() 
    {
        System.out.println("amendPersonalDetailsRecord1");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("John", "123", false);
        instance.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     * Valid username, incorrect password, HREmployee.
     */
    @Test
    public void testAmendPersonalDetailsRecord2() 
    {
        System.out.println("amendPersonalDetailsRecord2");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("John", "-", false);
        instance.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     * Invalid username.
     */
    @Test
    public void testAmendPersonalDetailsRecord3() 
    {
        System.out.println("amendPersonalDetailsRecord3");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("-", "-", false);
        instance.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, HREmployee, Revoked.
     */
    @Test
    public void testAmendPersonalDetailsRecord4() 
    {
        System.out.println("amendPersonalDetailsRecord4");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("John", "123", true);
        instance.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, Employee.
     */
    @Test
    public void testAmendPersonalDetailsRecord5() 
    {
        System.out.println("amendPersonalDetailsRecord5");
        int ID = 0;
        String firstname = "";
        String lastname = "";
        String dateOfBirth = "";
        String address = "";
        String town = "";
        String county = "";
        String postcode = "";
        String phoneNb = "";
        String mobileNb = "";
        String emergencyContact = "";
        String emergencyContactNb = "";
        SystemController instance = new SystemController("Bob", "123", false);
        instance.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
    }

    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, HREmployee.
     */
    @Test
    public void testGetPersonalDetailsRecord1() 
    {
        System.out.println("getPersonalDetailsRecord1");
        int ID = 0;
        SystemController instance = new SystemController("John", "123", false);
        PersonalDetailsRecord result = instance.getPersonalDetailsRecord(ID);
    }
    
    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     * Valid username, incorrect password, HREmployee.
     */
    @Test
    public void testGetPersonalDetailsRecord2() 
    {
        System.out.println("getPersonalDetailsRecord2");
        int ID = 0;
        SystemController instance = new SystemController("John", "-", false);
        PersonalDetailsRecord expResult = null;
        PersonalDetailsRecord result = instance.getPersonalDetailsRecord(ID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     * Invalid username.
     */
    @Test
    public void testGetPersonalDetailsRecord3() 
    {
        System.out.println("getPersonalDetailsRecord3");
        int ID = 0;
        SystemController instance = new SystemController("-", "123", false);
        PersonalDetailsRecord expResult = null;
        PersonalDetailsRecord result = instance.getPersonalDetailsRecord(ID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, HREmployee, Revoked.
     */
    @Test
    public void testGetPersonalDetailsRecord4() 
    {
        System.out.println("getPersonalDetailsRecord4");
        int ID = 0;
        SystemController instance = new SystemController("John", "123", true);
        PersonalDetailsRecord result = instance.getPersonalDetailsRecord(ID);
    }
    
    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, Employee, own ID.
     */
    @Test()
    public void testGetPersonalDetailsRecord5() 
    {
        System.out.println("getPersonalDetailsRecord5");
        SystemController instance = new SystemController("Bob", "123", false);
        int ID = instance.getCurrentUser().getID();
        PersonalDetailsRecord result = instance.getPersonalDetailsRecord(ID);
    }
    
    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, Employee, other ID.
     */
    @Test
    public void testGetPersonalDetailsRecord6() 
    {
        System.out.println("getPersonalDetailsRecord6");
        SystemController instance = new SystemController("Bob", "123", true);
        int ID = 0;
        PersonalDetailsRecord result = instance.getPersonalDetailsRecord(ID);
    }
    
    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     * Valid username and password, Director.
     */
    @Test
    public void testGetPersonalDetailsRecord7() 
    {
        System.out.println("getPersonalDetailsRecord7");
        SystemController instance = new SystemController("Bob", "123", true);
        int ID = 0;
        PersonalDetailsRecord expResult = null;
        PersonalDetailsRecord result = instance.getPersonalDetailsRecord(ID);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCurrentUser method, of class SystemController.
     * With a correct login.
     */
    @Test
    public void testGetCurrentUser1() 
    {
        System.out.println("getCurrentUser1");
        SystemController instance = new SystemController("John", "123", true);
        User expResult = instance.getCurrentUser();
        User result = instance.getCurrentUser();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getCurrentUser method, of class SystemController.
     * With a failed login.
     */
    @Test(expected = NullPointerException.class) 
    public void testGetCurrentUser2()
    {
        System.out.println("getCurrentUser2");
        SystemController instance = null;
        User expResult = null;
        User result = instance.getCurrentUser();
        assertEquals(expResult, result);
    }

    /**
     * Test of getLoginFeedback method, of class SystemController.
     * Valid username, invalid password.
     */
    @Test
    public void testGetLoginFeedback1() 
    {
        System.out.println("getLoginFeedback1");
        SystemController instance = new SystemController("John", "-", false);
        String expResult = "Incorrect password";
        String result = instance.getLoginFeedback();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLoginFeedback method, of class SystemController.
     * Valid username, valid password.
     */
    @Test
    public void testGetLoginFeedback2() 
    {
        System.out.println("getLoginFeedback2");
        SystemController instance = new SystemController("John", "123", false);
        String expResult = "Accepted";
        String result = instance.getLoginFeedback();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLoginFeedback method, of class SystemController.
     * Valid username, empty password.
     */
    @Test
    public void testGetLoginFeedback3() 
    {
        System.out.println("getLoginFeedback3");
        SystemController instance = new SystemController("John", "", false);
        String expResult = "Incorrect password";
        String result = instance.getLoginFeedback();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of getLoginFeedback method, of class SystemController.
     * Invalid username.
     */
    @Test
    public void testGetLoginFeedback4() 
    {
        System.out.println("getLoginFeedback4");
        SystemController instance = new SystemController("-", "123", false);
        String expResult = "Username not found";
        String result = instance.getLoginFeedback();
        assertEquals(expResult, result);
    }
}
