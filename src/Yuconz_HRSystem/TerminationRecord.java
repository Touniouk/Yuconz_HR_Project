package Yuconz_HRSystem;

/**
 *
 * @author tomwk
 */
public class TerminationRecord extends Record{

    private int reviewID;
    private String staffName;
    private String dateOfReview;
    private boolean twoWarnings;
    private boolean threeReviews;
    private boolean grossMisconduct;
    private String dateToTakeEffect;
    private String conditions;
    private String reviewStatus;
    private boolean reviewer1Signed;
    private boolean reviewer2Signed;
    private boolean directorApproved;
    private boolean headOfPersonnelApproved;
    
    public TerminationRecord(int staffID, int reviewID, String staffName, String dateOfReview, 
            boolean twoWarnings, boolean threeReviews, boolean grossMisconduct, 
            String dateToTakeEffect, String conditions, String reviewStatus, 
            boolean reviewer1Signed, boolean reviewer2Signed, boolean directorApproved, 
            boolean headOfPersonnelApproved) {
        
        super(staffID);
        
        this.reviewID = reviewID;
        this.staffName = staffName;
        this.dateOfReview = dateOfReview;
        this.twoWarnings = twoWarnings;
        this.threeReviews = threeReviews;
        this.grossMisconduct = grossMisconduct;
        this.dateToTakeEffect = dateToTakeEffect;
        this.conditions = conditions;
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

    public boolean isTwoWarnings() {
        return twoWarnings;
    }

    public void setTwoWarnings(boolean twoWarnings) {
        this.twoWarnings = twoWarnings;
    }

    public boolean isThreeReviews() {
        return threeReviews;
    }

    public void setThreeReviews(boolean threeReviews) {
        this.threeReviews = threeReviews;
    }

    public boolean isGrossMisconduct() {
        return grossMisconduct;
    }

    public void setGrossMisconduct(boolean grossMisconduct) {
        this.grossMisconduct = grossMisconduct;
    }

    public String getDateToTakeEffect() {
        return dateToTakeEffect;
    }

    public void setDateToTakeEffect(String dateToTakeEffect) {
        this.dateToTakeEffect = dateToTakeEffect;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
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
