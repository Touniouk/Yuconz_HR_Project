package Yuconz_HRSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * All methods for creating tables and modifying data in the database
 * 
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 2.3
 */
public class HRDatabase 
{
    /**
     * Get a connection to the database
     * @return 
     */
    private Connection connect() 
    {
        String sql = "jdbc:sqlite:HRDatabase.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conn;
    }
    
    /**
     * Creates tables for the database
     * @param sql the input SQL String
     */
    public void createTable(String sql) 
    {
        Connection conn = null;
        try {
            conn = connect();
            Statement statement = conn.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Adds login details to the loginDetails table in the database
     * Only currently used in the Main method
     * @param username
     * @param password
     * @param authorisationLvl 
     */
    public void addLoginDetail(String username, String password, String authorisationLvl)
    {
        Connection conn = null;
        try {
            conn = connect();
            String sql = "SELECT username FROM loginDetails WHERE username = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, username);
            ResultSet result = pS.executeQuery();
            if (result.next()) {
                System.out.println("Username \"" + username + "\" already exists");
            }
            else {
                sql = "INSERT INTO loginDetails VALUES(?,?,?,?)";
                pS = conn.prepareStatement(sql);
                pS.setString(2, username);
                pS.setString(3, password);
                pS.setString(4, authorisationLvl);
                pS.executeUpdate();
                printTable("loginDetails");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: loginDetails")) {
                String sql = "CREATE TABLE IF NOT EXISTS loginDetails("
                        + "ID INTEGER PRIMARY KEY, "
                        + "username String, "
                        + "password String, "
                        + "authorisationLvl String);" ;
                createTable(sql);
                System.out.println("Created table \"loginDetails\" in database");
                addLoginDetail(username, password, authorisationLvl);
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Gets Personal Record details from Database
     * @param record 
     * @return  
     */
    public String createPersonalRecord(PersonalDetailsRecord record) 
    {
        String feedback = "Unexpected error (createPersonalRecord database)";
        Connection conn = null;
        try {
            conn = connect();
                String sql = "INSERT INTO personalRecords VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pS = conn.prepareStatement(sql);
                pS.setInt(1, record.getID());
                pS.setString(2, record.getFirstname());
                pS.setString(3, record.getLastname());
                pS.setString(4, record.getDateOfBirth());
                pS.setString(5, record.getAddress());
                pS.setString(6, record.getTown());
                pS.setString(7, record.getCounty());
                pS.setString(8, record.getPostcode());
                pS.setString(9, record.getPhoneNb());
                pS.setString(10, record.getMobileNb());
                pS.setString(11, record.getEmergencyContact());
                pS.setString(12, record.getEmergencyContactNb());
                pS.executeUpdate();
                feedback = "Added Personal record for user #" + record.getID() + " in database";
                printTable("personalRecords");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: personalRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"personalRecords\" in database");
                feedback = createPersonalRecord(record);
            }
            else if (ex.getMessage().contains("UNIQUE constraint failed")) {
                feedback = "Personal details record for user " + record.getID() + " already exists";
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                feedback = ex.getMessage();
            }
        } catch (NullPointerException ex) {
            feedback = "Input record is null";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return feedback;
    }
    
    /**
     * Amends Personal Details in the Database
     * @param record 
     * @return  
     */
    public String amendPersonalRecord(PersonalDetailsRecord record) 
    {
        String feedback = "Unexpected error (amendPersonalRecord database)";
        Connection conn = null;
        try {
            conn = connect();
            String sql = "SELECT ID FROM personalRecords WHERE ID = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, record.getID());
            ResultSet result = pS.executeQuery();
            if (!result.next()) {
                feedback = "Personal details record for user #" + record.getID()
                        + " doesn't exist";
            }
            else {
                sql = "UPDATE personalRecords SET "
                        + "firstname = ?, "
                        + "lastname = ?, "
                        + "dateOfBirth = ?, "
                        + "address = ?, "
                        + "town = ?, "
                        + "county = ?, "
                        + "postcode = ?, "
                        + "phoneNb = ?, "
                        + "mobileNb = ?, "
                        + "emergencyContact = ?, "
                        + "emergencyContactNb = ? "
                        + "WHERE ID = ?";
                pS = conn.prepareStatement(sql);
                pS.setString(1, record.getFirstname());
                pS.setString(2, record.getLastname());
                pS.setString(3, record.getDateOfBirth());
                pS.setString(4, record.getAddress());
                pS.setString(5, record.getTown());
                pS.setString(6, record.getCounty());
                pS.setString(7, record.getPostcode());
                pS.setString(8, record.getPhoneNb());
                pS.setString(9, record.getMobileNb());
                pS.setString(10, record.getEmergencyContact());
                pS.setString(11, record.getEmergencyContactNb());
                pS.setInt(12, record.getID());
                pS.executeUpdate();
                feedback = "Amended Personal record for user #" + record.getID() + " in database";
                printTable("personalRecords");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: personalRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"personalRecords\" in database");
                feedback = amendPersonalRecord(record);
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                feedback = ex.getMessage();
            }
        } catch (NullPointerException ex) {
            feedback = "Input record is null";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return feedback;
    }
    
    /**
     * Gets Personal Details from the Database
     * @param ID
     * @return 
     */
    public PersonalDetailsRecord readPersonalRecord(int ID) 
    {
        Connection conn = null;
        PersonalDetailsRecord personalDetailsRecord = null;
        try {
            conn = connect();
            String sql = "SELECT * FROM personalRecords WHERE ID = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, ID);
            ResultSet result = pS.executeQuery();
            personalDetailsRecord = new PersonalDetailsRecord(
                    result.getInt("ID"), result.getString("firstname"), result.getString("lastname"), 
                    result.getString("dateOfBirth"), result.getString("address"), 
                    result.getString("town"), result.getString("county"), result.getString("postcode"), 
                    result.getString("phoneNb"), result.getString("mobileNb"), 
                    result.getString("emergencyContact"), result.getString("emergencyContactNb"));
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: personalRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"personalRecords\" in database");
                readPersonalRecord(ID);
            }
            else if (ex.getMessage().contains("ResultSet closed")) {
                System.out.println("Personal details record for user " + ID + " don't exist");
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        printTable("personalRecords");
        return personalDetailsRecord;
    }
    
    /**
     * Save a new annual review record in the database
     * @param record the record to save
     * @return 
     */
    public String createAnnualReviewRecord(AnnualReviewRecord record) 
    {
        String feedback = "Unexpected error (createAnnualReviewRecord database)";
        Connection conn = null;
        try {
            conn = connect();
                String sql = "INSERT INTO annualReviewRecords VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pS = conn.prepareStatement(sql);
                pS.setInt(2, record.getID());
                pS.setString(3, record.getName());
                pS.setInt(4, record.getReviewerID1());
                pS.setInt(5, record.getReviewerID2());
                pS.setString(6, record.getSection());
                pS.setString(7, record.getJobTitle());
                pS.setString(11, record.getReviewStatus());
                pS.setBoolean(12, record.isReviewer1Signed());
                pS.setBoolean(14, record.isReviewer2Signed());
                pS.setBoolean(16, record.isRevieweeSigned());
                pS.setString(18, record.getGoals());
                pS.setString(19, record.getObjectives());
                pS.setString(20, record.getAchievements());
                pS.executeUpdate();
                feedback = "Added Annual Review record for user #" + record.getID() + " in database";
                printTable("annualReviewRecords");
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: annualReviewRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"annualReviewRecords\" in database");
                feedback = createAnnualReviewRecord(record);
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NullPointerException ex) {
            feedback = "Input record is null";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return feedback;
    }
    
    /**
     * Amend a review record in the database
     * @param record
     * @return 
     */
    public String amendAnnualReviewRecord(AnnualReviewRecord record) 
    {
        String feedback = "Unexpected error (amendAnnualReviewRecord database)";
        Connection conn = null;
        try {
            conn = connect();
            String sql = "SELECT reviewID FROM annualReviewRecords WHERE reviewID = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, record.getReviewID());
            ResultSet result = pS.executeQuery();
            if (!result.next()) {
                feedback = "Review record " + record.getReviewID() + " doesn't exist";
            }
            else {
                sql = "UPDATE annualReviewRecords SET "
                        + "name = ?, "
                        + "section = ?, "
                        + "jobTitle = ?, "
                        + "performance = ?, "
                        + "comments = ?, "
                        + "recommendation = ?, "
                        + "reviewStatus = ?, "
                        + "reviewer1Signed = ?, "
                        + "dateReviewer1 = ?, "
                        + "reviewer2Signed = ?, "
                        + "dateReviewer2 = ?, "
                        + "revieweeSigned = ?, "
                        + "dateReviewee = ?, "
                        + "goals = ?, "
                        + "objectives = ?, "
                        + "achievements = ? WHERE reviewID = ?";
                pS = conn.prepareStatement(sql);
                pS.setString(1, record.getName());
                pS.setString(2, record.getSection());
                pS.setString(3, record.getJobTitle());
                pS.setString(4, record.getPerformance());
                pS.setString(5, record.getComments());
                pS.setString(6, record.getRecommendation());
                pS.setString(7, record.getReviewStatus());
                pS.setBoolean(8, record.isReviewer1Signed());
                pS.setString(9, record.getDateReviewer1());
                pS.setBoolean(10, record.isReviewer2Signed());
                pS.setString(11, record.getDateReviewer2());
                pS.setBoolean(12, record.isRevieweeSigned());
                pS.setString(13, record.getDateReviewee());
                pS.setString(14, record.getGoals());
                pS.setString(15, record.getObjectives());
                pS.setString(16, record.getAchievements());
                pS.setInt(17, record.getReviewID());
                pS.executeUpdate();
                feedback = "Amended Review record #" + record.getReviewID() + " in database";
                printTable("annualReviewRecords");
            }
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: annualReviewRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"annualReviewRecords\" in database");
                feedback = amendAnnualReviewRecord(record);
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NullPointerException ex) {
            feedback = "Input record is null";
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return feedback;
    }
    
    /**
     * Retrieve an Uncompleted review record from the database
     * @param reviewID
     * @return 
     */
    public AnnualReviewRecord readAnnualReviewRecord(int reviewID) 
    {
        Connection conn = null;
        AnnualReviewRecord annualReviewRecord = null;
        try {
            conn = connect();
            String sql = "SELECT * FROM annualReviewRecords WHERE reviewID = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, reviewID);
            ResultSet result = pS.executeQuery();
            annualReviewRecord = new AnnualReviewRecord(
                    result.getInt("reviewID"), 
                    result.getInt("staffID"), 
                    result.getString("name"), 
                    result.getInt("reviewerID1"), 
                    result.getInt("reviewerID2"), 
                    result.getString("section"), 
                    result.getString("jobTitle"), 
                    result.getString("performance"), 
                    result.getString("comments"), 
                    result.getString("recommendation"), 
                    result.getString("reviewStatus"), 
                    result.getBoolean("reviewer1Signed"), 
                    result.getString("dateReviewer1"),
                    result.getBoolean("reviewer2Signed"), 
                    result.getString("dateReviewer2"),
                    result.getBoolean("revieweeSigned"), 
                    result.getString("dateReviewee"),
                    result.getString("goals"), 
                    result.getString("objectives"), 
                    result.getString("achievements"));
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: annualReviewRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"annualReviewRecords\" in database");
                annualReviewRecord = readAnnualReviewRecord(reviewID);
            }
            else if (ex.getMessage().contains("ResultSet closed")) {
                System.out.println("Review record " + reviewID + " doesn't exist");
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        printTable("annualReviewRecords");
        return annualReviewRecord;
    }
    
    /**
     * Retrieve all the signed review record for a user from the database
     * @param userID
     * @return 
     */
    public ArrayList getPastReviewRecords(int userID)
    {
        ArrayList<AnnualReviewRecord> records = new ArrayList<>();
        Connection conn = null;
        try {
            conn = connect();
            String sql = "SELECT * FROM annualReviewRecords WHERE staffID = ? AND reviewStatus = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, userID);
            pS.setString(2, "Signed");
            ResultSet result = pS.executeQuery();
            while (result.next()) {
                AnnualReviewRecord annualReviewRecord = new AnnualReviewRecord(
                        result.getInt("reviewID"), 
                        result.getInt("staffID"), 
                        result.getString("name"), 
                        result.getInt("reviewerID1"), 
                        result.getInt("reviewerID2"), 
                        result.getString("section"), 
                        result.getString("jobTitle"), 
                        result.getString("performance"), 
                        result.getString("comments"), 
                        result.getString("recommendation"), 
                        result.getString("reviewStatus"), 
                        result.getBoolean("reviewer1Signed"), 
                        result.getString("dateReviewer1"),
                        result.getBoolean("reviewer2Signed"), 
                        result.getString("dateReviewer2"),
                        result.getBoolean("revieweeSigned"), 
                        result.getString("dateReviewee"),
                        result.getString("goals"), 
                        result.getString("objectives"), 
                        result.getString("achievements"));
                records.add(annualReviewRecord);
            }
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: annualReviewRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"annualReviewRecords\" in database");
                records = getPastReviewRecords(userID);
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return records;
    }
    
    /**
     * Check if a user is listed as either a reviewer or a reviewee in an uncompleted review
     * @param staffID
     * @return 
     */
    public HashMap checkIfInvolvedInReview(int staffID) 
    {
        Connection conn = null;
        HashMap<String, Integer> returnHash = new HashMap<>();
        try {
            conn = connect();
            String sql = "SELECT reviewID, staffID, reviewerID1, reviewerID2 "
                    + "FROM annualReviewRecords WHERE reviewStatus = 'Uncompleted'";
            PreparedStatement pS = conn.prepareStatement(sql);
            ResultSet result = pS.executeQuery();
            while (result.next()) {
                if (result.getInt("reviewerID1") == staffID) {
                    returnHash.put("Reviewer1", result.getInt("reviewID"));
                    return returnHash;
                }
                else if (result.getInt("reviewerID2") == staffID) {
                    returnHash.put("Reviewer2", result.getInt("reviewID"));
                    return returnHash;
                }
                else if (result.getInt("staffID") == staffID) {
                    returnHash.put("Reviewee", result.getInt("reviewID"));
                    return returnHash;
                }
            }
            returnHash.put("Not Involved", 0);
            return returnHash;
        } catch (SQLException ex) {
            if (ex.getMessage().contains("no such table: annualReviewRecords")) {
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
                createTable(sql);
                System.out.println("Created table \"annualReviewRecords\" in database");
                readPersonalRecord(staffID);
            }
            else {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        printTable("annualReviewRecords");
        return returnHash;
    }
    
    /**
     * Accesses Database and returns login details
     * @param username
     * @param password
     * @return 
     */
    public HashMap login(String username, String password) 
    {
        Connection conn = null;
        try {
            conn = connect();
            HashMap<String, String> userInfo = new HashMap<>();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT ID, username, password, "
                    + "authorisationLvl FROM loginDetails");
            while (result.next()) {
                if (result.getString("username").equals(username)) {
                    if (result.getString("password").equals(password)) {
                        ResultSetMetaData rsmd = result.getMetaData();
                        int columnsNumber = rsmd.getColumnCount();
                        for (int i = 1; i <= columnsNumber; i++) {
                            userInfo.put(rsmd.getColumnName(i), result.getString(i));
                        }
                        return userInfo;
                    }
                    else {
                        userInfo.put("Failed", "Incorrect password");
                        return userInfo;
                    }
                }
            }
            userInfo.put("Failed", "Username not found");
            return userInfo;
        } catch (SQLException ex) {
            Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
    
    /**
     * Print a table
     * @param tableName the table to print
     */
    public void printTable(String tableName) 
    {
        Connection conn = null;
        try {
            conn = connect();
            String sql = "SELECT * FROM " + tableName;
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            ResultSetMetaData rsmd = result.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            
            System.out.println("--------------------");
            for (int i = 1; i <= columnsNumber; i++) {
                if (i>1) System.out.print(" | ");
                System.out.print(rsmd.getColumnName(i));
            }
            System.out.println("");
            while (result.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i>1) System.out.print(" | ");
                    System.out.print(result.getString(i));
                }
                System.out.println("");
            }
            System.out.println("--------------------");
        } catch (SQLException ex) {
            Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Delete a row from loginDetails table
     * Used in test methods 
     * @param value
     */
    public void deleteLoginDetails(String value) 
    {
        Connection conn = null;
        try {
            conn = connect();
            String sql = "DELETE FROM loginDetails WHERE username = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setString(1, value);
            pS.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Delete row from personalDetails table
     * Used in test methods
     * @param value 
     */
    public void deletePersonalDetails(int value) 
    {
        Connection conn = null;
        try {
            conn = connect();
            String sql = "DELETE FROM personalRecords WHERE ID = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, value);
            pS.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Delete row from annualReview table
     * Used in test methods
     * @param value 
     */
    public void deleteAnnualReview(int value) 
    {
        Connection conn = null;
        try {
            conn = connect();
            String sql = "DELETE FROM annualReviewRecords WHERE staffID = ?";
            PreparedStatement pS = conn.prepareStatement(sql);
            pS.setInt(1, value);
            pS.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(HRDatabase.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}