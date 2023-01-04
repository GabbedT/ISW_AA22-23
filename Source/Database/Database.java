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


//----------------------//
//  FETCH ROWS METHODS  //
//----------------------//

    /**
     * Retrieve an USER row from the database. 
     * Used for login.
     * 
     * @param loginEmail The email of the user.
     * @param loginPassword The password of the user.
     * 
     * @return The USER row found. if no rows were found in 
     * the database or an exception occured the returned value is null 
     */
    public User getUser(String loginEmail, String loginPassword) {
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
     * and surname. Used for searching the employees.
     * 
     * @param nameEmployee The name of the employee
     * @param surnameEmployee The name of the employee
     * 
     * @return A list of EMPLOYEE rows found, if no rows were found in 
     * the database or an exception occured, the returned value is null.
     */
    public ArrayList<Employee> getEmployee(String nameEmployee, String surnameEmployee) {
        /* Result containers */
        Employee empDB = new Employee();
        ArrayList<Employee> empListDB = new ArrayList<Employee>();
        
        try {
            /* Execute the query, retrieve all the Employee
             * rows from the database */
            query = DBMS.createStatement();
            resultQuery = query.executeQuery(
                "SELECT *" + 
                "FROM Employee Emp, User Usr" + 

                /* Join the User with the Employee and find partial matches */
                "WHERE (Usr.ID = Emp.EmployeeID) AND (Usr.Name = " + nameEmployee + "%) " + "AND (UsrSurname = " + surnameEmployee + "%)"
            );

            /* Select the right Employee out of all the rows */
            while (resultQuery.next()) {
                /* Fill Employee information */
                empDB.setName(resultQuery.getString("Name"));
                empDB.setSurname(resultQuery.getString("Surname"));
                empDB.setGender((resultQuery.getString("Gender").charAt(0)));
                empDB.setID(resultQuery.getInt("ID"));
                empDB.setSalary(resultQuery.getInt("Salary"));
                empDB.setExpectedWorkHours(resultQuery.getInt("ExpectedWorkHours"));
                empDB.setFinalWorkHours(resultQuery.getInt("FinalWorkHours"));
                empDB.setEmail(resultQuery.getString("Email"));
                empDB.setPassword(resultQuery.getString("Password")); 

                empListDB.add(empDB);
            }
            
            if (empListDB.isEmpty()) {
                System.err.println("Zero Employee rows found in database!");

                return null;
            } else {
                return empListDB;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        }
    } 


    /**
     * Retrieve an Employee from the database based on the user fields. Used after
     * login if the logged user is an Employee (ID > 0), retrieve more informations
     * about the User.
     * 
     * @param user An instance of an User
     * 
     * @return The Employee fetched or null if any error occurred
     */
    public Employee getEmployee(User user) {
        /* Result containers */
        Employee empDB = new Employee(user);
        int numberOfRows = 0;

        try {
            /* Execute the query, retrieve one Employee
             * row from the database */
            query = DBMS.createStatement();
            resultQuery = query.executeQuery(
                "SELECT *" + 
                "FROM Employee" + 

                /* Find partial matches */
                "WHERE ID = " + Integer.toString(user.getID())
            );

            /* Extract the result of the query */
            while (resultQuery.next()) {
                ++numberOfRows;

                /* Fill remaining employee information */
                empDB.setSalary(resultQuery.getInt("Salary"));
                empDB.setExpectedWorkHours(resultQuery.getInt("ExpectedWorkHours"));
                empDB.setFinalWorkHours(resultQuery.getInt("FinalWorkHours"));
            }

            if (numberOfRows == 1) {
                return empDB;
            } else if (numberOfRows == 0) {
                System.out.println("Zero Employee rows found in database!");

                return null;
            } else {
                System.out.println("Multiple Employee rows found in database!");

                return null; 
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        }
    }

    
    /**
     * Retrieve from the database a shift associated with an Employee
     * 
     * @param employee The istance of an employee 
     * @param shiftID A number between 1 and 6 which indicates the day of the week
     * 
     * @return The shift associated with the employee, if no rows were found in 
     * the database or an exception occured, the returned value is null.
     */
    public Shift getShift(Employee employee, int shiftID) {
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
                "FROM Employee Emp, Shift Sh" + 
                "WHERE (Emp.ID = " + employee.getID() + ") AND (Emp.ID = sh.EmployeeID) AND (Sh.ShiftID = " + 
                        Integer.toString(shiftID) + ")"
            );

            while (resultQuery.next()) {
                ++rowCount; 

                shiftDB.setStartHour(resultQuery.getString("StartHour"));
                shiftDB.setExitHour(resultQuery.getString("ExitHour"));
                shiftDB.setStartHourEmployee(resultQuery.getString("StartHourEmployee"));
                shiftDB.setExitHourEmployee(resultQuery.getString("ExitHourEmployee"));
                shiftDB.setPriorityLevel(resultQuery.getInt("PriorityLevel"));
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


    /**
     * Retrieve from the database all the Notifications associated with the User
     * 
     * @param userID The ID associated with the user
     * 
     * @return A list of Notifications, return null if any error occurred
     */
    public ArrayList<Notification> getNotification(int receiverID) {
        /* Query result containers */
        Notification notifDB;
        ArrayList<Notification> notifListDB = new ArrayList<Notification>();

        try {
            /* Execute the query, retrieve all the Notifications
             * rows associated with the User from the database */
            query = DBMS.createStatement();
            resultQuery = query.executeQuery(
                "SELECT *" + 
                "FROM Notification" + 
                "WHERE ReceiverID = " + Integer.toString(receiverID)
            );

            while (resultQuery.next()) {
                /* Create a new notification with the corresponding ID */
                notifDB = new Notification(resultQuery.getInt("NotificationID"));
                
                /* Set sender and receiver */
                notifDB.setReceiverID(receiverID);
                notifDB.setSenderID(resultQuery.getInt("SenderID"));

                /* Fill notification text */
                notifDB.setDescription(resultQuery.getString("Description"));
                notifDB.setTitle(resultQuery.getString("Title"));

                /* Push the new notification into the list */
                notifListDB.add(notifDB);
            }

            if (notifListDB.isEmpty()) {
                System.err.println("Zero Notifications rows found in database!");

                return null;
            } else {
                return notifListDB;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        }
    }


    /**
     * Retrieve from the database all the Abstention Request notifications associated with the User
     * 
     * @param userID The ID associated with the user
     * 
     * @return A list of AbstentionRequest, return null if any error occurred
     */
    public ArrayList<AbstentionRequest> AbstentionRequest(int receiverID) {
        /* Query result containers */
        AbstentionRequest absReqfDB;
        ArrayList<AbstentionRequest> absReqListDB = new ArrayList<AbstentionRequest>();

        try {
            /* Execute the query, retrieve all the Notifications
             * rows associated with the User from the database */
            query = DBMS.createStatement();
            resultQuery = query.executeQuery(
                "SELECT *" + 
                "FROM AbstentionRequest AbsReq, Notification Notif" + 

                /* Join the two tables and select those with the same receiver */
                "WHERE (AbsReq.RequestID = Notif.NotificationID) AND (ReceiverID = " + Integer.toString(receiverID) + ")"
            );

            while (resultQuery.next()) {
                /* Create a new notification with the corresponding ID */
                absReqfDB = new AbstentionRequest(resultQuery.getInt("NotificationID"));
                
                /* Fill sender and receiver */
                absReqfDB.setReceiverID(receiverID);
                absReqfDB.setSenderID(resultQuery.getInt("SenderID"));

                /* Fill notification text */
                absReqfDB.setDescription(resultQuery.getString("Description"));
                absReqfDB.setTitle(resultQuery.getString("Title"));

                /* Fill status info */
                absReqfDB.setAcceptanceStatus(resultQuery.getBoolean("AcceptanceStatus"));

                /* Cast the integer value into AbstentionType value */
                absReqfDB.setType((AbstentionRequest.AbstentionType.values()[resultQuery.getInt("RequestType")]));

                /* Push the new notification into the list */
                absReqListDB.add(absReqfDB);
            }

            if (absReqListDB.isEmpty()) {
                System.err.println("Zero Notifications rows found in database!");

                return null;
            } else {
                return absReqListDB;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        }
    }



//--------------------//
//  ADD ROWS METHODS  //
//--------------------//

    /**
     * Insert an Employee in the database
     * 
     * @param row Employee tuple to add
     * 
     * @return Boolean value for error checking
     */
    public Boolean addEmployee(Employee row) {
        try {
            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();

            /* Two insertions needs to be done, the first for the User informations
             * the second for the Employee informations correlated with the User */
            query.executeUpdate(DatabaseUtils.insertUser((User) row));
            query.executeUpdate(DatabaseUtils.insertEmployee(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        }
    }


    /**
     * Insert a Notification in the database
     * 
     * @param row Notification tuple to add
     * 
     * @return Boolean value for error checking
     */
    public Boolean addNotification(Notification row) {
        try {
            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();
            query.executeUpdate(DatabaseUtils.insertNotification(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        }
    }


    /**
     * Insert an Abstention Request in the database
     * 
     * @param row AbstentionRequest tuple to add
     * 
     * @return Boolean value for error checking
     */
    public Boolean addAbstentionRequest(AbstentionRequest row) {
        try {
            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();

            /* Two insertions needs to be done, the first for the Notification informations
             * the second for the AbstentionRequest informations correlated with the Notification */
            query.executeUpdate(DatabaseUtils.insertNotification((Notification) row));
            query.executeUpdate(DatabaseUtils.insertAbstentionRequest(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        }
    }


    /**
     * Insert a Shift in the database
     * 
     * @param row Shift tuple to add
     * 
     * @return Boolean value for error checking
     */
    public Boolean addShift(Shift row) {
        try {
            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();
            query.executeUpdate(DatabaseUtils.insertShift(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        }
    }
}