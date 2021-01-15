/**
 * This class holds passoword cell objects
 */
public class passwordCell {

    private String name;
    private String password;
    private String username;
    private String email;

    /**
     * default constructor
     * for creating empty cell
     */
    public passwordCell(String name){
        this.name= name;
        this.password= "";
        this.username= "";
        this.email="";
    }

    /**
     *
     * @param name
     * @param password
     * @param username
     * @param email
     */
    public passwordCell(String name, String password, String username, String email){
        this.name= name;
        this.password= password;
        this.username= username;
        this.email= email;
    }

    //getters and setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
