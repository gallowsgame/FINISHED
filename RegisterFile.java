import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class RegisterFile implements RegisterFileInterface {
    
    private final String FILE_NAME = "user_data.txt";

    // use method from RegisterFileInterface
    @Override
    public boolean registerUser(String username, String password, String confirmpassword){

        // if user doesn't put anything in the field
        if (username.isBlank() || password.isBlank() || confirmpassword.isBlank()) 
        {
            System.out.println("Username or password can't be empty.");
            return false;
        } 
        // if user's password and confirmpassword dont match
        else if (!password.equals(confirmpassword)) {
            return false;
        }

        // if user inputs data correctly
        else {

            try {
                
                // record user data in (username,password)
                FileWriter writer = new FileWriter(FILE_NAME, true); 
                writer.write(username + "," + password + "\n"); //seperate username and password using ','
                writer.close();
                System.out.println("Registration successful!");
                return true;

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

    }


    // check if username already existed
    public boolean isUsernameExisted(String username) {

        try {

            // read data from "user_data.txt"  
            FileReader fileReader = new FileReader("user_data.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line; //create 'line' to read each line in 'user_data'


            while ((line = bufferedReader.readLine()) != null) {

                // seperate username and password using ','
                // 'parts' will seperate into two parts in array type
                //the first index of 'parts' have to be username and the second is password
                String[] parts = line.split(",");

                //check if username exists
                if (parts[0].equals(username)) {
                    bufferedReader.close();
                    return true; // Username already exists
                }
            }
            bufferedReader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return false; // Username is not found
    }

    
}