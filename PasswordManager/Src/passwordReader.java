import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Class to read stored passwords from a text file
 */
public class passwordReader {


    private ArrayList<passwordCell> passwordList;
    private File storage= new File("storage.txt");
    private Scanner scanner;
    private PrintWriter writer;

    /**
    *Default constructor
     */
    public passwordReader(){
        //create an empty arraylist
        passwordList= new ArrayList<>();
    }

    /**
     * this methods reads from the storage.txt file and
     * stores the information into an arraylist of passwordCell objects
     * @return the current data stored in the storage.txt file, in form of arrayList<passwordCell>
     */
    public ArrayList<passwordCell> readPasswords(){

        try {
            scanner = new Scanner(storage);
            //loop and read passwords
            //int counter = 0;
            //read while there are lines to be read
            while (scanner.hasNext()) {
                //System.out.printf("counter=%d\n", ++counter);
                String line = scanner.nextLine();
                String[] data = line.split(" "); //at i=0 name, i=1 password, i=2 username, i=3 email
                //System.out.println(Arrays.toString(data));
                passwordCell tempCell = new passwordCell(data[0], data[1], data[2], data[3]);
                passwordList.add(tempCell);

            }
        }
        catch(FileNotFoundException e) {e.printStackTrace();}
        catch(NullPointerException e) {e.printStackTrace();}
        finally {scanner.close();}

        //System.out.println(passwordList.toString());
        return passwordList;
    }

    /**
     * writes password to the storage file
     * Ideally rewriting the list after each change would be innefficent at larger
     * file sizes, but for now its okay
     * @param passwordList passwordlist that is to be written to file
     */
    public void writePasswords(ArrayList<passwordCell> passwordList){

        try {
            writer = new PrintWriter(storage);
            for (passwordCell cell : passwordList) {
                writer.println(cell.getName()+" "+cell.getPassword()+" "+cell.getUsername()+" "+cell.getEmail());
            }
        }
        catch (FileNotFoundException e){e.printStackTrace();}
        catch (NullPointerException e){e.printStackTrace();}
        finally{writer.close();}
    }



}
