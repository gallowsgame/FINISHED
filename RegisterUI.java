import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class RegisterUI extends JFrame implements ActionListener{

    private JPanel panel;
    private JButton regisButton;
    private JButton returntologinButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    // constructor 
    public RegisterUI() {
        initialize();
        setComponents();
        finalizeUI();
        // completing buttons events
        regisButton.addActionListener(this);
        returntologinButton.addActionListener(this);
        
    }

    // set Title and Size
    private void initialize() {
        setTitle("Register");
        setSize(340, 150);
    }

    private void setComponents() {

        panel = new JPanel(new FlowLayout());

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        confirmPasswordField = new JPasswordField(20);
        regisButton = new JButton("Register");
        returntologinButton = new JButton("Return To Login");

        // Change mouse cursor
        regisButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // add all components to panel
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPasswordField);
        panel.add(regisButton);
        panel.add(returntologinButton);

        add(panel, BorderLayout.CENTER);
    }

    private void finalizeUI() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // user cannot resize the panel
        setResizable(false);
    }

    // Buttons events
    @Override
    public void actionPerformed(ActionEvent e) {

        // when clicked "Register" button
        if (e.getSource()==regisButton){

            //fetch registered username from usernameField into username
            String username = usernameField.getText();
            //fetch registered password from passwordField and change to string and keep into 'password'
            String password = new String(passwordField.getPassword());
            //fetch registered confirmpassword from confirmPasswordField and change to string and keep into 'confirmpassword'
            String confirmpassword = new String(confirmPasswordField.getPassword());
        
            // Check if username, password, or confirm password is blank
            if (username.isBlank() || password.isBlank() || confirmpassword.isBlank()) {
                JOptionPane.showMessageDialog(RegisterUI.this, "Username or password cannot be blank.");

            // if password and confirmpassword don't match
            } else if (!password.equals(confirmpassword)) {
                JOptionPane.showMessageDialog(RegisterUI.this, "Password and confirm password do not match.");
            }
            else {
            // Check if the username already exists
                RegisterFile userdata = new RegisterFile(); // create object 'userdata' from RegisterFile

            //check existed username 
            //if username existed
            if (userdata.isUsernameExisted(username)) {
                JOptionPane.showMessageDialog(RegisterUI.this, "Username already exists. Please choose another one.");
            } 
            // if username doesn't exist
            else {
                // Proceed with registration
                userdata.registerUser(username, password, confirmpassword);
                JOptionPane.showMessageDialog(RegisterUI.this, "Registration successful!");

                //close current RegisterUI and open LoginUI again
                new LoginUI();
                dispose();
            }
        }
    }

    //user select Return To Login
    // return to LoginUI page
    if (e.getSource()==returntologinButton){
        dispose();
        new LoginUI();
    }
}
}