package Database;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    /* Connect to a database */
    private Connection DBMS = null; 

    private Statement query;
    private ResultSet resultQuery;


    /**
     * Constructor method of the class, it connects the local PostgreSQL database
     * to the application code. 
     */
    public Database() {
        try {
            /* Connect to PostgreSQL database */
            Class.forName("org.postgresql.Driver");
            DBMS = DriverManager.getConnection("jdbc:postgresql://localhost:5432/testdb", "postgres", "DBMS_Password");
        } catch (Exception connExc) {
            /* Catch connection exception */
            connExc.printStackTrace();
            System.err.println("Error while connecting to database!");
            System.exit(0);
        }

        System.out.println("Connection enstablished!");
    }

    
    /**
     * Retrieve an USER row from the database. Used for login  
     * 
     * @param loginEmail The email of the user.
     * @param loginPassword The password of the user.
     * @return The USER row found. if no rows were found in 
     * the database or an exception occured the returned value is null 
     */
    public User getUser(String loginEmail, String loginPassword) throws SQLException {
        /* Result container */
        User usrDB = new User();
        
        /* Row counter to debug */
        int rowCount = 0;
        
        try {
            /* Execute the query, retrieve all the Employee
             * rows from the database */
            query = DBMS.createStatement();
            resultQuery = query.executeQuery(
                "SELECT *" + 
                "FROM User" + 
                "WHERE Email = " + loginEmail + "AND Password = " + loginPassword
            );

            /* Select the right Employee out of all the rows */
            while (resultQuery.next()) {
                ++rowCount;

                /* Fill Employee information */
                usrDB.setName(resultQuery.getString("Name"));
                usrDB.setSurname(resultQuery.getString("Surname"));
                usrDB.setID(resultQuery.getInt("ID"));
                usrDB.setSalary(resultQuery.getInt("Salary"));
                usrDB.setExpectedWorkHours(resultQuery.getInt("ExpectedWorkHours"));
                usrDB.setFinalWorkHours(resultQuery.getInt("FinalWorkHours"));
                usrDB.setEmail(loginEmail);
                usrDB.setPassword(loginPassword); 
            }
            
            if (rowCount == 1) {
                return usrDB;
            } else if (rowCount == 0) {
                System.err.println("Zero Employee rows found in database!");
                return null;
            } else {
                System.err.println("Multiple rows found in database with the same primary key!");
                return null;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();
            return null;
        }
    } 


    /**
     * Retrieve all the EMPLOYEE rows from the database based on name
     * and surname. 
     * 
     * @param nameEmployee The name of the employee
     * @param surnameEmployee The name of the employee
     * 
     * @return A list of EMPLOYEE rows found, if no rows were found in 
     * the database or an exception occured, the returned value is null.
     */
    public ArrayList<Employee> getEmployeeList(String nameEmployee, String surnameEmployee) throws SQLException {
        /* Result containers */
        Employee empDB = new Employee();
        ArrayList<Employee> empListDB = new ArrayList<Employee>();
        
        try {
            /* Execute the query, retrieve all the Employee
             * rows from the database */
            query = DBMS.createStatement();
            resultQuery = query.executeQuery(
                "SELECT *" + 
                "FROM Employee" + 

                /* Find partial matches */
                "WHERE Name = " + nameEmployee + "%" + "AND Surname = " + surnameEmployee + "%"
            );

            /* Select the right Employee out of all the rows */
            while (resultQuery.next()) {
                /* Fill Employee information */
                empDB.setName(resultQuery.getString("Name"));
                empDB.setSurname(resultQuery.getString("Surname"));
                empDB.setID(resultQuery.getInt("ID"));
                empDB.setSalary(resultQuery.getInt("Salary"));
                empDB.setExpectedWorkHours(resultQuery.getInt("ExpectedWorkHours"));
                empDB.setFinalWorkHours(resultQuery.getInt("FinalWorkHours"));
                empDB.setEmail(resultQuery.getString("Email"));
                empDB.setPassword(resultQuery.getString("Password")); 
            }
            
            if (empDB.isEmpty()) {
                System.err.println("Zero Employee rows found in database!");
                return null;
            } else {
                return empDB;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();
            return null;
        }
    } 


    /**
     * Retrieve from the database an shift specified by the following parameters:
     * 
     * @param employee The istance of an employee 
     * @param shiftID A number between 1 and 12, an odd number indicates
     * the first shift of the day (morning), an even number indicates the second
     * shift of the day (afternoon)
     * 
     * @return The shift associated with the employee, if no rows were found in 
     * the database or an exception occured, the returned value is null.
     */
    public Shift getShift(Employee employee, int shiftID) throws SQLException {
        if (shiftID < 1 || shiftID > 12) {
            System.out.println("Invalid shift number!");
            return null;
        }

        /* Query result container */
        Shift shiftDB = new Shift();
        
        /* Row counter to debug */
        int rowCount = 0;

        try {
            /* Execute the query, retrieve all the Employee
             * rows from the database */
            query = DBMS.createStatement();
            resultQuery = query.executeQuery(
                "SELECT sh.*" + 
                "FROM Employee emp, Shift sh" + 
                "WHERE (emp.ID = " + employee.getID() + ") AND (emp.ID = sh.EmployeeID) AND (sh.ShiftID = " + 
                        Integer.toString(shiftID) + ")"
            );

            while (resultQuery.next()) {
                ++rowCount; 

                shiftDB.setStartHour(resultQuery.getTime("StartHour"));
                shiftDB.setExitHour(resultQuery.getTime("ExitHour"));
                shiftDB.setStartHourEmployee(resultQuery.getTime("StartHourEmployee"));
                shiftDB.setExitHourEmployee(resultQuery.getTime("ExitHourEmployee"));
                shiftDB.setShiftID(shiftID);
            }

            if (rowCount == 1) {
                return shiftDB;
            } else if (rowCount == 0) {
                System.err.println("Zero Shift rows found in database!");
                return null;
            } else {
                System.err.println("Multiple rows found in database with the same primary key!");
                return null;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();
            return null;
        }
    }
}