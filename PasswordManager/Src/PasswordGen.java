import java.util.Random;

/**
 *This class represents a password generator
 * based on user input password generator can
 * generate safe passwords!
 */
public class PasswordGen {

    private String password;
    private int maxChar;

    public PasswordGen(int maxChar){
        this.password= "";
        //to check if user inputted an insecure password length
        try {
            if (maxChar < 16) {
                throw new IllegalArgumentException("maxChar must be greater than 16");
            }
            this.maxChar = maxChar;
        }
        catch(IllegalArgumentException e){
            System.out.println("Max characters has been set to 16!");
            this.maxChar = 16;
        }

    }

    /**
     * Generates a secure password of maxChar length
     * @return
     */
    public String generatePass(){

        String rtrnString= "";
        Random rd= new Random();
        //loop until secure password is generated
        while ((passwordValidator.checkValidity(rtrnString)).contains(false)) {
            rtrnString= "";
            for (int i = 0; i < this.maxChar; i++) {
                int randChar = rd.nextInt(89) + 33;
                rtrnString += (char) randChar;
            }
        }
        System.out.println("Final string: "+ rtrnString);
        return rtrnString;
    }

    public String getPassword() {
        return password;
    }
}
