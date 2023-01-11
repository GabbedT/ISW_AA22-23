package Database;

import java.sql.Date;

public class Employee extends User {

    /* Class attributes */
    private int salary;
    private int expectedWorkHours, finalWorkHours;
    private float hourlySalary;


    /**
     * Default constructor: set all the attributes to their default values
     */
    public Employee() { 
        super();
        this.expectedWorkHours = 144;
        this.finalWorkHours = 0;
        this.salary = 1;
        this.hourlySalary = 0.0F;
    }

    
    public Employee(User user) {
        super.copy(user);
    }


    /**
     * Constructor to instantiate a new Employee object, used for hiring new employees.
     * The global ID will get incremented.
     * 
     * @param name Employee legal name
     * @param surname Employee legal surname
     * @param email Employee email to access his account
     * @param password Employee password to access his account
     * @param salary Employee salary in euro
     * @param gender Employee gender
     * @param age Employee age
     * @param birthDay Employee birth day (Date.SQL)
     * @param expWork Expected work hours from the Employee
     */
    public Employee(String name, String surname, String email, String password, char gender, int age, Date birthDay, int salary, int expWork) {
        super(name, surname, email, password, gender, age, birthDay);
        this.expectedWorkHours = expWork;
        this.finalWorkHours = 0;

        setSalary(salary);
    }


//-----------------------//
//  GET AND SET METHODS  //
//-----------------------//

    /**
     * Simple get method for salary
     * 
     * @return The salary of the employee
     */
    public int getSalary() {
        return this.salary;
    }

    /**
     * Simple set method for salary. It computes the hourly salary and check if
     * it's above the minimum. If it's not assign the variables to their minimum
     * values. 
     * 
     * @param salary The salary of the user
     * @return Class User for subsequent set calls, null if error occurred
     */
    public Employee setSalary(int salary) {
        float newHourlySalary = (float)(salary / this.expectedWorkHours);
        
        /* Check the minimum salary */
        if (newHourlySalary < 9.0F) {
            System.err.println("Minimum hourly salary is 9 euro / hour, inserted: " + Double.toString(newHourlySalary));
            System.out.println("Assigning the minimum salary");

            this.hourlySalary = 9.0F;
            this.salary = (int)(this.hourlySalary * this.expectedWorkHours);
            
            return null;
        } else {
            this.salary = salary;
            this.hourlySalary = newHourlySalary;
        }

        return this;
    }


    /**
     * Simple get method for the expected number of work hours of the employee
     * 
     * @return The expected number of work hours 
     */
    public int getExpectedWorkHours() {
        return this.expectedWorkHours;
    } 

    /**
     * Simple set method for expected number of hours 
     * 
     * @param expWorkHours The salary of the user
     * @return Class User for subsequent set calls
     */
    public Employee setExpectedWorkHours(int expWorkHours) {
        /* Check the maximum number of work hours in a week */
        if ((expWorkHours / 4) > 48) {
            System.err.println("Maximum weekly work hours is 48 hours / week, inserted: " + Integer.toString((expWorkHours / 4)));
            System.out.println("Assigning the maximum expected work hours: 48");

            this.expectedWorkHours = 48;

            return null;
        } else {
            this.expectedWorkHours = expWorkHours;
        }

        return this;
    }


    /**
     * Simple get method for the current work hours of the employee
     * 
     * @return The final number of work hours 
     */
    public int getFinalWorkHours() {
        return this.finalWorkHours;
    }

    /**
     * Simple set method for the current work hours of the employee
     * 
     * @param finWorkHours Set the current workHours
     * @return The expected number of work hours 
     */
    public Employee setFinalWorkHours(int finWorkHours) {
        this.finalWorkHours = finWorkHours;
        return this;
    }


//-----------//
//  METHODS  //
//-----------//

    public void copy(Employee employee) {
        super.copy(employee);

        this.setExpectedWorkHours(employee.getExpectedWorkHours());
        this.finalWorkHours = employee.getFinalWorkHours();
        this.setSalary(employee.getSalary());
    }

// SPOSTA I METODI NELLA CONTROL

    /**
     * Increment the current work hours of the employee
     * 
     * @param incValue A floating point value to
     */
    public void incrementFinalWorkHours(int incValue) {
        this.finalWorkHours += incValue;
    }


    public int computeFinalSalary() {
        if (this.finalWorkHours > ((float) this.expectedWorkHours)) {
            /* If the employee worked overtime, increment the 
             * hourly salary by 25% for the overtime hours */
            float overtimeHours = finalWorkHours - ((float) expectedWorkHours);
            float overtimeHourlySalary = this.hourlySalary + (0.25F * this.hourlySalary);
            
            return (this.salary + ((int) (overtimeHours * overtimeHourlySalary)));
        } else {
            return (int) (this.finalWorkHours * this.hourlySalary);
        }
    }


    public String toString() {
        return super.toString() + "\nSalary: " + this.salary + "\nExpected Work Hours: " + this.expectedWorkHours + "\nFinal Work Hours: " + this.finalWorkHours;
    }
}
