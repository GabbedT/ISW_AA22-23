package com.example.azienda;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class User {

    private static int globalUserID = 2;
    
    /* Class attributes */
    private String name, surname;
    private Date dateOfBirth;
    private String email, password;
    private int age;
    private char gender;
    private int ID;


    /**
     * Default constructor
     */
    public User() {
        Database DBMS = new Database();

        globalUserID = DBMS.getGlobalUserID();

        this.name = "Default";
        this.surname = "Default";
        this.email = "Default";
        this.password = "Default";
        this.gender = 'X';
        this.age = 0;
        this.dateOfBirth = new Date(0, 0, 0);
        this.ID = Integer.MAX_VALUE;
    }

    /**
     * Constructor to instantiate a filled User object. 
     * 
     * @param name User legal name
     * @param surname User legal surname
     * @param email User email to access his account
     * @param password User password to access his account
     * @param gender User gender
     * @param age User age
     * @param birthDay User birth day (Date.SQL)
     */
    public User(String name, String surname, String email, String password, char gender, int age, Date birthDay) {
        Database DBMS = new Database();

        globalUserID = DBMS.getGlobalUserID();

        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.age = age;
        this.dateOfBirth = new Date(birthDay.getTime());
        this.ID = globalUserID;

        setGender(gender);
    }


    public static void main(String[] args) {
        Date birth = new Date(System.currentTimeMillis());
        User usrA = new User("Gabriele", "Tripi", "gab@gmail.com", "Ciao", 'M', 20, birth);
        User usrB = new User("Andrea", "Raineri", "gab@gmail.com", "Ciao", 'M', 20, birth);
        User usrC = new User("Hermes", "Cassataro", "gab@gmail.com", "Ciao", 'M', 20, birth);
        User usrD = new User("Emanuele", "Spataro", "gab@gmail.com", "Ciao", 'M', 20, birth);

        System.out.println(usrA.toString());
        System.out.println(usrB.toString());
        System.out.println(usrC.toString());
        System.out.println(usrD.toString());
    }

//-----------------------//
//  GET AND SET METHODS  //
//-----------------------//

    public int getGlobalUserID() {
        return globalUserID;
    }

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
     * @param email The email of the user
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


    public int getAge() {
        return this.age;
    }

    public User setAge(int age) {
        /* Check if the user is an adult */
        if (age < 18) {
            return null;
        } else {
            this.age = age;

            return this;
        }
    }


    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public User setDateOfBirth(Date birthDay) {
        Calendar currentTime = Calendar.getInstance();
        long minYears = 568025136000L;

        /* Check if the user is an adult */
        if ((currentTime.getTimeInMillis() - birthDay.getTime()) < minYears) {
            return null;
        } else {
            this.dateOfBirth = new Date(birthDay.getTime());

            return this;
        }
    }





    public void copy(User user) {
        this.name = user.getName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.gender = user.getGender();
        this.ID = user.getID();
        this.age = user.getAge();
        this.dateOfBirth = new Date(user.getDateOfBirth().getTime());
    }


    public String toString() {
        return "Name: " + this.name + "\nSurname: " + this.surname + "\nID: " + this.ID + "\nEmail: " + this.email + "\nPassword: " 
               + this.password + "\nGender: " + this.gender + "\nAge: " + this.age + "\nBirth Day: " + this.dateOfBirth.toString() + "\n\n";
    }
}
