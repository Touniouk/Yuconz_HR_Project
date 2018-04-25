package Yuconz_HRSystem;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * The link between the user and the database
 * 
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 1.4
 */
public class SystemController 
{
    
    private User currentUser;
    private String loginFeedback;
    
    /**
     * @param enteredUsername
     * @param enteredPassword
     * @param revokedPriviliges 
     */
    public SystemController(String enteredUsername, String enteredPassword, boolean revokedPriviliges) 
    {
        try {
            login(enteredUsername, enteredPassword, revokedPriviliges);
            loginFeedback = "Accepted";
        } catch (Exception ex) {
            loginFeedback = ex.getMessage();
        }
    }
    
    /**
     * @param enteredUsername
     * @param enteredPassword
     * @param revokedPriviliges
     * @throws Exception 
     */
    private void login(String enteredUsername, String enteredPassword, boolean revokedPriviliges) 
            throws Exception 
    {
        
        HRDatabase database = new HRDatabase();
        HashMap<String, String> userInfo = database.login(enteredUsername, enteredPassword);
        if (userInfo.containsKey("Failed")) {
            Exception e = new Exception(userInfo.get("Failed"));
            throw e;
        }
        else {
            if (!revokedPriviliges) {
                currentUser = new User(Integer.parseInt(userInfo.get("ID")), userInfo.get("username")
                        , userInfo.get("password"), userInfo.get("authorisationLvl"));
            }
            else {
                currentUser = new User(Integer.parseInt(userInfo.get("ID")), userInfo.get("username")
                        , userInfo.get("password"), "Employee");
            }
        }
    }
    
    //----------------------------------------------------------------------------
    // Personal Details Records
    //----------------------------------------------------------------------------
    
    /**
     * @param ID
     * @param firstname
     * @param lastname
     * @param dateOfBirth
     * @param address
     * @param town
     * @param county
     * @param postcode
     * @param phoneNb
     * @param mobileNb
     * @param emergencyContact
     * @param emergencyContactNb 
     * @return  
     */
    public String createPersonalDetailsRecord(int ID, String firstname, String lastname, 
            String dateOfBirth, String address, String town, String county, String postcode, 
            String phoneNb, String mobileNb, String emergencyContact, String emergencyContactNb)
    {
        String feedback = "Unexpected error (createPersonalDetailsRecord sc)";
        if (currentUser != null) {
            if (currentUser.getAuthorisationLvl().equals("HREmployee")) {
                PersonalDetailsRecord personalDetailsRecord = new PersonalDetailsRecord(ID, 
                        firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, 
                        mobileNb, emergencyContact, emergencyContactNb);
                HRDatabase database = new HRDatabase();
                feedback = database.createPersonalRecord(personalDetailsRecord);
            }
            else {
                feedback = "Cannot create personal details records (insuficient authorisations)";
            }
        }
        else {
            System.out.println("Login first");
        }
        return feedback;
    }
    
    /**
     * @param ID
     * @param firstname
     * @param lastname
     * @param dateOfBirth
     * @param address
     * @param town
     * @param county
     * @param postcode
     * @param phoneNb
     * @param mobileNb
     * @param emergencyContact
     * @param emergencyContactNb 
     * @return  
     */
    public String amendPersonalDetailsRecord(int ID, String firstname, String lastname, 
            String dateOfBirth, String address, String town, String county, String postcode, 
            String phoneNb, String mobileNb, String emergencyContact, String emergencyContactNb) 
    {
        String feedback = "Unexpected error (amendPersonalDetailsRecord sc)";
        if (currentUser != null) {
            if (currentUser.getAuthorisationLvl().equals("HREmployee") || currentUser.getID() == ID) {
                PersonalDetailsRecord personalDetailsRecord = new PersonalDetailsRecord(ID, 
                        firstname, lastname, dateOfBirth, address, town, county, postcode, phoneNb, 
                        mobileNb, emergencyContact, emergencyContactNb);
                HRDatabase database = new HRDatabase();
                feedback = database.amendPersonalRecord(personalDetailsRecord);
            }
            else {
                feedback = "Cannot amend personal details records";
            }
        }
        else {
            System.out.println("Login first");
        }
        return feedback;
    }
    
    /**
     * Return personal details record for entered ID
     * @param ID
     * @return
     * @throws NullPointerException 
     */
    public PersonalDetailsRecord getPersonalDetailsRecord(int ID) throws NullPointerException
    {
        HRDatabase database = new HRDatabase();
        
        if (currentUser != null) {
            if (currentUser.getAuthorisationLvl().equals("HREmployee") ||
                    currentUser.getAuthorisationLvl().equals("Director")) {
                return database.readPersonalRecord(ID);
            }
            else {
                return database.readPersonalRecord(currentUser.getID());
            }
        }
        else {
            System.out.println("Login first");
        }
        return null;
    }
    
    //----------------------------------------------------------------------------
    // Annual Review Records
    //----------------------------------------------------------------------------
    
    /**
     * Create a new review record
     * @param name
     * @param section
     * @param jobTitle
     * @param reviewerID1
     * @param reviewerID2
     * @param achievements
     * @return 
     */
    public String createAnnualReviewRecord(String name, String section, String jobTitle, 
            int reviewerID1, int reviewerID2, String achievements)
    {
        String feedback = "Unexpected error";
        if (currentUser != null) {
            AnnualReviewRecord annualReviewRecord = new AnnualReviewRecord(
                    currentUser.getID(), name, section, jobTitle, reviewerID1, reviewerID2, achievements);
            HRDatabase database = new HRDatabase();
            feedback = database.createAnnualReviewRecord(annualReviewRecord);
            }
        else {
            System.out.println("Login first");
        }
        return feedback;
    }
    
    /**
     * Amend an existing, uncompleted review
     * @param reviewID
     * @param date
     * @param performance
     * @param goals
     * @param comments
     * @param recommendation
     * @param signed
     * @return 
     */
    public String amendAnnualReviewRecord(int reviewID, String date,
            String performance, String goals, String comments, String recommendation,
            boolean signed) {
        String feedback = "Unexpected error (amendAnnualReviewRecord sc)";
        if (currentUser != null) {
            //Check if the record exists
            try {
                AnnualReviewRecord annualReviewRecord = getAnnualReviewRecord(reviewID);
                //Check authorisation
                if (annualReviewRecord.getID() == getCurrentUser().getID() 
                        || (annualReviewRecord.getReviewerID1()== getCurrentUser().getID() 
                        && !getCurrentUser().getAuthorisationLvl().equals("Employee")) 
                        || (annualReviewRecord.getReviewerID2() == getCurrentUser().getID())
                        && !getCurrentUser().getAuthorisationLvl().equals("Employee")) {
                    //Amend record
                    annualReviewRecord.setPerformance(performance);
                    annualReviewRecord.setGoals(goals);
                    annualReviewRecord.setComments(comments);
                    annualReviewRecord.setRecommendation(recommendation);
                    if (annualReviewRecord.getReviewerID1() == getCurrentUser().getID()) {
                        annualReviewRecord.setReviewer1Signed(signed);
                        annualReviewRecord.setDateReviewer1(date);
                    }
                    else if (annualReviewRecord.getReviewerID2() == getCurrentUser().getID()) {
                        annualReviewRecord.setReviewer2Signed(signed);
                        annualReviewRecord.setDateReviewer2(date);
                    }
                    else if (annualReviewRecord.getID() == getCurrentUser().getID()) {
                        annualReviewRecord.setRevieweeSigned(signed);
                        annualReviewRecord.setDateReviewee(date);
                    }
                    if (annualReviewRecord.isRevieweeSigned() && annualReviewRecord.isReviewer1Signed() 
                            && annualReviewRecord.isReviewer2Signed()) {
                        annualReviewRecord.setReviewStatus("Signed");
                    }
                    HRDatabase database = new HRDatabase();
                    feedback = database.amendAnnualReviewRecord(annualReviewRecord);
                }
            } catch (NullPointerException ex) {
                feedback = ex.getMessage();
            }
        }
        else {
            System.out.println("Login first");
        }
        return feedback;
    }
    
    /**
     * Retrieve an annual review record from the database
     * @param reviewID
     * @return
     * @throws NullPointerException 
     */
    public AnnualReviewRecord getAnnualReviewRecord(int reviewID) throws NullPointerException
    {
        
        if (currentUser != null) {
            HRDatabase database = new HRDatabase();
            return database.readAnnualReviewRecord(reviewID);
        }
        else {
            System.out.println("Login first");
        }
        return null;
    }
    
    /**
     * Return a list of past review records for the given user
     * @param userID
     * @return 
     */
    public ArrayList<AnnualReviewRecord> getPastReviewRecords(int userID)
    {
        HashMap<String, Integer> hashMap = checkIfInvolvedInReview(currentUser.getID());
        if (hashMap.containsKey("Reviewee") ||
                hashMap.containsKey("Reviewer1") ||
                hashMap.containsKey("Reviewer2") ||
                userID == currentUser.getID() ||
                currentUser.getAuthorisationLvl().equals("Director") ||
                currentUser.getAuthorisationLvl().equals("HREmployee")) {
            HRDatabase database = new HRDatabase();
            ArrayList<AnnualReviewRecord> records = database.getPastReviewRecords(userID);
            return records;
        }
        return null;
    }
    
    public HashMap checkIfInvolvedInReview(int staffID) {
        HRDatabase database = new HRDatabase();
        return database.checkIfInvolvedInReview(staffID);
    }
    
    //----------------------------------------------------------------------------

    public User getCurrentUser() 
    {
        return currentUser;
    }
    
    public String getLoginFeedback() 
    {
        return loginFeedback;
    }
}