package Yuconz_HRSystem;


/**
 * An instance of the User class
 * 
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 1.2
 */
public class User 
{
    
    private final String username;
    private final String password;
    private final String authorisationLvl;
    private final int userID;
    
    /**
     * @param userID
     * @param username
     * @param password
     * @param authorisationLvl 
     */
    public User(int userID, String username, String password, String authorisationLvl) 
    {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.authorisationLvl = authorisationLvl;
    }
    
    /**
     * @return
     */
    public String getAuthorisationLvl() 
    {
        return authorisationLvl;
    }
    
    /**
     * @return
     */
    public int getID() 
    {
        return userID;
    }
    
    /**
     * @return
     */
    public String getUsername() 
    {
        return username;
    }
}