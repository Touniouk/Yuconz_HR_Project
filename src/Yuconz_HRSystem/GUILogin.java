package Yuconz_HRSystem;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

/**
 * The User Interface for login
 * 
 * @author Tom, Nagendra, Luc, Reehan
 * @Version 1.2
 */
public class GUILogin 
{

    public GUILogin() 
    {

            createWindow();
    }

    /**
     * Create window
     */
    public static void createWindow() 
    {

        //Setup Frame
        JFrame frame = new JFrame("Login Window");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Create panel and add componants
        JPanel panel = new JPanel();
        frame.setContentPane(panel);
        placeComponents(panel, frame);

        //Open GUI in the middle of the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);

        //Set frame visible
        frame.setVisible(true);
    }

    /**
     * Place the components in the panel
     * 
     * @param panel
     * @param frame 
     */
    private static void placeComponents(JPanel panel, JFrame frame) 
    {

        panel.setLayout(null);

        //Setup for username field
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        JTextField userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        //Setup for password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        //Setup for privilages checkbox
        JCheckBox privilageCheckBox = new JCheckBox("Login without privileges");
        privilageCheckBox.setBounds(40, 70, 200, 25);
        panel.add(privilageCheckBox);

        //Setup for feedback field
        JLabel feedbackLabel = new JLabel("");
        feedbackLabel.setBounds(10, 95, 250, 25);
        panel.add(feedbackLabel);

        //Create login button
        JButton loginButton = new JButton("login");
        loginButton.addActionListener(e -> {
            SystemController sC = new SystemController(userText.getText(), 
                    String.valueOf(passwordText.getPassword()), privilageCheckBox.isSelected());
            switch (sC.getLoginFeedback()) {
                case "Accepted":
                    frame.dispose();
                    new GUIMain(sC);
                    break;
                case "Username not found":
                    feedbackLabel.setText("Username \"" + userText.getText() + "\" not found");
                    break;
                case "Incorrect password":
                    feedbackLabel.setText(sC.getLoginFeedback());
                    break;
            }
            //Always clear the password textfield
            passwordText.setText("");
        });

        //When you press the enter key while on the frame, loginButton is "pressed"
        JRootPane rootPane = frame.getRootPane();
        rootPane.setDefaultButton(loginButton);

        //Setup Login Button
        loginButton.setBounds(110, 120, 80, 25);
        panel.add(loginButton);
    }
}