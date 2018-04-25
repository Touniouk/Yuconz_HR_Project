package Yuconz_HRSystem;

import java.util.ArrayList;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for SystemController
 * @author Tom, Nagendra, Luc, Reehan
 */
public class SystemControllerTest 
{
    
    public SystemControllerTest() 
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
     */
    @Test
    public void testCreatePersonalDetailsRecordHREmployee() {
        System.out.println("testCreatePersonalDetailsRecordHREmployee");
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "HREmployee");
        //Test
        int ID = 1;
        String firstname = "Test";
        String lastname = "Test";
        String dateOfBirth = "Test";
        String address = "Test";
        String town = "Test";
        String county = "Test";
        String postcode = "Test";
        String phoneNb = "Test";
        String mobileNb = "Test";
        String emergencyContact = "Test";
        String emergencyContactNb = "Test";
        SystemController instance2 = new SystemController("Rik", "123", false);
        String expResult = "Added Personal record for user #1 in database";
        String result = instance2.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, 
                address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
        //This line deletes the personal details document
        instance.deletePersonalDetails(1);
    }
    
    /**
     * Test of createPersonalDetailsRecord method, of class SystemController.
     */
    @Test
    public void testCreatePersonalDetailsRecordNonHREmployee() {
        System.out.println("testCreatePersonalDetailsRecordNonHREmployee");
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "Employee");
        //Test
        int ID = 1;
        String firstname = "Test";
        String lastname = "Test";
        String dateOfBirth = "Test";
        String address = "Test";
        String town = "Test";
        String county = "Test";
        String postcode = "Test";
        String phoneNb = "Test";
        String mobileNb = "Test";
        String emergencyContact = "Test";
        String emergencyContactNb = "Test";
        SystemController instance2 = new SystemController("Rik", "123", false);
        String expResult = "Cannot create personal details records (insuficient authorisations)";
        String result = instance2.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, 
                address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
    }
    
    /**
     * Test of createPersonalDetailsRecord method, of class SystemController.
     */
    @Test
    public void testCreatePersonalDetailsRecordHREmployeeRevoked() {
        System.out.println("testCreatePersonalDetailsRecordHREmployeeRevoked");
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "HREmployee");
        //Test
        int ID = 1;
        String firstname = "Test";
        String lastname = "Test";
        String dateOfBirth = "Test";
        String address = "Test";
        String town = "Test";
        String county = "Test";
        String postcode = "Test";
        String phoneNb = "Test";
        String mobileNb = "Test";
        String emergencyContact = "Test";
        String emergencyContactNb = "Test";
        SystemController instance2 = new SystemController("Rik", "123", true);
        String expResult = "Cannot create personal details records (insuficient authorisations)";
        String result = instance2.createPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, 
                address, town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
    }
    
    //--------------------------------

    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     */
    @Test
    public void testAmendPersonalDetailsRecordHREmployee() {
        System.out.println("testAmendPersonalDetailsRecordHREmployee");
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "HREmployee");
        //Test
        int ID = 1;
        String firstname = "Test";
        String lastname = "Test";
        String dateOfBirth = "Test";
        String address = "Test";
        String town = "Test";
        String county = "Test";
        String postcode = "Test";
        String phoneNb = "Test";
        String mobileNb = "Test";
        String emergencyContact = "Test";
        String emergencyContactNb = "Test";
        SystemController instance2 = new SystemController("Rik", "123", false);
        //Create Personal Record
        instance2.createPersonalDetailsRecord(ID, "firstname", "lastname", "dateOfBirth", "address", "town", 
                "county", "postcode", "phoneNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        String expResult = "Amended Personal record for user #1 in database";
        String result = instance2.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, 
                town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
        //This line deletes the personal details document
        instance.deletePersonalDetails(1);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     */
    @Test
    public void testAmendPersonalDetailsRecordNonHREmployeeAmendOwnRecord() {
        System.out.println("testAmendPersonalDetailsRecordNonHREmployeeAmendOwnRecord");
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "Employee");
        instance.addLoginDetail("Rick", "123", "HREmployee");
        //Test
        int ID = 1;
        String firstname = "Test";
        String lastname = "Test";
        String dateOfBirth = "Test";
        String address = "Test";
        String town = "Test";
        String county = "Test";
        String postcode = "Test";
        String phoneNb = "Test";
        String mobileNb = "Test";
        String emergencyContact = "Test";
        String emergencyContactNb = "Test";
        //Login as HR to create record
        SystemController instance2 = new SystemController("Rick", "123", false);
        //Create Personal Record
        instance2.createPersonalDetailsRecord(ID, "firstname", "lastname", "dateOfBirth", "address", "town", 
                "county", "postcode", "phoneNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        //Login as non HR
        SystemController instance3 = new SystemController("Rik", "123", false);
        String expResult = "Amended Personal record for user #1 in database";
        String result = instance3.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, 
                town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
        instance.deleteLoginDetails("Rick");
        //This line deletes the personal details document
        instance.deletePersonalDetails(1);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     */
    @Test
    public void testAmendPersonalDetailsRecordNonHREmployeeAmendOtherRecord() {
        System.out.println("testAmendPersonalDetailsRecordNonHREmployeeAmendOtherRecord");
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "Employee");
        instance.addLoginDetail("Rick", "123", "HREmployee");
        //Test
        int ID = 2;
        String firstname = "Test";
        String lastname = "Test";
        String dateOfBirth = "Test";
        String address = "Test";
        String town = "Test";
        String county = "Test";
        String postcode = "Test";
        String phoneNb = "Test";
        String mobileNb = "Test";
        String emergencyContact = "Test";
        String emergencyContactNb = "Test";
        //Login as HR to create record
        SystemController instance2 = new SystemController("Rick", "123", false);
        //Create Personal Record
        instance2.createPersonalDetailsRecord(ID, "firstname", "lastname", "dateOfBirth", "address", "town", 
                "county", "postcode", "phoneNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        //Login as non HR
        SystemController instance3 = new SystemController("Rik", "123", false);
        String expResult = "Cannot amend personal details records";
        String result = instance3.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, 
                town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
        instance.deleteLoginDetails("Rick");
        //This line deletes the personal details document
        instance.deletePersonalDetails(2);
    }
    
    /**
     * Test of amendPersonalDetailsRecord method, of class SystemController.
     */
    @Test
    public void testAmendPersonalDetailsRecordHREmployeeRevoked() {
        System.out.println("testAmendPersonalDetailsRecordNonHREmployeeAmendOtherRecord");
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "HREmployee");
        //Test
        int ID = 2;
        String firstname = "Test";
        String lastname = "Test";
        String dateOfBirth = "Test";
        String address = "Test";
        String town = "Test";
        String county = "Test";
        String postcode = "Test";
        String phoneNb = "Test";
        String mobileNb = "Test";
        String emergencyContact = "Test";
        String emergencyContactNb = "Test";
        //Login as HR to create record
        SystemController instance2 = new SystemController("Rik", "123", false);
        //Create Personal Record
        instance2.createPersonalDetailsRecord(ID, "firstname", "lastname", "dateOfBirth", "address", "town", 
                "county", "postcode", "phoneNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        //Login as non HR
        SystemController instance3 = new SystemController("Rik", "123", true);
        String expResult = "Cannot amend personal details records";
        String result = instance3.amendPersonalDetailsRecord(ID, firstname, lastname, dateOfBirth, address, 
                town, county, postcode, phoneNb, mobileNb, emergencyContact, emergencyContactNb);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
        //This line deletes the personal details document
        instance.deletePersonalDetails(2);
    }

    /**
     * Test of getPersonalDetailsRecord method, of class SystemController.
     */
    @Test
    public void testGetPersonalDetailsRecordAsDirector() {
        System.out.println("testGetPersonalDetailsRecordAsDirector");
        int ID = 2;
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "Director");
        //Test
        SystemController instance2 = new SystemController("Rik", "123", false);
        PersonalDetailsRecord testRecord = new PersonalDetailsRecord(ID, "firstname", "lastname", 
                "dateOfBirth", "address", "town", "county", "postcode", "phoneNb", "mobileNb", 
                "emergencyContact", "emergencyContactNb");
        instance.createPersonalRecord(testRecord);
        PersonalDetailsRecord expResult = testRecord;
        PersonalDetailsRecord result = instance2.getPersonalDetailsRecord(ID);
        assertEquals(expResult.getAddress(), result.getAddress());
        assertEquals(expResult.getCounty(), result.getCounty());
        assertEquals(expResult.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(expResult.getEmergencyContact(), result.getEmergencyContact());
        assertEquals(expResult.getEmergencyContactNb(), result.getEmergencyContactNb());
        assertEquals(expResult.getFirstname(), result.getFirstname());
        assertEquals(expResult.getID(), result.getID());
        assertEquals(expResult.getLastname(), result.getLastname());
        assertEquals(expResult.getMobileNb(), result.getMobileNb());
        assertEquals(expResult.getPhoneNb(), result.getPhoneNb());
        assertEquals(expResult.getPostcode(), result.getPostcode());
        assertEquals(expResult.getTown(), result.getTown());
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
    }

//    /**
//     * Test of createAnnualReviewRecord method, of class SystemController.
//     */
//    @Test
//    public void testCreateAnnualReviewRecord() {
//        System.out.println("createAnnualReviewRecord");
//        String name = "";
//        String section = "";
//        String jobTitle = "";
//        int reviewerID1 = 0;
//        int reviewerID2 = 0;
//        String achievements = "";
//        SystemController instance = null;
//        String expResult = "";
//        String result = instance.createAnnualReviewRecord(name, section, jobTitle, reviewerID1, reviewerID2, achievements);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of amendAnnualReviewRecord method, of class SystemController.
//     */
//    @Test
//    public void testAmendAnnualReviewRecord() {
//        System.out.println("amendAnnualReviewRecord");
//        int reviewID = 0;
//        String date = "";
//        String performance = "";
//        String goals = "";
//        String comments = "";
//        String recommendation = "";
//        boolean signed = false;
//        SystemController instance = null;
//        String expResult = "";
//        String result = instance.amendAnnualReviewRecord(reviewID, date, performance, goals, comments, recommendation, signed);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getAnnualReviewRecord method, of class SystemController.
//     */
//    @Test
//    public void testGetAnnualReviewRecord() {
//        System.out.println("getAnnualReviewRecord");
//        int reviewID = 0;
//        SystemController instance = null;
//        AnnualReviewRecord expResult = null;
//        AnnualReviewRecord result = instance.getAnnualReviewRecord(reviewID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getPastReviewRecords method, of class SystemController.
//     */
//    @Test
//    public void testGetPastReviewRecords() {
//        System.out.println("getPastReviewRecords");
//        int userID = 0;
//        SystemController instance = null;
//        ArrayList<AnnualReviewRecord> expResult = null;
//        ArrayList<AnnualReviewRecord> result = instance.getPastReviewRecords(userID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of checkIfInvolvedInReview method, of class SystemController.
//     */
//    @Test
//    public void testCheckIfInvolvedInReview() {
//        System.out.println("checkIfInvolvedInReview");
//        int staffID = 0;
//        SystemController instance = null;
//        HashMap expResult = null;
//        HashMap result = instance.checkIfInvolvedInReview(staffID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getCurrentUser method, of class SystemController.
//     */
//    @Test
//    public void testGetCurrentUser() {
//        System.out.println("getCurrentUser");
//        SystemController instance = null;
//        User expResult = null;
//        User result = instance.getCurrentUser();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getLoginFeedback method, of class SystemController.
//     */
//    @Test
//    public void testGetLoginFeedback() {
//        System.out.println("getLoginFeedback");
//        SystemController instance = null;
//        String expResult = "";
//        String result = instance.getLoginFeedback();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//    
}
