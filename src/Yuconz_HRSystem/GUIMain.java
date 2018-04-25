package Yuconz_HRSystem;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * The User Interface for the main window
 *
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 2.5
 */
public class GUIMain extends javax.swing.JFrame 
{

    private AnnualReviewRecord currentReview;
    private final SystemController sC;

    /**
     * Creates new form GUIMain
     *
     * @param sC
     */
    public GUIMain(SystemController sC) 
    {
        this.sC = sC;
        currentReview = null;
        initComponents();
        checkReviews();
        checkAuthorisation();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);
        setVisible(true);
    }

    /**
     * Setup the GUI differently depending on the user's authorisation level
     */
    private void checkAuthorisation() 
    {
        switch (sC.getCurrentUser().getAuthorisationLvl()) {
            case "Employee":
                //Personal details tab
                IDTextField.setEnabled(false);
                try {
                    PersonalDetailsRecord record = sC.getPersonalDetailsRecord(sC.getCurrentUser().getID());
                    IDTextField.setText(Integer.toString(sC.getCurrentUser().getID()));
                    firstnameTextField.setText(record.getFirstname());
                    lastnameTextField.setText(record.getLastname());
                    dobTextField.setText(record.getDateOfBirth());
                    addressTextField.setText(record.getAddress());
                    townTextField.setText(record.getTown());
                    countyTextField.setText(record.getCounty());
                    postcodeTextField.setText(record.getPostcode());
                    phoneTextField.setText(record.getPhoneNb());
                    mobileTextField.setText(record.getMobileNb());
                    emergencyTextField.setText(record.getEmergencyContact());
                    emergencyNbTextField.setText(record.getEmergencyContactNb());
                } catch (NullPointerException ex) {
                    redLabel.setText("Personal detail record for user "
                            + IDTextField.getText() + " doesn't exist in database");
                    redLabel.setVisible(true);
                }
                //Annual Review Tab
                jTabbedPane3.addTab("Past Reviews", createPastReviewsPanel(jTabbedPane3,
                        sC.getPastReviewRecords(sC.getCurrentUser().getID())));
                break;
            case "Manager":
                //Personal details Tab
                IDTextField.setEnabled(false);
                try {
                    PersonalDetailsRecord record = sC.getPersonalDetailsRecord(sC.getCurrentUser().getID());
                    IDTextField.setText(Integer.toString(sC.getCurrentUser().getID()));
                    firstnameTextField.setText(record.getFirstname());
                    lastnameTextField.setText(record.getLastname());
                    dobTextField.setText(record.getDateOfBirth());
                    addressTextField.setText(record.getAddress());
                    townTextField.setText(record.getTown());
                    countyTextField.setText(record.getCounty());
                    postcodeTextField.setText(record.getPostcode());
                    phoneTextField.setText(record.getPhoneNb());
                    mobileTextField.setText(record.getMobileNb());
                    emergencyTextField.setText(record.getEmergencyContact());
                    emergencyNbTextField.setText(record.getEmergencyContactNb());
                } catch (NullPointerException ex) {
                    redLabel.setText("Personal detail record for user "
                            + IDTextField.getText() + " doesn't exist in database");
                    redLabel.setVisible(true);
                }
                //Annual Review Tab
                jTabbedPane3.addTab("Past Reviews", createPastReviewsPanel(jTabbedPane3,
                        sC.getPastReviewRecords(sC.getCurrentUser().getID())));
                break;
            case "Director":
                //Annual Review Tab
                JPanel panel = new JPanel();
                JTextField textField = new JTextField("ID");
                JButton button = new JButton("Get");
                button.addActionListener(e -> {
                    if (jTabbedPane3.indexOfTab("Past Reviews") != -1) {
                        jTabbedPane3.remove(jTabbedPane3.indexOfTab("Past Reviews"));
                    }
                    jTabbedPane3.addTab("Past Reviews", createPastReviewsPanel(jTabbedPane3,
                            sC.getPastReviewRecords(Integer.valueOf(textField.getText()))));
                });
                panel.add(textField);
                panel.add(button);
                jTabbedPane3.addTab("ID", panel);
                break;
            case "HREmployee":
                //Annual Review Tab
                JPanel panel2 = new JPanel();
                JTextField textField2 = new JTextField("ID");
                JButton button2 = new JButton("Get");
                button2.addActionListener(e -> {
                    if (jTabbedPane3.indexOfTab("Past Reviews") != -1) {
                        jTabbedPane3.remove(jTabbedPane3.indexOfTab("Past Reviews"));
                    }
                    jTabbedPane3.addTab("Past Reviews", createPastReviewsPanel(jTabbedPane3,
                            sC.getPastReviewRecords(Integer.valueOf(textField2.getText()))));
                });
                panel2.add(textField2);
                panel2.add(button2);
                jTabbedPane3.addTab("ID", panel2);
                break;
        }
    }

    /**
     * Check if user is involved in any uncompleted reviews
     */
    private void checkReviews() 
    {
        jTabbedPane1.remove(jTabbedPane2);
        if (jTabbedPane2.getTabCount() > 1) {
            jTabbedPane2.remove(1);
        }
        HashMap<String, Integer> hashMap = sC.checkIfInvolvedInReview(sC.getCurrentUser().getID());
        if (hashMap.containsKey("Reviewee")) {
            currentReview = sC.getAnnualReviewRecord(hashMap.get("Reviewee"));
            nameTextField2.setText(currentReview.getName());
            reviewer1TextField2.setText(Integer.toString(currentReview.getReviewerID1()));
            reviewer2TextField2.setText(Integer.toString(currentReview.getReviewerID2()));
            sectionTextField2.setText(currentReview.getSection());
            jobTitleTextField2.setText(currentReview.getJobTitle());
            objectivesTextArea.setText(currentReview.getAchievements());
            performanceSummaryText.setText(currentReview.getPerformance());
            goalTextArea.setText(currentReview.getGoals());
            reviewerCommentsText.setText(currentReview.getComments());
            recomendationBox.setSelectedItem(currentReview.getRecommendation());
            recomendationBox.setEnabled(false);
            ArrayList<AnnualReviewRecord> records = sC.getPastReviewRecords(currentReview.getID());
            jTabbedPane2.addTab("Past Reviews", createPastReviewsPanel(jTabbedPane2, records));
            jTabbedPane1.insertTab("Current Review", null, jTabbedPane2, null, 2);
            disableAnnualReviewFields();
        } else if (hashMap.containsKey("Reviewer1")) {
            currentReview = sC.getAnnualReviewRecord(hashMap.get("Reviewer1"));
            nameTextField2.setText(currentReview.getName());
            reviewer1TextField2.setText(Integer.toString(currentReview.getReviewerID1()));
            reviewer2TextField2.setText(Integer.toString(currentReview.getReviewerID2()));
            sectionTextField2.setText(currentReview.getSection());
            jobTitleTextField2.setText(currentReview.getJobTitle());
            objectivesTextArea.setText(currentReview.getAchievements());
            performanceSummaryText.setText(currentReview.getPerformance());
            goalTextArea.setText(currentReview.getGoals());
            reviewerCommentsText.setText(currentReview.getComments());
            recomendationBox.setSelectedItem(currentReview.getRecommendation());
            ArrayList<AnnualReviewRecord> records = sC.getPastReviewRecords(currentReview.getID());
            jTabbedPane2.addTab("Past Reviews", createPastReviewsPanel(jTabbedPane2, records));
            jTabbedPane1.insertTab("Current Review", null, jTabbedPane2, null, 2);
            if (sC.getCurrentUser().getAuthorisationLvl().equals("Employee")) {
                performanceSummaryText.setEnabled(false);
                goalTextArea.setEnabled(false);
                reviewerCommentsText.setEnabled(false);
                recomendationBox.setEnabled(false);
                signCheckBox.setEnabled(false);
                dateSignedField.setEnabled(false);
                submitButton2.setEnabled(false);
            }
        } else if (hashMap.containsKey("Reviewer2")) {
            currentReview = sC.getAnnualReviewRecord(hashMap.get("Reviewer2"));
            nameTextField2.setText(currentReview.getName());
            reviewer1TextField2.setText(Integer.toString(currentReview.getReviewerID1()));
            reviewer2TextField2.setText(Integer.toString(currentReview.getReviewerID2()));
            sectionTextField2.setText(currentReview.getSection());
            jobTitleTextField2.setText(currentReview.getJobTitle());
            objectivesTextArea.setText(currentReview.getAchievements());
            performanceSummaryText.setText(currentReview.getPerformance());
            goalTextArea.setText(currentReview.getGoals());
            reviewerCommentsText.setText(currentReview.getComments());
            recomendationBox.setSelectedItem(currentReview.getRecommendation());
            ArrayList<AnnualReviewRecord> records = sC.getPastReviewRecords(currentReview.getID());
            jTabbedPane2.addTab("Past Reviews", createPastReviewsPanel(jTabbedPane2, records));
            jTabbedPane1.insertTab("Current Review", null, jTabbedPane2, null, 2);
            if (sC.getCurrentUser().getAuthorisationLvl().equals("Employee")) {
                performanceSummaryText.setEnabled(false);
                goalTextArea.setEnabled(false);
                reviewerCommentsText.setEnabled(false);
                recomendationBox.setEnabled(false);
                signCheckBox.setEnabled(false);
                dateSignedField.setEnabled(false);
                submitButton2.setEnabled(false);
            }
        }
    }

    /**
     * A panel that displays a bunch of button corresponding to past reviews 
     * Clicking on a button opens the corresponding past review in a new closeable tab
     * @param tabbedPane the tabbed pane in which to add the panel
     * @param records the list of records to create the buttons
     * @return
     */
    private JPanel createPastReviewsPanel(JTabbedPane tabbedPane, ArrayList<AnnualReviewRecord> records) 
    {
        //Create a panel for the new tab
        JPanel panel = new JPanel();
        //Create a button for every record
        records.forEach((record) -> {
            JButton button = new JButton(record.getDateReviewee());
            panel.add(button);
            //Clicking this button opens a past review in a new closeable tab
            button.addActionListener(e -> {
                //Disable button so that you can't open the same review multiple times
                button.setEnabled(false);
                //Panel for the new tab containing the review
                JPanel panel2 = new JPanel();
                String title = "Review " + record.getDateReviewee();
                //Create the actual GUI to display the review
                JScrollPane pane = createOpenedReviewPanel(panel2, record);
                //Add it to the tabbedpane
                tabbedPane.addTab(title, pane);
                //Panel to display the tab title
                //It's a combination of a label and a button
                JPanel panel3 = new JPanel();
                panel3.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
                JLabel label = new JLabel(title);
                JButton button2 = new JButton("x");
                panel3.add(label);
                panel3.add(button2);
                //add more space between the label and the button
                label.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
                //add more space to the top of the component
                panel3.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
                //Make the button look better
                button2.setPreferredSize(new Dimension(17, 17));
                button2.setToolTipText("Close this tab");
                //Make it transparent
                button2.setContentAreaFilled(false);
                //No need to be focusable
                button2.setFocusable(false);
                button2.setBorder(BorderFactory.createEtchedBorder());
                button2.setBorderPainted(false);
                button2.setRolloverEnabled(true);
                //Add an actionlistener to the button to close the tab
                button2.addActionListener(e2 -> {
                    tabbedPane.remove(tabbedPane.indexOfTab(title));
                    //Re enable the button to open the review that just closed
                    button.setEnabled(true);
                });
                panel3.setOpaque(false);
                tabbedPane.setTabComponentAt(tabbedPane.indexOfTab(title), panel3);
            });
        });
        return panel;
    }

    /**
     * clears text fields for Personal Details
     */
    private void clearText() 
    {
        firstnameTextField.setText("");
        lastnameTextField.setText("");
        dobTextField.setText("");
        addressTextField.setText("");
        townTextField.setText("");
        countyTextField.setText("");
        postcodeTextField.setText("");
        phoneTextField.setText("");
        mobileTextField.setText("");
        emergencyTextField.setText("");
        emergencyNbTextField.setText("");
    }

    /**
     * Disable fields in annual review
     */
    private void disableAnnualReviewFields() 
    {
        nameTextField1.setEnabled(false);
        sectionBox1.setEnabled(false);
        jobTitleTextField1.setEnabled(false);
        reviewer1TextField1.setEnabled(false);
        reviewer2TextField1.setEnabled(false);
        achievementsTextArea.setEnabled(false);
        submitButton1.setEnabled(false);
    }

    /**
     * Set up a panel to display a review record 
     * This is basically the code generated by the GUI builder but with local variables
     * @param panel the panel to "modify"
     * @param record the record to display
     * @return
     */
    private JScrollPane createOpenedReviewPanel(JPanel panel, AnnualReviewRecord record) 
    {
        JLabel label1 = new JLabel();
        label1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        label1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label1.setText("Current Review");

        JLabel label2 = new JLabel();
        label2.setText("Name:");

        JLabel label3 = new JLabel();
        label3.setText("Manager/Director ID:");

        JLabel label4 = new JLabel();
        label4.setText("2nd Manager/Director ID:");

        JLabel label5 = new JLabel();
        label5.setText("Section:");

        JLabel label6 = new JLabel();
        label6.setText("Job Title:");

        JTextField textField3 = new JTextField();
        textField3.setText(record.getName());
        textField3.setEnabled(false);

        JTextField textField2 = new JTextField();
        textField2.setText(Integer.toString(record.getReviewerID1()));
        textField2.setEnabled(false);

        JTextField textField1 = new JTextField();
        textField1.setText(Integer.toString(record.getReviewerID2()));
        textField1.setEnabled(false);

        JTextField textField4 = new JTextField();
        textField4.setText(record.getSection());
        textField4.setEnabled(false);

        JTextField textField5 = new JTextField();
        textField5.setText(record.getJobTitle());
        textField5.setEnabled(false);

        JLabel label7 = new JLabel();
        label7.setText("A review of past performance: achievements and outcomes");

        JLabel label8 = new JLabel();
        label8.setText("No:");

        JLabel label9 = new JLabel();
        label9.setText("Objectives:");

        JLabel label10 = new JLabel();
        label10.setText("Achievement:");

        JTextArea textArea1 = new JTextArea();
        textArea1.setColumns(20);
        textArea1.setRows(5);
        textArea1.setText(record.getAchievements());
        textArea1.setEnabled(false);
        JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setViewportView(textArea1);

        JLabel label11 = new JLabel();
        label11.setText("Performance Summary:");

        JTextArea textArea2 = new JTextArea();
        textArea2.setColumns(20);
        textArea2.setRows(5);
        textArea2.setText(record.getPerformance());
        textArea2.setEnabled(false);
        JScrollPane scrollPane2 = new JScrollPane();
        scrollPane2.setViewportView(textArea2);

        JLabel label12 = new JLabel();
        label12.setText("A preview of future performance : goals/planned outcomes");

        JTextArea textArea3 = new JTextArea();
        textArea3.setColumns(20);
        textArea3.setRows(5);
        textArea3.setText(record.getGoals());
        textArea3.setEnabled(false);
        JScrollPane scrollPane3 = new JScrollPane();
        scrollPane3.setViewportView(textArea3);

        JLabel label13 = new JLabel();
        label13.setText("Reviewer Comments:");

        JTextArea textArea4 = new JTextArea();
        textArea4.setColumns(20);
        textArea4.setRows(5);
        textArea4.setText(record.getComments());
        textArea4.setEnabled(false);
        JScrollPane scrollPane4 = new JScrollPane();
        scrollPane4.setViewportView(textArea4);

        JLabel label14 = new JLabel();
        label14.setText("Recomendation:");

        JTextField textField6 = new JTextField();
        textField6.setText(record.getRecommendation());
        textField6.setEnabled(false);

        JLabel label15 = new JLabel();
        label15.setText("Date Signed");

        JLabel label16 = new JLabel();
        label16.setText("Reviewee:");

        JLabel label17 = new JLabel();
        label17.setText("Reviewer1:");

        JLabel label18 = new JLabel();
        label18.setText("Reviewer2:");

        JTextField textField7 = new JTextField();
        textField7.setText(record.getDateReviewer2());
        textField7.setEnabled(false);

        JTextField textField8 = new JTextField();
        textField8.setText(record.getDateReviewer1());
        textField8.setEnabled(false);

        JTextField textField9 = new JTextField();
        textField9.setText(record.getDateReviewee());
        textField9.setEnabled(false);

        javax.swing.GroupLayout openedPastReviewPanelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(openedPastReviewPanelLayout);
        openedPastReviewPanelLayout.setHorizontalGroup(
                openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(scrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(label1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label4)
                                                        .addComponent(label2)
                                                        .addComponent(label3)
                                                        .addComponent(label5)
                                                        .addComponent(label6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(textField1)
                                                        .addComponent(textField2)
                                                        .addComponent(textField3)
                                                        .addComponent(textField4)
                                                        .addComponent(textField5, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)))
                                        .addComponent(scrollPane1)
                                        .addComponent(scrollPane2)
                                        .addComponent(scrollPane4)
                                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(label7)
                                                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                                                .addComponent(label8)
                                                                .addGap(70, 70, 70)
                                                                .addComponent(label9)
                                                                .addGap(69, 69, 69)
                                                                .addComponent(label10))
                                                        .addComponent(label11)
                                                        .addComponent(label12)
                                                        .addComponent(label13)
                                                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                                                .addComponent(label14)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(label15)
                                                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                                                .addComponent(label16)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                                                .addComponent(label17)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                                                .addComponent(label18)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        openedPastReviewPanelLayout.setVerticalGroup(
                openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(label1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label2)
                                        .addComponent(textField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label3)
                                        .addComponent(textField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label4)
                                        .addComponent(textField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label5)
                                        .addComponent(textField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label6)
                                        .addComponent(textField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label8)
                                        .addComponent(label9)
                                        .addComponent(label10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(scrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label14)
                                        .addComponent(textField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(label15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label16)
                                        .addComponent(textField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label17)
                                        .addComponent(textField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(label18)
                                        .addComponent(textField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(105, Short.MAX_VALUE))
        );
        JScrollPane scrollPane5 = new JScrollPane();
        scrollPane5.setViewportView(panel);

        return scrollPane5;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        emergencyTextField = new javax.swing.JTextField();
        mobileTextField = new javax.swing.JTextField();
        phoneTextField = new javax.swing.JTextField();
        postcodeTextField = new javax.swing.JTextField();
        countyTextField = new javax.swing.JTextField();
        townTextField = new javax.swing.JTextField();
        addressTextField = new javax.swing.JTextField();
        dobTextField = new javax.swing.JTextField();
        lastnameTextField = new javax.swing.JTextField();
        firstnameTextField = new javax.swing.JTextField();
        emergencyNbTextField = new javax.swing.JTextField();
        getPersonalRecordButton = new javax.swing.JButton();
        addToDatabaseButton = new javax.swing.JButton();
        IDTextField = new javax.swing.JFormattedTextField();
        redLabel = new javax.swing.JLabel();
        blueLabel = new javax.swing.JLabel();
        clearButton = new javax.swing.JButton();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        nameTextField1 = new javax.swing.JTextField();
        jobTitleTextField1 = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        achievementsTextArea = new javax.swing.JTextArea();
        submitButton1 = new javax.swing.JButton();
        redLabel4 = new javax.swing.JLabel();
        blueLabel4 = new javax.swing.JLabel();
        reviewer1TextField1 = new javax.swing.JFormattedTextField();
        reviewer2TextField1 = new javax.swing.JFormattedTextField();
        sectionBox1 = new javax.swing.JComboBox<>();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane7 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        jLabel54 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        reviewer2TextField2 = new javax.swing.JTextField();
        reviewer1TextField2 = new javax.swing.JTextField();
        nameTextField2 = new javax.swing.JTextField();
        sectionTextField2 = new javax.swing.JTextField();
        jobTitleTextField2 = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        objectivesTextArea = new javax.swing.JTextArea();
        jLabel64 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        performanceSummaryText = new javax.swing.JTextArea();
        jLabel65 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        goalTextArea = new javax.swing.JTextArea();
        jLabel66 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        reviewerCommentsText = new javax.swing.JTextArea();
        jLabel67 = new javax.swing.JLabel();
        recomendationBox = new javax.swing.JComboBox<>();
        jLabel68 = new javax.swing.JLabel();
        signCheckBox = new javax.swing.JCheckBox();
        submitButton2 = new javax.swing.JButton();
        redLabel3 = new javax.swing.JLabel();
        blueLabel3 = new javax.swing.JLabel();
        dateSignedField = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        openedPastReviewPanel = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel86 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jLabel89 = new javax.swing.JLabel();
        reviewer2TextField4 = new javax.swing.JTextField();
        reviewer1TextField4 = new javax.swing.JTextField();
        nameTextField4 = new javax.swing.JTextField();
        sectionTextField4 = new javax.swing.JTextField();
        jobTitleTextField4 = new javax.swing.JTextField();
        jLabel90 = new javax.swing.JLabel();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        jLabel93 = new javax.swing.JLabel();
        jScrollPane19 = new javax.swing.JScrollPane();
        jTextArea14 = new javax.swing.JTextArea();
        jLabel94 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        performanceSummaryText2 = new javax.swing.JTextArea();
        jLabel95 = new javax.swing.JLabel();
        jScrollPane21 = new javax.swing.JScrollPane();
        jTextArea15 = new javax.swing.JTextArea();
        jLabel96 = new javax.swing.JLabel();
        jScrollPane22 = new javax.swing.JScrollPane();
        reviewerCommentsText2 = new javax.swing.JTextArea();
        jLabel97 = new javax.swing.JLabel();
        recomendationTextField = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        jLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Personal Details");

        jLabel5.setText("ID:");

        jLabel6.setText("Firstname:");

        jLabel7.setText("Lastname:");

        jLabel8.setText("Date of birth:");

        jLabel9.setText("Address:");

        jLabel10.setText("Town/City:");

        jLabel11.setText("County:");

        jLabel12.setText("Postcode:");

        jLabel17.setText("Phone:");

        jLabel13.setText("Mobile:");

        jLabel14.setText("Emergency contact:");

        jLabel15.setText("Emergency number:");

        getPersonalRecordButton.setText("Get Personal Record");
        getPersonalRecordButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPersonalRecordButtonActionPerformed(evt);
            }
        });

        addToDatabaseButton.setText("Add to Database");
        addToDatabaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addToDatabaseButtonActionPerformed(evt);
            }
        });

        IDTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        redLabel.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        redLabel.setForeground(new java.awt.Color(255, 0, 0));
        redLabel.setText("Negative feedback");

        blueLabel.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        blueLabel.setForeground(new java.awt.Color(0, 0, 255));
        blueLabel.setText("Positive feedback");

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17)
                            .addComponent(jLabel13)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emergencyTextField)
                            .addComponent(mobileTextField)
                            .addComponent(phoneTextField)
                            .addComponent(postcodeTextField)
                            .addComponent(countyTextField)
                            .addComponent(townTextField)
                            .addComponent(addressTextField)
                            .addComponent(dobTextField)
                            .addComponent(lastnameTextField)
                            .addComponent(firstnameTextField)
                            .addComponent(emergencyNbTextField)
                            .addComponent(IDTextField)))
                    .addComponent(addToDatabaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(redLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(blueLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(getPersonalRecordButton, javax.swing.GroupLayout.DEFAULT_SIZE, 479, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(firstnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lastnameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(dobTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(addressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(townTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(countyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(postcodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(mobileTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(emergencyTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(emergencyNbTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getPersonalRecordButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addToDatabaseButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        redLabel.setVisible(false);
        blueLabel.setVisible(false);

        jTabbedPane1.addTab("Personal Details", jPanel3);

        jLabel26.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel26.setText("Annual Review Record");

        jLabel19.setText("Name:");

        jLabel27.setText("Manager/Director ID:");

        jLabel28.setText("2nd Manager/Director ID:");

        jLabel29.setText("Section:");

        jLabel30.setText("Job Title:");

        jLabel31.setText("A review of past performance: achievements and outcomes");

        achievementsTextArea.setColumns(20);
        achievementsTextArea.setRows(5);
        jScrollPane6.setViewportView(achievementsTextArea);

        submitButton1.setText("Submit");
        submitButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButton1ActionPerformed(evt);
            }
        });

        redLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        redLabel4.setForeground(new java.awt.Color(255, 0, 0));
        redLabel4.setText("Negative feedback");

        blueLabel4.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        blueLabel4.setForeground(new java.awt.Color(0, 0, 255));
        blueLabel4.setText("Positive feedback");

        reviewer1TextField1.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        sectionBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Administration", "Sales and Marketing", "Services Delivery", "Human Ressources" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel28)
                            .addComponent(jLabel19)
                            .addComponent(jLabel29)
                            .addComponent(jLabel30))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jobTitleTextField1)
                            .addComponent(nameTextField1)
                            .addComponent(reviewer1TextField1)
                            .addComponent(reviewer2TextField1)
                            .addComponent(sectionBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jScrollPane6)
                    .addComponent(submitButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel31)
                            .addComponent(redLabel4)
                            .addComponent(blueLabel4))
                        .addGap(0, 106, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel26)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(nameTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(reviewer1TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(reviewer2TextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(sectionBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(jobTitleTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton1)
                .addContainerGap(157, Short.MAX_VALUE))
        );

        redLabel4.setVisible(false);
        blueLabel4.setVisible(false);

        jTabbedPane3.addTab("Create Review", jPanel5);

        jTabbedPane1.addTab("Annual Review", jTabbedPane3);

        jLabel54.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel54.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel54.setText("Current Review");

        jLabel55.setText("Name:");

        jLabel56.setText("Manager/Director ID:");

        jLabel57.setText("2nd Manager/Director ID:");

        jLabel58.setText("Section:");

        jLabel59.setText("Job Title:");

        reviewer2TextField2.setEnabled(false);

        reviewer1TextField2.setEnabled(false);

        nameTextField2.setEnabled(false);

        sectionTextField2.setEnabled(false);

        jobTitleTextField2.setEnabled(false);

        jLabel60.setText("A review of past performance: achievements and outcomes");

        jLabel61.setText("No:");

        jLabel62.setText("Objectives:");

        jLabel63.setText("Achievement:");

        objectivesTextArea.setColumns(20);
        objectivesTextArea.setRows(5);
        objectivesTextArea.setEnabled(false);
        jScrollPane11.setViewportView(objectivesTextArea);

        jLabel64.setText("Performance Summary:");

        performanceSummaryText.setColumns(20);
        performanceSummaryText.setRows(5);
        jScrollPane12.setViewportView(performanceSummaryText);

        jLabel65.setText("A preview of future performance : goals/planned outcomes");

        goalTextArea.setColumns(20);
        goalTextArea.setRows(5);
        jScrollPane13.setViewportView(goalTextArea);

        jLabel66.setText("Reviewer Comments:");

        reviewerCommentsText.setColumns(20);
        reviewerCommentsText.setRows(5);
        jScrollPane14.setViewportView(reviewerCommentsText);

        jLabel67.setText("Recommendation:");

        recomendationBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Stay in post", "Salary increase", "Promotion", "Probation", "Termination" }));

        jLabel68.setText("Date:");

        signCheckBox.setText("Sign");

        submitButton2.setText("Submit");
        submitButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButton2ActionPerformed(evt);
            }
        });

        redLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        redLabel3.setForeground(new java.awt.Color(255, 0, 0));
        redLabel3.setText("Negative feedback");

        blueLabel3.setFont(new java.awt.Font("Lucida Grande", 0, 10)); // NOI18N
        blueLabel3.setForeground(new java.awt.Color(0, 0, 255));
        blueLabel3.setText("Positive feedback");

        dateSignedField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(java.text.DateFormat.getDateInstance(java.text.DateFormat.SHORT))));
        dateSignedField.setText("dd/MM/yy");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel54, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel57)
                            .addComponent(jLabel55)
                            .addComponent(jLabel56)
                            .addComponent(jLabel58)
                            .addComponent(jLabel59))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reviewer2TextField2)
                            .addComponent(reviewer1TextField2)
                            .addComponent(nameTextField2)
                            .addComponent(sectionTextField2)
                            .addComponent(jobTitleTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)))
                    .addComponent(jScrollPane11)
                    .addComponent(jScrollPane12)
                    .addComponent(jScrollPane14)
                    .addComponent(submitButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel60)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel62)
                                .addGap(69, 69, 69)
                                .addComponent(jLabel63))
                            .addComponent(jLabel64)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel67)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recomendationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(signCheckBox)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel68)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dateSignedField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(redLabel3)
                            .addComponent(blueLabel3))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel54)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel55)
                    .addComponent(nameTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel56)
                    .addComponent(reviewer1TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(reviewer2TextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel58)
                    .addComponent(sectionTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel59)
                    .addComponent(jobTitleTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel60)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel61)
                    .addComponent(jLabel62)
                    .addComponent(jLabel63))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel64)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel65)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel66)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel67)
                    .addComponent(recomendationBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(signCheckBox)
                    .addComponent(jLabel68)
                    .addComponent(dateSignedField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(redLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(blueLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(submitButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        redLabel3.setVisible(false);
        blueLabel3.setVisible(false);

        jScrollPane7.setViewportView(jPanel13);

        jTabbedPane2.addTab("Pending Review", jScrollPane7);

        jLabel84.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel84.setText("Current Review");

        jLabel85.setText("Name:");

        jLabel86.setText("Manager/Director ID:");

        jLabel87.setText("2nd Manager/Director ID:");

        jLabel88.setText("Section:");

        jLabel89.setText("Job Title:");

        reviewer2TextField4.setEnabled(false);

        reviewer1TextField4.setEnabled(false);

        nameTextField4.setEnabled(false);

        sectionTextField4.setEnabled(false);

        jobTitleTextField4.setEnabled(false);

        jLabel90.setText("A review of past performance: achievements and outcomes");

        jLabel91.setText("No:");

        jLabel92.setText("Objectives:");

        jLabel93.setText("Achievement:");

        jTextArea14.setColumns(20);
        jTextArea14.setRows(5);
        jTextArea14.setEnabled(false);
        jScrollPane19.setViewportView(jTextArea14);

        jLabel94.setText("Performance Summary:");

        performanceSummaryText2.setColumns(20);
        performanceSummaryText2.setRows(5);
        performanceSummaryText2.setEnabled(false);
        jScrollPane20.setViewportView(performanceSummaryText2);

        jLabel95.setText("A preview of future performance : goals/planned outcomes");

        jTextArea15.setColumns(20);
        jTextArea15.setRows(5);
        jTextArea15.setEnabled(false);
        jScrollPane21.setViewportView(jTextArea15);

        jLabel96.setText("Reviewer Comments:");

        reviewerCommentsText2.setColumns(20);
        reviewerCommentsText2.setRows(5);
        reviewerCommentsText2.setEnabled(false);
        jScrollPane22.setViewportView(reviewerCommentsText2);

        jLabel97.setText("Recommendation:");

        recomendationTextField.setText("recomendationTextField");

        jLabel16.setText("Date Signed");

        jLabel18.setText("Reviewee:");

        jLabel20.setText("Reviewer1:");

        jLabel21.setText("Reviewer2:");

        jTextField1.setText("date");

        jTextField2.setText("date");

        jTextField3.setText("date");

        javax.swing.GroupLayout openedPastReviewPanelLayout = new javax.swing.GroupLayout(openedPastReviewPanel);
        openedPastReviewPanel.setLayout(openedPastReviewPanelLayout);
        openedPastReviewPanelLayout.setHorizontalGroup(
            openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane21, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                        .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel87)
                            .addComponent(jLabel85)
                            .addComponent(jLabel86)
                            .addComponent(jLabel88)
                            .addComponent(jLabel89))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(reviewer2TextField4)
                            .addComponent(reviewer1TextField4)
                            .addComponent(nameTextField4)
                            .addComponent(sectionTextField4)
                            .addComponent(jobTitleTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)))
                    .addComponent(jScrollPane19)
                    .addComponent(jScrollPane20)
                    .addComponent(jScrollPane22)
                    .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                        .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel90)
                            .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                .addComponent(jLabel91)
                                .addGap(70, 70, 70)
                                .addComponent(jLabel92)
                                .addGap(69, 69, 69)
                                .addComponent(jLabel93))
                            .addComponent(jLabel94)
                            .addComponent(jLabel95)
                            .addComponent(jLabel96)
                            .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                .addComponent(jLabel97)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(recomendationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16)
                            .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        openedPastReviewPanelLayout.setVerticalGroup(
            openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(openedPastReviewPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel84)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(nameTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel86)
                    .addComponent(reviewer1TextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel87)
                    .addComponent(reviewer2TextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel88)
                    .addComponent(sectionTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel89)
                    .addComponent(jobTitleTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel90)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel91)
                    .addComponent(jLabel92)
                    .addComponent(jLabel93))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel94)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel96)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel97)
                    .addComponent(recomendationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(openedPastReviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(105, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(openedPastReviewPanel);

        jTabbedPane2.addTab("Review + Date", jScrollPane1);

        jTabbedPane1.addTab("Current Review", jTabbedPane2);
        jTabbedPane2.removeTabAt(1);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 36)); // NOI18N
        jLabel1.setText("Yuconz HR System");

        jLabel2.setText(sC.getCurrentUser().getUsername() + " (" + sC.getCurrentUser().getAuthorisationLvl() + ")");

        jLabel3.setText("Logged in as:");

        jButton1.setText("Logout");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 557, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Setup for Logout button
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        dispose();
        new GUILogin();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * Setup for the "clear" button
     *
     * @param evt
     */
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:

        //Clears all textfields bar the ID textfield
        clearText();
    }//GEN-LAST:event_clearButtonActionPerformed

    /**
     * Setup for "Add to database" button which can either create or amend a personal details record
     *
     * @param evt
     */
    private void addToDatabaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addToDatabaseButtonActionPerformed
        // TODO add your handling code here:

        //Clear the feedback labels
        blueLabel.setVisible(false);
        redLabel.setVisible(false);
        //Check if the ID textfield is valid
        if (IDTextField.getText().equals("")) {
            //Show negative feedback
            redLabel.setText("*ID field is empty, must be a number");
            redLabel.setVisible(true);
        } else {
            //Try to create record in database
            String feedback = sC.createPersonalDetailsRecord(Integer.valueOf(IDTextField.getText()),
                    firstnameTextField.getText(), lastnameTextField.getText(),
                    dobTextField.getText(), addressTextField.getText(),
                    townTextField.getText(), countyTextField.getText(),
                    postcodeTextField.getText(), phoneTextField.getText(),
                    mobileTextField.getText(), emergencyTextField.getText(),
                    emergencyNbTextField.getText());
            //If record already exists or user has insufficient privileges to create a record, try to amend it
            if (feedback.equals("Cannot create personal details records (insuficient authorisations)")
                    || feedback.contains("already exists")) {
                feedback = sC.amendPersonalDetailsRecord(Integer.valueOf(IDTextField.getText()),
                        firstnameTextField.getText(), lastnameTextField.getText(),
                        dobTextField.getText(), addressTextField.getText(),
                        townTextField.getText(), countyTextField.getText(),
                        postcodeTextField.getText(), phoneTextField.getText(),
                        mobileTextField.getText(), emergencyTextField.getText(),
                        emergencyNbTextField.getText());
            }
            if (feedback.contains("Added") || feedback.contains("Amended")) {
                //Show positive feedback
                blueLabel.setText(feedback);
                blueLabel.setVisible(true);
            } else {
                //Show negative feedback
                redLabel.setText(feedback);
                redLabel.setVisible(true);
            }
        }
    }//GEN-LAST:event_addToDatabaseButtonActionPerformed

    /**
     * Setup for the "get record" button
     *
     * @param evt
     */
    private void getPersonalRecordButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getPersonalRecordButtonActionPerformed
        // TODO add your handling code here:

        //Clear the feedback labels
        blueLabel.setVisible(false);
        redLabel.setVisible(false);
        //Clear the text in all of the textfields bar the ID field
        clearText();
        //Check if the ID textfield is valid
        if (IDTextField.getText().equals("")) {
            //Show negative feedback
            redLabel.setText("*ID field is empty, must be a number");
            redLabel.setVisible(true);
        } else {
            //Try to get the personal record associated with the entered ID from the database
            try {
                PersonalDetailsRecord record = sC.getPersonalDetailsRecord(
                        Integer.parseInt(IDTextField.getText()));
                //Set the textfield to the values from the record
                firstnameTextField.setText(record.getFirstname());
                lastnameTextField.setText(record.getLastname());
                dobTextField.setText(record.getDateOfBirth());
                addressTextField.setText(record.getAddress());
                townTextField.setText(record.getTown());
                countyTextField.setText(record.getCounty());
                postcodeTextField.setText(record.getPostcode());
                phoneTextField.setText(record.getPhoneNb());
                mobileTextField.setText(record.getMobileNb());
                emergencyTextField.setText(record.getEmergencyContact());
                emergencyNbTextField.setText(record.getEmergencyContactNb());
                //Exception if the ID doesn't have a record associated with it
            } catch (NullPointerException ex) {
                //Show negative feedback
                redLabel.setText("Personal detail record for user "
                        + IDTextField.getText() + " don't exist in database");
                redLabel.setVisible(true);
            }
        }
    }//GEN-LAST:event_getPersonalRecordButtonActionPerformed

    /**
     * Setup for the submit button in current review tab
     *
     * @param evt
     */
    private void submitButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButton2ActionPerformed
        // TODO add your handling code here:

        //Clear the feedback labels
        blueLabel3.setVisible(false);
        redLabel3.setVisible(false);
        //Check Fields

        //Amend review record
        if (signCheckBox.isSelected()) {
            if (dateSignedField.getText().equals("") || dateSignedField.getText().equals("dd/MM/yy")) {
                redLabel3.setText("Invalid date");
                redLabel3.setVisible(true);
            }
            String feedback = sC.amendAnnualReviewRecord(currentReview.getReviewID(), dateSignedField.getText(),
                    performanceSummaryText.getText(), goalTextArea.getText(), reviewerCommentsText.getText(),
                    (String) recomendationBox.getSelectedItem(), signCheckBox.isSelected());
            checkReviews();   
            blueLabel3.setText(feedback);
            blueLabel3.setVisible(true);
        }
        else {
            String feedback = sC.amendAnnualReviewRecord(currentReview.getReviewID(), dateSignedField.getText(),
                    performanceSummaryText.getText(), goalTextArea.getText(), reviewerCommentsText.getText(),
                    (String) recomendationBox.getSelectedItem(), signCheckBox.isSelected());
            checkReviews();   
            blueLabel3.setText(feedback);
            blueLabel3.setVisible(true);
        }
        if (jTabbedPane1.getTabCount() > 2) {
            jTabbedPane1.setSelectedIndex(2);
        }
    }//GEN-LAST:event_submitButton2ActionPerformed

    /**
     * Setup for the submit button in annual review tab
     *
     * @param evt
     */
    private void submitButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButton1ActionPerformed
        // TODO add your handling code here:

        //Clear the feedback labels
        blueLabel4.setVisible(false);
        redLabel4.setVisible(false);
        //Check fields
        if (nameTextField1.getText().equals("")) {
            redLabel4.setText("Name cannot be empty");
            redLabel4.setVisible(true);
        } else if (reviewer1TextField1.getText().equals("") || reviewer2TextField1.getText().equals("")) {
            redLabel4.setText("Reviewer ID must be a number");
            redLabel4.setVisible(true);
        } else if (reviewer1TextField1.getText().equals(reviewer2TextField1.getText())) {
            redLabel4.setText("Both Reviewers cannot be the same");
            redLabel4.setVisible(true);
        } else if (jobTitleTextField1.getText().equals("")) {
            redLabel4.setText("Job Title cannot be empty");
            redLabel4.setVisible(true);
        } //Create new Review record
        else {
            String feedback = sC.createAnnualReviewRecord(nameTextField1.getText(),
                    (String) sectionBox1.getSelectedItem(), jobTitleTextField1.getText(),
                    Integer.valueOf(reviewer1TextField1.getText()), Integer.valueOf(reviewer2TextField1.getText()),
                    achievementsTextArea.getText());
            checkReviews();
            blueLabel4.setText(feedback);
            blueLabel4.setVisible(true);
        }
    }//GEN-LAST:event_submitButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField IDTextField;
    private javax.swing.JTextArea achievementsTextArea;
    private javax.swing.JButton addToDatabaseButton;
    private javax.swing.JTextField addressTextField;
    private javax.swing.JLabel blueLabel;
    private javax.swing.JLabel blueLabel3;
    private javax.swing.JLabel blueLabel4;
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField countyTextField;
    private javax.swing.JFormattedTextField dateSignedField;
    private javax.swing.JTextField dobTextField;
    private javax.swing.JTextField emergencyNbTextField;
    private javax.swing.JTextField emergencyTextField;
    private javax.swing.JTextField firstnameTextField;
    private javax.swing.JButton getPersonalRecordButton;
    private javax.swing.JTextArea goalTextArea;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel97;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextArea jTextArea14;
    private javax.swing.JTextArea jTextArea15;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jobTitleTextField1;
    private javax.swing.JTextField jobTitleTextField2;
    private javax.swing.JTextField jobTitleTextField4;
    private javax.swing.JTextField lastnameTextField;
    private javax.swing.JTextField mobileTextField;
    private javax.swing.JTextField nameTextField1;
    private javax.swing.JTextField nameTextField2;
    private javax.swing.JTextField nameTextField4;
    private javax.swing.JTextArea objectivesTextArea;
    private javax.swing.JPanel openedPastReviewPanel;
    private javax.swing.JTextArea performanceSummaryText;
    private javax.swing.JTextArea performanceSummaryText2;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.JTextField postcodeTextField;
    private javax.swing.JComboBox<String> recomendationBox;
    private javax.swing.JTextField recomendationTextField;
    private javax.swing.JLabel redLabel;
    private javax.swing.JLabel redLabel3;
    private javax.swing.JLabel redLabel4;
    private javax.swing.JFormattedTextField reviewer1TextField1;
    private javax.swing.JTextField reviewer1TextField2;
    private javax.swing.JTextField reviewer1TextField4;
    private javax.swing.JFormattedTextField reviewer2TextField1;
    private javax.swing.JTextField reviewer2TextField2;
    private javax.swing.JTextField reviewer2TextField4;
    private javax.swing.JTextArea reviewerCommentsText;
    private javax.swing.JTextArea reviewerCommentsText2;
    private javax.swing.JComboBox<String> sectionBox1;
    private javax.swing.JTextField sectionTextField2;
    private javax.swing.JTextField sectionTextField4;
    private javax.swing.JCheckBox signCheckBox;
    private javax.swing.JButton submitButton1;
    private javax.swing.JButton submitButton2;
    private javax.swing.JTextField townTextField;
    // End of variables declaration//GEN-END:variables
}
