package Database;

public class User {
    
    /* Class attributes */
    private String name, surname;
    private String email, password;
    private char gender;
    private int ID;


    /**
     * Default constructor
     */
    public User() {
        this.name = "Default";
        this.surname = "Default";
        this.email = "Default";
        this.password = "Default";
        this.gender = 'X';
        this.ID = Integer.MAX_VALUE;
    }

    public User(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.ID = user.getID();
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
    public User(int ID, String name, String surname, String email, String password, char gender) { 
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        setGender(gender);
        setID(ID);
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
        return this.name;
    }

    /**
     * Simple set method for name
     * 
     * @param name The name of the user
     * 
     * @return Class User handle for subsequent set calls, returns null if any error occurred
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
        return this.surname;
    }

    /**
     * Simple set method for surname
     * 
     * @param surname The surname of the user
     * 
     * @return Class User handle for subsequent set calls, returns null if any error occurred
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
        return this.email;
    }

    /**
     * Simple set method for email 
     * 
     * @param mail The email of the user
     * 
     * @return Class User handle for subsequent set calls, returns null if any error occurred
     */
    public User setEmail(String email) {
        this.email = email;
        return this;
    }


    /**
     * Simple get method for password
     * 
     * @return The email of the user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Simple set method for password 
     * 
     * @param password The password of the user
     * 
     * @return Class User handle for subsequent set calls, returns null if any error occurred
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
        return this.ID;
    }

    /**
     * Simple set method for ID 
     * 
     * @param ID The ID of the user
     * 
     * @return Class User handle for subsequent set calls, returns null if any error occurred
     */
    public User setID(int ID) {
        if (ID > 0) {
            this.ID = ID;

            return this;
        } else {
            return null;
        }
    }


    /**
     * Simple get method for gender
     * 
     * @return The gender of the User
     */
    public char getGender() {
        return gender;
    }

    /**
     * Simple set method for gender 
     * 
     * @param gender The gender of the user in upper case (M or F)
     * 
     * @return Class User handle for subsequent set calls, returns null if any error occurred
     */
    public User setGender(char gender) {
        char tmpGender = Character.toUpperCase(gender);

        if (tmpGender == 'M' || tmpGender == 'F') {
            this.gender = tmpGender;

            return this;
        } else {
            return null;
        }
    }



    public String toString() {
        return "Name: " + this.name + "\nSurname: " + this.surname + "\nID: " + this.ID + "\nEmail: " + this.email + "\nPassword: " 
               + this.password + "\nGender: " + this.gender;
    }
}
