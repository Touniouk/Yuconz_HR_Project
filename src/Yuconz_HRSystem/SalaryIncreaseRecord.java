package Yuconz_HRSystem;

/**
 *
 * @author tomwk
 */
public class SalaryIncreaseRecord extends Record{
   
    private int reviewID;
    private String staffName;
    private String dateOfReview;
    private String dateToTakeEffect;
    private double currentSalary;
    private double newSalary;
    private String reviewStatus;
    private boolean reviewer1Signed;
    private boolean reviewer2Signed;
    private boolean directorApproved;
    private boolean headOfPersonnelApproved;
    
    /**
     * @param staffID
     * @param reviewID
     * @param staffName
     * @param dateOfReview
     * @param dateToTakeEffect
     * @param currentSalary
     * @param newSalary
     * @param reviewStatus
     * @param reviewer1Signed
     * @param reviewer2Signed
     * @param directorApproved
     * @param headOfPersonnelApproved 
     * 
     * inputs values into salary increase records
     */
    public SalaryIncreaseRecord(int staffID, int reviewID, String staffName, String dateOfReview,
            String dateToTakeEffect, double currentSalary, double newSalary, 
            String reviewStatus, boolean reviewer1Signed, boolean reviewer2Signed, 
            boolean directorApproved, boolean headOfPersonnelApproved) {
        
        super(staffID);
        
        this.reviewID = reviewID;
        this.staffName = staffName;
        this.dateOfReview = dateOfReview;
        this.dateToTakeEffect = dateToTakeEffect;
        this.currentSalary = currentSalary;
        this.newSalary = newSalary;
        this.reviewStatus = reviewStatus;
        this.reviewer1Signed = reviewer1Signed;
        this.reviewer2Signed = reviewer2Signed;
        this.directorApproved = directorApproved;
        this.headOfPersonnelApproved = headOfPersonnelApproved;
    }
    
    
    public int getReviewID() {
        return reviewID;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getDateOfReview() {
        return dateOfReview;
    }

    public void setDateOfReview(String dateOfReview) {
        this.dateOfReview = dateOfReview;
    }

    public String getDateToTakeEffect() {
        return dateToTakeEffect;
    }

    public void setDateToTakeEffect(String dateToTakeEffect) {
        this.dateToTakeEffect = dateToTakeEffect;
    }

    public double getCurrentSalary() {
        return currentSalary;
    }

    public void setCurrentSalary(double currentSalary) {
        this.currentSalary = currentSalary;
    }

    public double getNewSalary() {
        return newSalary;
    }

    public void setNewSalary(double newSalary) {
        this.newSalary = newSalary;
    }

    public String getReviewStatus() {
        return reviewStatus;
    }

    public void setReviewStatus(String reviewStatus) {
        this.reviewStatus = reviewStatus;
    }

    public boolean isReviewer1Signed() {
        return reviewer1Signed;
    }

    public void setReviewer1Signed(boolean reviewer1Signed) {
        this.reviewer1Signed = reviewer1Signed;
    }

    public boolean isReviewer2Signed() {
        return reviewer2Signed;
    }

    public void setReviewer2Signed(boolean reviewer2Signed) {
        this.reviewer2Signed = reviewer2Signed;
    }

    public boolean isDirectorApproved() {
        return directorApproved;
    }

    public void setDirectorApproved(boolean directorApproved) {
        this.directorApproved = directorApproved;
    }

    public boolean isHeadOfPersonnelApproved() {
        return headOfPersonnelApproved;
    }

    public void setHeadOfPersonnelApproved(boolean headOfPersonnelApproved) {
        this.headOfPersonnelApproved = headOfPersonnelApproved;
    }
}
