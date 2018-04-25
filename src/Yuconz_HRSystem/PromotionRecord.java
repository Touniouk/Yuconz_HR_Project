package Yuconz_HRSystem;

/**
 *
 * @author tomwk
 */
public class PromotionRecord extends Record{
    
    private int reviewID;
    private String staffName;
    private String dateOfReview;
    private String dateToTakeEffect;
    private String currentGrade;
    private String newGrade;
    private int newManagerID;
    private String newDepartment;
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
     * @param currentGrade
     * @param newGrade
     * @param newManagerID
     * @param newDepartment
     * @param newSalary
     * @param reviewStatus
     * @param reviewer1Signed
     * @param reviewer2Signed
     * @param directorApproved
     * @param headOfPersonnelApproved 
     * 
     * inputs values into promotion records
     */
    
    public PromotionRecord(int staffID, int reviewID, String staffName, String dateOfReview, 
            String dateToTakeEffect, String currentGrade, String newGrade, int newManagerID, 
            String newDepartment, double newSalary, String reviewStatus, boolean reviewer1Signed, 
            boolean reviewer2Signed, boolean directorApproved, boolean headOfPersonnelApproved) {
        
        super(staffID);
        
        this.reviewID = reviewID;
        this.staffName = staffName;
        this.dateOfReview = dateOfReview;
        this.dateToTakeEffect = dateToTakeEffect;
        this.currentGrade = currentGrade;
        this.newGrade = newGrade;
        this.newManagerID = newManagerID;
        this.newDepartment = newDepartment;
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

    public String getCurrentGrade() {
        return currentGrade;
    }

    public void setCurrentGrade(String currentGrade) {
        this.currentGrade = currentGrade;
    }

    public String getNewGrade() {
        return newGrade;
    }

    public void setNewGrade(String newGrade) {
        this.newGrade = newGrade;
    }

    public int getNewManagerID() {
        return newManagerID;
    }

    public void setNewManagerID(int newManagerID) {
        this.newManagerID = newManagerID;
    }

    public String getNewDepartment() {
        return newDepartment;
    }

    public void setNewDepartment(String newDepartment) {
        this.newDepartment = newDepartment;
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
