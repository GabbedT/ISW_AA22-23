package Database;

public class User {
    
    /* Class attributes */
    private String name, surname;
    private String email, password;
    private int ID;


    public User() {
        this.name = "Default";
        this.surname = "Default";
        this.email = "Default";
        this.password = "Default";
        this.ID = 0xFFFF_FFFF;
    }

    /**
     * Constructor to instantiate a filled User object. 
     * 
     * @param ID User identification code, ID must be a number bigger than 0
     * @param name User legal name
     * @param surname User legal surname
     * @param email User email to access his account
     * @param password User password to access his account
     */
    public User(int ID, String name, String surname, String email, String password) { 
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.ID = ID;
    }


//-----------------------//
//  GET AND SET METHODS  //
//-----------------------//

    /**
     * Simple get method for surname
     * 
     * @return The name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Simple set method for name
     * 
     * @param name The name of the user
     * @return Class User for subsequent set calls
     */
    public User setName(String name) {
        this.name = name;
        return this;
    }


    /**
     * Simple get method for surname
     * 
     * @return The surname of the user
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Simple set method for surname
     * 
     * @param surname The surname of the user
     * @return Class User for subsequent set calls
     */
    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }


    /**
     * Simple get method for email
     * 
     * @return The email of the user
     */
    public String getEmail() {
        return email;
    }

    /**
     * Simple set method for email 
     * 
     * @param mail The email of the user
     * @return Class User for subsequent set calls
     */
    public User setMail(String email) {
        this.email = email;
        return this;
    }


    /**
     * Simple get method for password
     * 
     * @return The email of the user
     */
    public String getPassword() {
        return password;
    }

    /**
     * Simple set method for password 
     * 
     * @param password The password of the user
     * @return Class User for subsequent set calls
     */
    public User setPassword(String password) {
        this.password = password;
        return this;
    }


    /**
     * Simple get method for password
     * 
     * @return The email of the user
     */
    public int getID() {
        return ID;
    }

    /**
     * Simple set method for ID 
     * 
     * @param ID The ID of the user
     * @return Class User for subsequent set calls
     */
    public User setID(int ID) {
        this.ID = ID;
        return this;
    }
}
