package Yuconz_HRSystem;

/**
 * A superclass for the different record type
 * 
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 1.3
 */
public class Record
{
    private int ID;
    
    /**
     * Initialises ID
     * @param ID 
     */
    public Record(int ID) 
    {
        this.ID = ID;
    }

    /**
     * @return 
     */
    public int getID() 
    {
        return ID;
    }

    /**
     * @param ID 
     * Sets user ID
     */
    public void setID(int ID) 
    {
        this.ID = ID;
    }
}
