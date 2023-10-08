import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GeneratePasswordUI extends JFrame  implements ChangeListener,ActionListener {

    private JPanel panel;
    private JLabel label;
    private JSlider slider;
    private JButton generateButton;
    private JButton keeppasswordButton;
    private JButton mypasswordButton;
    private JTextField generatedPasswordField;
    private JTextField myPasswordField;


    public GeneratePasswordUI() {
        Initialize();
        setComponents();
        Finally();

        // completing buttons events
        generateButton.addActionListener(this);
        keeppasswordButton.addActionListener(this);
        mypasswordButton.addActionListener(this);
    }

    public void Initialize() {
        setTitle("Generate Password");
        setSize(400, 300);

        slider = new JSlider(JSlider.HORIZONTAL, 0, 2000, 1);
        slider.setPreferredSize(new Dimension(200, 50));
        slider.addChangeListener(this);
    }

    public void setComponents() {
        panel = new JPanel();
        label = new JLabel();
        generatedPasswordField = new JTextField(20);
        myPasswordField = new JTextField(20);
        generateButton = new JButton("Generate!");
        keeppasswordButton = new JButton("Keep Password");
        mypasswordButton = new JButton("My Password");
        

        panel.setLayout(new FlowLayout());

        panel.add(slider);
        panel.add(generateButton);
        panel.add(label);
        panel.add(generatedPasswordField);
        panel.add(keeppasswordButton);
        panel.add(mypasswordButton);
        panel.add(myPasswordField);

        add(panel);

        label.setText("Password Length: " + slider.getValue());
    }

    public void Finally() {
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }


    private String generated; // create 'generated' for keep generated password
    private String getusername = LoginUI.loginusername; // fetch username from loginUI
    private PasswordManager PWM = new PasswordManager(); // create object PWM from PasswordManager

@Override
public void stateChanged(ChangeEvent e) {
    label.setText("Password Length: " + slider.getValue());
}

@Override
public void actionPerformed(ActionEvent e) {

    //user selects Generate!
    if (e.getSource() == generateButton) {

        //get value from slider into passwordLength
        int passwordLength = slider.getValue();
        PasswordGenerator passwordGenerator = new PasswordGenerator(); //create object passwordGenerator
        generated = passwordGenerator.generatePassword(passwordLength); // assign to 'generated' 

        //if user doesn't choose length
        if (passwordLength == 0) {
            JOptionPane.showMessageDialog(GeneratePasswordUI.this, "No Password Generated");
        }
        // show generated password in generatedPasswordField
        generatedPasswordField.setText(generated);
    }
    //user selects Keep Password
    if (e.getSource() == keeppasswordButton) {

        //show confirm keeping password
        int Confirm_Keeping = JOptionPane.showConfirmDialog(null, "Keep this password?",
         "Confirm !",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        //user selects YES
        if (Confirm_Keeping==0){
        JOptionPane.showMessageDialog(GeneratePasswordUI.this, "Your Password Is Added Successfully !");
        //add user's generated password using PWM into userpassword.txt
        PWM.addUserPassword(getusername, generated);

        }
    }

    //user selects My Password
    if (e.getSource() == mypasswordButton) {

        //show user's password from database in myPasswordField
        myPasswordField.setText(PWM.getPasswordForUser(getusername));

    }
}

}

