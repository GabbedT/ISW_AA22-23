package Database;

public class Employee extends User {

    /* Class attributes */
    int salary;
    int expectedWorkHours, finalWorkHours;

    /**
     * Constructor to instantiate a filled Employee object.
     * 
     * @param ID Employee identification code, ID must be a number bigger than 0
     * @param name Employee legal name
     * @param surname Employee legal surname
     * @param email Employee email to access his account
     * @param password Employee password to access his account
     * @param salary Employee salary in euro
     * @param expWH Employee expected total work hours 
     * @param finWH Employee final total work hours
     */
    public Employee(int ID, String name, String surname, String email, String password, int salary, int expWH, int finWH) {
        super(ID, name, surname, email, password);
        this.salary = salary;
        this.expectedWorkHours = expWH;
        this.finalWorkHours = finWH;
    }
    
}
