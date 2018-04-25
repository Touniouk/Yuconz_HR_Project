package Yuconz_HRSystem;

/**
 * Creates login details for four test users with different authorisation levels
 * The main Class
 * 
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 1.2
 */
public class Main {
    
    private Main() {}
    
    public static void main(String[] args) {
        
        //Create a database if it doesn't exist yet and add test login details
        HRDatabase database = new HRDatabase();
        
        database.addLoginDetail("John","123","HREmployee");
        database.addLoginDetail("Bob","123","Employee");
        database.addLoginDetail("Tom","123","Director");
        database.addLoginDetail("Pat","123","Manager");
        database.printTable("loginDetails");
        
        //Create login GUI
       new GUILogin();
    }
}