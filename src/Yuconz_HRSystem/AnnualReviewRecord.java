package Yuconz_HRSystem;

/**
 * Stores the details to an Annual Review Record
 *
 * @author tomwk, Nagendra, Luc, Reehan
 */
public class AnnualReviewRecord extends Record
{
    private int reviewID;
    private String name;
    private int reviewerID1;
    private int reviewerID2;
    private String section;
    private String jobTitle;
    private String performance;
    private String comments;
    private String recommendation;
    private String reviewStatus;
    private boolean reviewer1Signed;
    private String dateReviewer1;
    private boolean reviewer2Signed;
    private String dateReviewer2;
    private boolean revieweeSigned;
    private String dateReviewee;
    private String goals;
    private String objectives;
    private String achievements;

    /**
     * @param staffID
     * @param name
     * @param section
     * @param jobTitle
     * @param reviewerID1
     * @param reviewerID2
     * @param achievements
     */
    public AnnualReviewRecord(
            int staffID, String name, String section, String jobTitle, 
            int reviewerID1, int reviewerID2, String achievements) 
    {
        super(staffID);
        this.name = name;
        this.section = section;
        this.jobTitle = jobTitle;
        this.reviewStatus = "Uncompleted";
        this.reviewerID1 = reviewerID1;
        this.reviewerID2 = reviewerID2;
        this.reviewer1Signed = false;
        this.reviewer2Signed = false;
        this.revieweeSigned = false;
        this.achievements = achievements;
    }
    
    public AnnualReviewRecord(int reviewID, int ID, String name, int reviewerID1, int reviewerID2, String section, 
            String jobTitle, String performance, String comments, String recommendation, String reviewStatus, 
            boolean reviewer1Signed, String dateReviewer1, boolean reviewer2Signed, String dateReviewer2, 
            boolean revieweeSigned, String dateReviewee, String goals, String objectives, String achievements) 
    {
        super(ID);
        this.reviewID = reviewID;
        this.name = name;
        this.reviewerID1 = reviewerID1;
        this.reviewerID2 = reviewerID2;
        this.section = section;
        this.jobTitle = jobTitle;
        this.performance = performance;
        this.comments = comments;
        this.recommendation = recommendation;
        this.reviewStatus = reviewStatus;
        this.reviewer1Signed = reviewer1Signed;
        this.dateReviewer1 = dateReviewer1;
        this.reviewer2Signed = reviewer2Signed;
        this.dateReviewer2 = dateReviewer2;
        this.revieweeSigned = revieweeSigned;
        this.dateReviewee = dateReviewee;
        this.goals = goals;
        this.objectives = objectives;
        this.achievements = achievements;
    }

    /**
     * Get the record review ID
     * @return 
     */
    public int getReviewID() 
    {
        return reviewID;
    }

    /**
     * Set the record review ID to the given value
     * @param reviewID 
     */
    public void setReviewID(int reviewID) 
    {
        this.reviewID = reviewID;
    }

    /**
     * Get the record name
     * @return 
     */
    public String getName() 
    {
        return name;
    }

    /**
     * Set the record name
     * @param name 
     */
    public void setName(String name) 
    {
        this.name = name;
    }

    /**
     * Get the record reviewer one ID
     * @return 
     */
    public int getReviewerID1() 
    {
        return reviewerID1;
    }

    /**
     * Set the record reviewer one ID
     * @param reviewerID1 
     */
    public void setReviewerID1(int reviewerID1) 
    {
        this.reviewerID1 = reviewerID1;
    }

    /**
     * Get the record reviewer two ID
     * @return 
     */
    public int getReviewerID2() 
    {
        return reviewerID2;
    }

    /**
     * Set the record reviewer two ID
     * @param reviewerID2 
     */
    public void setReviewerID2(int reviewerID2) 
    {
        this.reviewerID2 = reviewerID2;
    }

    /**
     * Get the record section
     * @return 
     */
    public String getSection() 
    {
        return section;
    }

    /**
     * Set the record section
     * @param section 
     */
    public void setSection(String section) 
    {
        this.section = section;
    }

    /**
     * Get the record job title
     * @return 
     */
    public String getJobTitle() 
    {
        return jobTitle;
    }

    /**
     * Set the record job title
     * @param jobTitle 
     */
    public void setJobTitle(String jobTitle) 
    {
        this.jobTitle = jobTitle;
    }

    /**
     * Get the record performance
     * @return 
     */
    public String getPerformance() 
    {
        return performance;
    }

    /**
     * Set record performance
     * @param performance 
     */
    public void setPerformance(String performance) 
    {
        this.performance = performance;
    }

    /**
     * Get record comments
     * @return 
     */
    public String getComments() 
    {
        return comments;
    }

    /**
     * Set record comments
     * @param comments 
     */
    public void setComments(String comments) 
    {
        this.comments = comments;
    }

    /**
     * Get record recommendation
     * @return 
     */
    public String getRecommendation() 
    {
        return recommendation;
    }

    /**
     * Set record recommendation
     * @param recommendation 
     */
    public void setRecommendation(String recommendation) 
    {
        this.recommendation = recommendation;
    }

    /**
     * Get record status
     * @return 
     */
    public String getReviewStatus() 
    {
        return reviewStatus;
    }

    /**
     * Set record status
     * @param reviewStatus 
     */
    public void setReviewStatus(String reviewStatus) 
    {
        this.reviewStatus = reviewStatus;
    }

    /**
     * Check if reviewer 1 has signed the record
     * @return 
     */
    public boolean isReviewer1Signed() 
    {
        return reviewer1Signed;
    }

    /**
     * Set the signed value for reviewer 1
     * @param reviewer1Signed 
     */
    public void setReviewer1Signed(boolean reviewer1Signed) 
    {
        this.reviewer1Signed = reviewer1Signed;
    }

    /**
     * Get the date at which reviewer 1 signed the record
     * @return 
     */
    public String getDateReviewer1() 
    {
        return dateReviewer1;
    }

    /**
     * Set the date at which reviewer 1 signed the record
     * @param dateReviewer1 
     */
    public void setDateReviewer1(String dateReviewer1) 
    {
        this.dateReviewer1 = dateReviewer1;
    }

    /**
     * Check if reviewer 2 has signed the record
     * @return 
     */
    public boolean isReviewer2Signed() 
    {
        return reviewer2Signed;
    }

    /**
     * Set the signed value for reviewer 2
     * @param reviewer2Signed 
     */
    public void setReviewer2Signed(boolean reviewer2Signed) 
    {
        this.reviewer2Signed = reviewer2Signed;
    }

    /**
     * Get the date at which reviewer 2 signed the record
     * @return 
     */
    public String getDateReviewer2() 
    {
        return dateReviewer2;
    }

    /**
     * Set the date at which reviewer 2 signed the record
     * @param dateReviewer2 
     */
    public void setDateReviewer2(String dateReviewer2) 
    {
        this.dateReviewer2 = dateReviewer2;
    }

    /**
     * Check if reviewee has signed the record
     * @return 
     */
    public boolean isRevieweeSigned() 
    {
        return revieweeSigned;
    }

    /**
     * Set the signed value for reviewer 2
     * @param revieweeSigned 
     */
    public void setRevieweeSigned(boolean revieweeSigned) 
    {
        this.revieweeSigned = revieweeSigned;
    }

    /**
     * Get the date at which reviewee signed the record
     * @return 
     */
    public String getDateReviewee() 
    {
        return dateReviewee;
    }

    /**
     * Set the date at which reviewee signed the record
     * @param dateReviewee 
     */
    public void setDateReviewee(String dateReviewee) 
    {
        this.dateReviewee = dateReviewee;
    }

    /**
     * Get the record goals
     * @return 
     */
    public String getGoals() 
    {
        return goals;
    }

    /**
     * Set the record goals
     * @param goals 
     */
    public void setGoals(String goals) 
    {
        this.goals = goals;
    }

    /**
     * Get the record objectives
     * @return 
     */
    public String getObjectives() 
    {
        return objectives;
    }

    /**
     * Set the record objectives
     * @param objectives 
     */
    public void setObjectives(String objectives) 
    {
        this.objectives = objectives;
    }

    /**
     * Get the record achievements
     * @return 
     */
    public String getAchievements() 
    {
        return achievements;
    }

    /**
     * Set the record achievements
     * @param achievements 
     */
    public void setAchievements(String achievements) 
    {
        this.achievements = achievements;
    }
}