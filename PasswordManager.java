import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class PasswordManager {
    
    private static Map<String, String> userPasswords;
    // String getusername = LoginUI.loginusername; //fetch username from LoginUI

    public PasswordManager() {
        userPasswords = new HashMap<>();
        loadUserPasswords(); 
    }

    //add user's generated password into 'userpassword.txt'
    public void addUserPassword(String username, String password) {
        userPasswords.put(username, password);
        saveUserPasswords();
    }

    //get user's generated password by username
    public String getPasswordForUser(String username) {
        return userPasswords.get(username);
    }

    // call user's password
    private void loadUserPasswords() {
        try (BufferedReader reader = new BufferedReader(new FileReader("userpassword.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    userPasswords.put(username, password);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // keeping user's password into "userpassword.txt"
    private static void saveUserPasswords() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userpassword.txt"))) {
            for (Map.Entry<String, String> entry : userPasswords.entrySet()) {
                writer.write(entry.getKey() + "," + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}