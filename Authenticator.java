import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Authenticator {

    // Authenticate user account
    public static boolean authenticateLogin(String username, String password) {

        try {
            // read "user_data.txt"
            FileReader fileReader = new FileReader("user_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            
            // search each line
            while ((line = bufferedReader.readLine()) != null) {
                
                String[] parts = line.split(",");
                
                // check if username and password are correct
                if (parts[0].equals(username) && parts[1].equals(password)) 
                {
                    bufferedReader.close();
                    return true;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}