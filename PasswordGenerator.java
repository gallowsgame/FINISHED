import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator implements passwordGeneratorInterface {

    private final String UpperChar = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LowerChar = "abcdefghijklmnopqrstuvwxyz";
    private final String Numbers = "0123456789";
    private final String Symbols = "!@#$%^&*()-_+=<>?./";
    
    @Override
    public String generatePassword(int length) {
        //use StringBuilder to append each char into password
        StringBuilder password = new StringBuilder();
        //randomizer
        Random random = new SecureRandom();

        // mix all characters into 'allCharacters'
        String allCharacters = Symbols + UpperChar + LowerChar + Numbers + Symbols ;

        // generate password loop until reach length
        for (int i = 0; i < length; i++) {

            // choose random index from 'allCharacters'
            int index = random.nextInt(allCharacters.length());
            // complement each char into complete password
            password.append(allCharacters.charAt(index));

        }
        //store generated password in 'GenPass'
        String GenPass = password.toString();
        System.out.println(GenPass);
        return GenPass;
    }
}
