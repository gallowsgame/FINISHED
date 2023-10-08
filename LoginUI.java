import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginUI extends JFrame implements ActionListener {

    // create varibles for components 
    private JPanel panel;
    private JButton loginButton, mainregisButton;
    private JTextField usernameField;
    private JPasswordField passwordField;
    public static String loginusername;
    private JLabel usernameLabel;
    private JLabel passwordLabel;
    

    // constructor
    public LoginUI(){

        initialize();
        setComponents();
        Finally();
        //  completing button events
        mainregisButton.addActionListener(this);
        loginButton.addActionListener(this);

    }

    // set title and size
    public void initialize (){
    setTitle("Password Generator");
    setSize(500,250);
    }

    // align all components properly
    public void setComponents(){

        panel = new JPanel(new GridLayout(4,5));

        usernameLabel = new JLabel("Username : ");
        usernameLabel.setFont(new Font("",Font.PLAIN, 20));
        passwordLabel = new JLabel("Password : ");
        passwordLabel.setFont(new Font("",Font.PLAIN, 20));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("",Font.PLAIN, 20));
        mainregisButton = new JButton("Register");
        mainregisButton.setFont(new Font("",Font.PLAIN, 20));

        // Change mouse cursor 
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        mainregisButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // add all components to panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); 
        panel.add(loginButton);
        panel.add(new JLabel()); 
        panel.add(mainregisButton);


        add(panel, BorderLayout.CENTER);


    }
    
    public void Finally (){
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // connot resize the panel
        setResizable(false);
        
    }

    // Buttons events
    @Override
    public void actionPerformed(ActionEvent e) {

        // when clicked "Register" button
        if(e.getSource()==mainregisButton){

            //close the LoginUi and open RegisterUI
            dispose();
            new RegisterUI();
        }

        // when click "Login" button
        if (e.getSource() == loginButton){
            
            // fetching username and password from usernameField
            loginusername = usernameField.getText(); // fetch username
            String loginpassword = new String(passwordField.getPassword()); //fetch password

            //Authenticate user using Authenticator 
            boolean checkLogin = Authenticator.authenticateLogin(loginusername, loginpassword); 
            if (loginusername.contains(",")){
                JOptionPane.showMessageDialog(LoginUI.this, "Symbols are not acceptable");
            }
            //if LOGIN SUCCESS --> authenticateLogin --> checkLogin = true
            if (checkLogin) {
                JOptionPane.showMessageDialog(LoginUI.this, "Login Successful !");
                new GeneratePasswordUI();
                dispose();
            }
            //if LOGIN FAILED --> authenticateLogin --> checkLogin = false
            else {
                JOptionPane.showMessageDialog(LoginUI.this, "Login failed. Invalid Username Or Password.");
            }
        }
    }
    
}