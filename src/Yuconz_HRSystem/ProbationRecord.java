package Yuconz_HRSystem;

/**
 *
 * @author tomwk
 */
public class ProbationRecord extends Record{
    
    private int reviewID;
    private String staffName;
    private String dateOfReview;
    private boolean warningLetterIssued;
    private String dateIssued;
    private String dateOfNextReview;
    private String probationObjectives;
    private String reviewStatus;
    private boolean reviewer1Signed;
    private boolean reviewer2Signed;
    private boolean directorApproved;
    private boolean headOfPersonnelApproved;

    public ProbationRecord(int staffID, int reviewID, String staffName, String dateOfReview, 
            boolean warningLetterIssued, String dateIssued, String dateOfNextReview, 
            String probationObjectives, String reviewStatus, boolean reviewer1Signed, 
            boolean reviewer2Signed, boolean directorApproved, boolean headOfPersonnelApproved) {
        
        super(staffID);
        
        this.reviewID = reviewID;
        this.staffName = staffName;
        this.dateOfReview = dateOfReview;
        this.warningLetterIssued = warningLetterIssued;
        this.dateIssued = dateIssued;
        this.dateOfNextReview = dateOfNextReview;
        this.probationObjectives = probationObjectives;
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

    public boolean isWarningLetterIssued() {
        return warningLetterIssued;
    }

    public void setWarningLetterIssued(boolean warningLetterIssued) {
        this.warningLetterIssued = warningLetterIssued;
    }

    public String getDateIssued() {
        return dateIssued;
    }

    public void setDateIssued(String dateIssued) {
        this.dateIssued = dateIssued;
    }

    public String getDateOfNextReview() {
        return dateOfNextReview;
    }

    public void setDateOfNextReview(String dateOfNextReview) {
        this.dateOfNextReview = dateOfNextReview;
    }

    public String getProbationObjectives() {
        return probationObjectives;
    }

    public void setProbationObjectives(String probationObjectives) {
        this.probationObjectives = probationObjectives;
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
