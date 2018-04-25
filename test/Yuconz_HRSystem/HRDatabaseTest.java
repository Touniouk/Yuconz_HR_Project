package Yuconz_HRSystem;

import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests for class HRDatabase
 * @author Tom, Nagendra, Luc, Reehan
 */
public class HRDatabaseTest 
{
    
    public HRDatabaseTest() 
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
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    //----------------------------------------------------------------------------
    // Create tables
    //----------------------------------------------------------------------------

    /**
     * Test of createTable method, of class HRDatabase.
     */
    @Test
    public void testCreateTableAnnualReview() {
        System.out.println("testCreateTableAnnualReview");
        String sql = "CREATE TABLE IF NOT EXISTS annualReviewRecords("
                        + "reviewID INTEGER PRIMARY KEY, "
                        + "staffID int, "
                        + "name String, "
                        + "reviewerID1 int, "
                        + "reviewerID2 int, "
                        + "section String, "
                        + "jobTitle String, "
                        + "performance String, "
                        + "comments String, "
                        + "recommendation String, "
                        + "reviewStatus String, "
                        + "reviewer1Signed boolean, "
                        + "dateReviewer1 String, "
                        + "reviewer2Signed boolean, "
                        + "dateReviewer2 String, "
                        + "revieweeSigned boolean, "
                        + "dateReviewee String, "
                        + "goals String, "
                        + "objectives String, "
                        + "achievements String);";
        HRDatabase instance = new HRDatabase();
        instance.createTable(sql);
    }
    
    /**
     * Test of createTable method, of class HRDatabase.
     */
    @Test
    public void testCreateTablePersonalDetails() {
        System.out.println("testCreateTablePersonalDetails");
        String sql = "CREATE TABLE IF NOT EXISTS personalRecords("
                        + "ID int, "
                        + "firstname String, "
                        + "lastname String, "
                        + "dateOfBirth String, "
                        + "address String, "
                        + "town String, "
                        + "county String, "
                        + "postcode String, "
                        + "phoneNb String, "
                        + "mobileNb String, "
                        + "emergencyContact String, "
                        + "emergencyContactNb String, "
                        + "primary key(ID));";
        HRDatabase instance = new HRDatabase();
        instance.createTable(sql);
    }
    
    /**
     * Test of createTable method, of class HRDatabase.
     */
    @Test
    public void testCreateTableLoginDetails() {
        System.out.println("testCreateTableLoginDetails");
        String sql = "CREATE TABLE IF NOT EXISTS loginDetails("
                        + "ID INTEGER PRIMARY KEY, "
                        + "username String, "
                        + "password String, "
                        + "authorisationLvl String);" ;
        HRDatabase instance = new HRDatabase();
        instance.createTable(sql);
    }
    
    //----------------------------------------------------------------------------
    // Login Details Records
    //----------------------------------------------------------------------------

    /**
     * Test of addLoginDetail method, of class HRDatabase.
     */
    @Test
    public void testAddLoginDetail() {
        System.out.println("testAddLoginDetail");
        String username = "Rik";
        String password = "123";
        String authorisationLvl = "";
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail(username, password, authorisationLvl);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
    }
    
    //----------------------------------------------------------------------------
    // Personal Details Records
    //----------------------------------------------------------------------------

    /**
     * Test of createPersonalRecord method, of class HRDatabase.
     */
    @Test
    public void testCreatePersonalRecordNullRecord() {
        System.out.println("testCreatePersonalRecordNullRecord");
        PersonalDetailsRecord record = null;
        HRDatabase instance = new HRDatabase();
        String expResult = "Input record is null";
        String result = instance.createPersonalRecord(record);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createPersonalRecord method, of class HRDatabase.
     */
    @Test
    public void testCreatePersonalRecordValidRecord() {
        System.out.println("testCreatePersonalRecordValidRecord");
        PersonalDetailsRecord record = new PersonalDetailsRecord(2, "name", "lastname", "date", "address", 
                "town", "county", "postcode", "phonNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        HRDatabase instance = new HRDatabase();
        String expResult = "Added Personal record for user #" + record.getID() + " in database";
        String result = instance.createPersonalRecord(record);
        assertEquals(expResult, result);
        //This line deletes the created personal record so that the tests don't add more login details
        instance.deletePersonalDetails(2);
    }
    
    //------------------------------

    /**
     * Test of amendPersonalRecord method, of class HRDatabase.
     */
    @Test
    public void testAmendPersonalRecordNonExistingRecord() {
        System.out.println("testAmendPersonalRecordNonExistingRecord");
        PersonalDetailsRecord record = new PersonalDetailsRecord(3, "name", "lastname", "date", "address", 
                "town", "county", "postcode", "phonNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        HRDatabase instance = new HRDatabase();
        String expResult = "Personal details record for user #3 doesn't exist";
        String result = instance.amendPersonalRecord(record);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of amendPersonalRecord method, of class HRDatabase.
     */
    @Test
    public void testAmendPersonalRecordNullRecord() {
        System.out.println("testAmendPersonalRecordNullRecord");
        PersonalDetailsRecord record = null;
        HRDatabase instance = new HRDatabase();
        String expResult = "Input record is null";
        String result = instance.amendPersonalRecord(record);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of amendPersonalRecord method, of class HRDatabase.
     */
    @Test
    public void testAmendPersonalRecordExistingRecord() {
        System.out.println("testAmendPersonalRecordExistingRecord");
        PersonalDetailsRecord record = new PersonalDetailsRecord(2, "name", "lastname", "date", "address", 
                "town", "county", "postcode", "phonNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        HRDatabase instance = new HRDatabase();
        //Create a new Personal Details Record
        instance.createPersonalRecord(new PersonalDetailsRecord(2, "name2", "lastname2", "date2", "address2", 
                "town2", "county2", "postcode2", "phonNb2", "mobileNb2", "emergencyContact2", "emergencyContactNb2"));
        //Test to amend
        String expResult = "Amended Personal record for user #2 in database";
        String result = instance.amendPersonalRecord(record);
        assertEquals(expResult, result);
        //This line deletes the created personal record
        instance.deletePersonalDetails(2);
    }
    
    //----------------------------------

    /**
     * Test of readPersonalRecord method, of class HRDatabase.
     */
    @Test
    public void testReadPersonalRecordNonExistingRecord() {
        System.out.println("testReadPersonalRecordNonExistingRecord");
        int ID = 2;
        HRDatabase instance = new HRDatabase();
        PersonalDetailsRecord expResult = null;
        PersonalDetailsRecord result = instance.readPersonalRecord(ID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of readPersonalRecord method, of class HRDatabase.
     */
    @Test
    public void testReadPersonalRecordExistingRecord() {
        System.out.println("testReadPersonalRecordExistingRecord");
        int ID = 2;
        HRDatabase instance = new HRDatabase();
        //Create a new Personal Details Record
        PersonalDetailsRecord testRecord = new PersonalDetailsRecord(2, "name", "lastname", "date", "address", 
                "town", "county", "postcode", "phonNb", "mobileNb", "emergencyContact", "emergencyContactNb");
        instance.createPersonalRecord(testRecord);
        //Try to get it
        //Check if both records have the same values
        PersonalDetailsRecord expResult = testRecord;
        PersonalDetailsRecord result = instance.readPersonalRecord(ID);
        assertEquals(expResult.getID(), result.getID());
        assertEquals(expResult.getAddress(), result.getAddress());
        assertEquals(expResult.getCounty(), result.getCounty());
        assertEquals(expResult.getDateOfBirth(), result.getDateOfBirth());
        assertEquals(expResult.getEmergencyContact(), result.getEmergencyContact());
        assertEquals(expResult.getEmergencyContactNb(), result.getEmergencyContactNb());
        assertEquals(expResult.getFirstname(), result.getFirstname());
        assertEquals(expResult.getLastname(), result.getLastname());
        assertEquals(expResult.getMobileNb(), result.getMobileNb());
        assertEquals(expResult.getPhoneNb(), result.getPhoneNb());
        assertEquals(expResult.getPostcode(), result.getPostcode());
        assertEquals(expResult.getTown(), result.getTown());
        //This line deletes the created personal record
        instance.deletePersonalDetails(2);
    }
    
    //----------------------------------------------------------------------------
    // Annual Review Records
    //----------------------------------------------------------------------------

    /**
     * Test of createAnnualReviewRecord method, of class HRDatabase.
     */
    @Test
    public void testCreateAnnualReviewRecordNullRecord() {
        System.out.println("testCreateAnnualReviewRecordNullRecord");
        AnnualReviewRecord record = null;
        HRDatabase instance = new HRDatabase();
        String expResult = "Input record is null";
        String result = instance.createAnnualReviewRecord(record);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of createAnnualReviewRecord method, of class HRDatabase.
     */
    @Test
    public void testCreateAnnualReviewRecordValidRecord() {
        System.out.println("testCreateAnnualReviewRecordValidRecord");
        AnnualReviewRecord record = new AnnualReviewRecord(2, "Bob", "section", "jobTitle", 3, 4, "achievements");
        HRDatabase instance = new HRDatabase();
        String expResult = "Added Annual Review record for user #2 in database";
        String result = instance.createAnnualReviewRecord(record);
        assertEquals(expResult, result);
        //This line deletes the created review record
        instance.deleteAnnualReview(2);
    }
    
    //---------------------------------

    /**
     * Test of amendAnnualReviewRecord method, of class HRDatabase.
     */
    @Test
    public void testAmendNonExistingAnnualReviewRecord() {
        System.out.println("testAmendNonExistingAnnualReviewRecord");
        AnnualReviewRecord record = new AnnualReviewRecord(2, "Bob", "section", "jobTitle", 3, 4, "achievements");
        HRDatabase instance = new HRDatabase();
        String expResult = "Review record 0 doesn't exist";
        String result = instance.amendAnnualReviewRecord(record);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of amendAnnualReviewRecord method, of class HRDatabase.
     */
    @Test
    public void testAmendExistingAnnualReviewRecord() {
        System.out.println("testAmendExistingAnnualReviewRecord");
        AnnualReviewRecord record = new AnnualReviewRecord(1, 2, "name", 3, 4, "section", "jobTitle", 
                "performance", "comments", "recommendation", "reviewStatus", false, "dateReviewer1", false, 
                "dateReviewer2", false, "dateReviewee", "goals", "objectives", "achievements");
        HRDatabase instance = new HRDatabase();
        //Create a new annual review record
        instance.createAnnualReviewRecord(record);
        String expResult = "Amended Review record #1 in database";
        String result = instance.amendAnnualReviewRecord(record);
        assertEquals(expResult, result);
        //This line deletes the created review record
        instance.deleteAnnualReview(2);
    }
    
    /**
     * Test of amendAnnualReviewRecord method, of class HRDatabase.
     */
    @Test
    public void testAmendNullAnnualReviewRecord() {
        System.out.println("testAmendNullAnnualReviewRecord");
        AnnualReviewRecord record = null;
        HRDatabase instance = new HRDatabase();
        String expResult = "Input record is null";
        String result = instance.amendAnnualReviewRecord(record);
        assertEquals(expResult, result);
    }
    
    //-------------------------------

    /**
     * Test of readAnnualReviewRecord method, of class HRDatabase.
     */
    @Test
    public void testReadNonExistingAnnualReviewRecord() {
        System.out.println("testReadNonExistingAnnualReviewRecord");
        int reviewID = 0;
        HRDatabase instance = new HRDatabase();
        AnnualReviewRecord expResult = null;
        AnnualReviewRecord result = instance.readAnnualReviewRecord(reviewID);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of readAnnualReviewRecord method, of class HRDatabase.
     */
    @Test
    public void testReadExistingAnnualReviewRecord() {
        System.out.println("testReadExistingAnnualReviewRecord");
        int reviewID = 1;
        HRDatabase instance = new HRDatabase();
        //Create an annual review
        AnnualReviewRecord record = new AnnualReviewRecord(2, "name", "section", "jobTitle", 3, 
                4, "achievements");
        instance.createAnnualReviewRecord(record);
        AnnualReviewRecord expResult = record;
        AnnualReviewRecord result = instance.readAnnualReviewRecord(reviewID);
        assertEquals(expResult.getAchievements(), result.getAchievements());
        assertEquals(expResult.getComments(), result.getComments());
        assertEquals(expResult.getDateReviewee(), result.getDateReviewee());
        assertEquals(expResult.getDateReviewer1(), result.getDateReviewer1());
        assertEquals(expResult.getDateReviewer2(), result.getDateReviewer2());
        assertEquals(expResult.getGoals(), result.getGoals());
        assertEquals(expResult.getJobTitle(), result.getJobTitle());
        assertEquals(expResult.getName(), result.getName());
        assertEquals(expResult.getObjectives(), result.getObjectives());
        assertEquals(expResult.getPerformance(), result.getPerformance());
        assertEquals(expResult.getRecommendation(), result.getRecommendation());
        assertEquals(expResult.getReviewStatus(), result.getReviewStatus());
        assertEquals(expResult.getReviewerID1(), result.getReviewerID1());
        assertEquals(expResult.getReviewerID2(), result.getReviewerID2());
        assertEquals(expResult.getSection(), result.getSection());
        assertEquals(expResult.isRevieweeSigned(), result.isRevieweeSigned());
        assertEquals(expResult.isReviewer1Signed(), result.isReviewer1Signed());
        assertEquals(expResult.isReviewer2Signed(), result.isReviewer2Signed());
        //This line deletes the created review record
        instance.deleteAnnualReview(2);
    }
    
    //---------------------------

//    /**
//     * Test of getPastReviewRecords method, of class HRDatabase.
//     */
//    @Test
//    public void testGetPastReviewRecords() {
//        System.out.println("getPastReviewRecords");
//        int userID = 0;
//        HRDatabase instance = new HRDatabase();
//        ArrayList expResult = null;
//        ArrayList result = instance.getPastReviewRecords(userID);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of checkIfInvolvedInReview method, of class HRDatabase.
     */
    @Test
    public void testCheckIfInvolvedInReviewWhenReviewer1() {
        System.out.println("testCheckIfInvolvedInReviewWhenReviewer1");
        int staffID = 3;
        HRDatabase instance = new HRDatabase();
        HashMap<String, Integer> expResult = new HashMap();
        expResult.put("Reviewer1", 1);
        //Create new review for user 2 with user 3 as a reviewer
        AnnualReviewRecord record = new AnnualReviewRecord(2, "name", "section", "jobTitle", 3, 
                4, "achievements");
        instance.createAnnualReviewRecord(record);
        HashMap result = instance.checkIfInvolvedInReview(staffID);
        expResult.keySet().forEach((key) -> {
            assertEquals(expResult.get(key), result.get(key));
        });
        //This line deletes the created review record
        instance.deleteAnnualReview(2);
    }
    
    /**
     * Test of checkIfInvolvedInReview method, of class HRDatabase.
     */
    @Test
    public void testCheckIfInvolvedInReviewWhenReviewer2() {
        System.out.println("testCheckIfInvolvedInReviewWhenReviewer2");
        int staffID = 4;
        HRDatabase instance = new HRDatabase();
        HashMap<String, Integer> expResult = new HashMap();
        expResult.put("Reviewer2", 1);
        //Create new review for user 2 with user 3 as a reviewer
        AnnualReviewRecord record = new AnnualReviewRecord(2, "name", "section", "jobTitle", 3, 
                4, "achievements");
        instance.createAnnualReviewRecord(record);
        HashMap result = instance.checkIfInvolvedInReview(staffID);
        expResult.keySet().forEach((key) -> {
            assertEquals(expResult.get(key), result.get(key));
        });
        //This line deletes the created review record
        instance.deleteAnnualReview(2);
    }
    
    /**
     * Test of checkIfInvolvedInReview method, of class HRDatabase.
     */
    @Test
    public void testCheckIfInvolvedInReviewWhenReviewee() {
        System.out.println("testCheckIfInvolvedInReviewWhenReviewee");
        int staffID = 2;
        HRDatabase instance = new HRDatabase();
        HashMap<String, Integer> expResult = new HashMap();
        expResult.put("Reviewee", 1);
        //Create new review for user 2 with user 3 as a reviewer
        AnnualReviewRecord record = new AnnualReviewRecord(2, "name", "section", "jobTitle", 3, 
                4, "achievements");
        instance.createAnnualReviewRecord(record);
        HashMap result = instance.checkIfInvolvedInReview(staffID);
        assertEquals(expResult, result);
        //This line deletes the created review record
        instance.deleteAnnualReview(2);
    }
    
    /**
     * Test of checkIfInvolvedInReview method, of class HRDatabase.
     */
    @Test
    public void testCheckIfInvolvedInReviewWhenNotInvolved() {
        System.out.println("testCheckIfInvolvedInReviewWhenNotInvolved");
        int staffID = 0;
        HRDatabase instance = new HRDatabase();
        HashMap<String, Integer> expResult = new HashMap();
        expResult.put("Not Involved", 0);
        HashMap result = instance.checkIfInvolvedInReview(staffID);
        assertEquals(expResult, result);
    }

    /**
     * Test of login method, of class HRDatabase.
     */
    @Test
    public void testCorrectLogin() {
        System.out.println("testCorrectLogin");
        String username = "Rik";
        String password = "123";
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail(username, password, "authorisationLvl");
        //Check
        HashMap<String, String> expResult = new HashMap();
        expResult.put("authorisationLvl", "authorisationLvl");
        expResult.put("ID", "1");
        expResult.put("password", "123");
        expResult.put("username", "Rik");
        HashMap result = instance.login(username, password);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
    }
    
    /**
     * Test of login method, of class HRDatabase.
     */
    @Test
    public void testFailedLoginIncorrectUsername() {
        System.out.println("testFailedLogin");
        String username = "-";
        String password = "123";
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "authorisationLvl");
        //Check
        HashMap<String, String> expResult = new HashMap();
        expResult.put("Failed", "Username not found");
        HashMap result = instance.login(username, password);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
    }
    
    /**
     * Test of login method, of class HRDatabase.
     */
    @Test
    public void testFailedLoginIncorrectPassword() {
        System.out.println("testFailedLogin");
        String username = "Rik";
        String password = "-";
        //Add login details
        HRDatabase instance = new HRDatabase();
        instance.addLoginDetail("Rik", "123", "authorisationLvl");
        //Check
        HashMap<String, String> expResult = new HashMap();
        expResult.put("Failed", "Incorrect password");
        HashMap result = instance.login(username, password);
        assertEquals(expResult, result);
        //This line deletes the created login details so that the tests don't add more login details
        instance.deleteLoginDetails("Rik");
    }
}