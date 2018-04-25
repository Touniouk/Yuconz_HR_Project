package Yuconz_HRSystem;

/**
 * Stores the details to a Personal Details Record
 * 
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 1.1
 */
public class PersonalDetailsRecord extends Record
{
    private String firstname;
    private String lastname;
    private String dateOfBirth;
    private String address;
    private String town;
    private String county;
    private String postcode;
    private String phoneNb;
    private String mobileNb;
    private String emergencyContact;
    private String emergencyContactNb;

    /**
     * @param staffNb
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
     * 
     * inputs values into personal details record
     */
    public PersonalDetailsRecord(int staffNb, String firstname, String lastname, 
            String dateOfBirth, String address, String town, String county, String postcode, 
            String phoneNb, String mobileNb, String emergencyContact, String emergencyContactNb) 
    {
        
        super(staffNb);
        this.firstname = firstname;
        this.lastname = lastname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.town = town;
        this.county = county;
        this.postcode = postcode;
        this.phoneNb = phoneNb;
        this.mobileNb = mobileNb;
        this.emergencyContact = emergencyContact;
        this.emergencyContactNb = emergencyContactNb;
    }

    /**
     * @return
     */
    public String getFirstname() 
    {
        return firstname;
    }
    
    /**
     * @param firstname 
     * Sets the first name
     */
    public void setFirstname(String firstname) 
    {
        this.firstname = firstname;
    }

    /**
     * @return 
     */
    public String getLastname() 
    {
        return lastname;
    }

    /**
     * @param lastname 
     * Sets the last name
     */
    public void setLastname(String lastname) 
    {
        this.lastname = lastname;
    }

    /**
     * @return 
     */
    public String getDateOfBirth() 
    {
        return dateOfBirth;
    }

    /**
     * @param dateOfBirth
     * Sets the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) 
    {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * @return 
     */
    public String getAddress() 
    {
        return address;
    }

    /**
     * @param address 
     * Sets the address
     */
    public void setAddress(String address) 
    {
        this.address = address;
    }

    /**
     * @return 
     */
    public String getTown() 
    {
        return town;
    }

    /**
     * @param town 
     * Sets the town
     */
    public void setTown(String town) 
    {
        this.town = town;
    }

    /**
     * @return 
     */
    public String getCounty() 
    {
        return county;
    }

    /**
     * @param county 
     * Sets the county
     */
    public void setCounty(String county) 
    {
        this.county = county;
    }

    /**
     * @return 
     */
    public String getPostcode() 
    {
        return postcode;
    }

    /**
     * @param postcode 
     * Sets the postcode
     */
    public void setPostcode(String postcode) 
    {
        this.postcode = postcode;
    }

    /**
     * @return 
     */
    public String getPhoneNb() 
    {
        return phoneNb;
    }

    /**
     * @param phoneNb 
     * Sets the phone number
     */
    public void setPhoneNb(String phoneNb) 
    {
        this.phoneNb = phoneNb;
    }

    /**
     * @return 
     */
    public String getMobileNb() 
    {
        return mobileNb;
    }

    /**
     * @param mobileNb 
     * Sets the mobile number
     */
    public void setMobileNb(String mobileNb) 
    {
        this.mobileNb = mobileNb;
    }

    /**
     * @return 
     */
    public String getEmergencyContact() 
    {
        return emergencyContact;
    }

    /**
     * @param emergencyContact 
     * Sets the emergency contact
     */
    public void setEmergencyContact(String emergencyContact) 
    {
        this.emergencyContact = emergencyContact;
    }

    /**
     * @return 
     */
    public String getEmergencyContactNb() 
    {
        return emergencyContactNb;
    }

    /**
     * @param emergencyContactNb 
     * Sets the emergency contact number
     */
    public void setEmergencyContactNb(String emergencyContactNb) 
    {
        this.emergencyContactNb = emergencyContactNb;
    }
}