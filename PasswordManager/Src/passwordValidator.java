import java.util.ArrayList;
import java.util.Arrays;

/**
 * utiliy class to check validity of a password
 */
public final class passwordValidator {

    //empty constructor to avoid initialization
    private passwordValidator(){

    }

    /**
     *
     * @param password password to check
     * @return an array of booleans (1. has char A-Z, 2.has char a-z, 3. has numeral, 0-9 4. has special characters ! " # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \ ] ^ _ `
     */
    public static ArrayList<Boolean> checkValidity(String password){
        //1. has char A-Z, 2.has char a-z, 3. has numeral, 0-9 4. has special characters ! " # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \ ] ^ _ ` 5.greater than 16 char
        ArrayList<Boolean> checkList = new ArrayList<Boolean>(Arrays.asList(false, false, false, false, false));

        //check if there is one capital letter
        if (password.matches("(.*[A-Z].*)")){
            //System.out.println("works");
            checkList.set(0,true);
        }
        //checks if there is one upper case letter
        if (password.matches("(.*[a-z].*)")){
            //System.out.println("works");
            checkList.set(1,true);
        }
        //checking for numeral
        if (password.matches("(.*[0-9].*)")){
            //System.out.println("works");
            checkList.set(2,true);
        }
        //matches if one of these special characters are present ! " # $ % & ' ( ) * + , - . / : ; < = > ? @ [ \ ] ^ _ `
        if (password.matches("(.*[!-/:-@\\[-`].*)")){
            //System.out.println("works");
            checkList.set(3,true);
        }
        //check if string is greater than or equal to 12 char
        if (password.length() >=16){
            checkList.set(4,true);
        }

        return checkList;
    }
}
