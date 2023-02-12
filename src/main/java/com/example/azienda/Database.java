package com.example.azienda;

import java.awt.image.DataBuffer;
import java.sql.*;
import java.util.ArrayList;
import java.lang.Class;

public class Database {

    /* Connect to a database */
    private Connection DBMS = null;

    private Statement query;
    private ResultSet resultQuery;

    private final String databaseName = "ApplicationDatabase";
    private final String username = "postgres";
    private final String password = "DBMS_Password";

    public static void main(String[] args) {
        Database DBMS = new Database();

        ArrayList<AbstentionRequest> list = DBMS.getAbstentionRequest(1, true);
        System.out.println(list.toString());
    }


    /**
     * Constructor method of the class, it connects the local PostgreSQL database
     * to the application code.
     */
    public Database() {
        try {
            /* Connect to PostgreSQL database */
            Class.forName("org.postgresql.Driver");
            DBMS = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.databaseName, this.username, this.password);
        } catch (Exception connExc) {
            /* Catch connection exception */
            connExc.printStackTrace();
            System.err.println("Error while connecting to database!");
            System.exit(0);
        }

        System.out.println("Connection established!");
    }


    public void closeConnection() {
        if (query != null) {
            try {
                query.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultQuery != null) {
            try {
                resultQuery.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (DBMS != null) {
            try {
                DBMS.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void enstablishConnection() {
        try {
            DBMS = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.databaseName, this.username, this.password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


//----------------------//
//  FETCH ROWS METHODS  //
//----------------------//

    /**
     * Retrieve a USER row from the database.
     * Used for login.
     *
     * @param loginEmail    The email of the user.
     * @param loginPassword The password of the user.
     * @return The USER row found. if no rows were found in
     * the database or an exception occurred the returned value is null
     */
    public User getUser(String loginEmail, String loginPassword) {
        this.enstablishConnection();

        /* Result container */
        User usrDB = new User();

        /* Row counter to debug */
        int rowCount = 0;

        try {
            /* Execute the query, retrieve all the Employee
             * rows from the database */
            query = DBMS.createStatement();

            String queryString = "SELECT * " +
                    "FROM UserApp " +
                    "WHERE Email = ? AND Password = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);

            statement.setString(1, loginEmail);
            statement.setString(2, loginPassword);

            resultQuery = statement.executeQuery();

            /* Select the right Employee out of all the rows */
            while (resultQuery.next()) {
                ++rowCount;

                /* Fill Employee information */
                usrDB.setName(resultQuery.getString("Name"));
                usrDB.setSurname(resultQuery.getString("Surname"));
                usrDB.setID(resultQuery.getInt("ID"));
                usrDB.setGender(resultQuery.getString("Gender").charAt(0));
                usrDB.setAge(resultQuery.getInt("Age"));
                usrDB.setDateOfBirth(resultQuery.getDate("DateOfBirth"));
                usrDB.setEmail(loginEmail);
                usrDB.setPassword(loginPassword);
            }

            if (rowCount == 1) {
                return usrDB;
            } else if (rowCount == 0) {
                System.err.println("Zero Employee rows found in database!");

                return null;
            } else {
                return null;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public User getUser(int userID) {
        this.enstablishConnection();

        /* Result container */
        User usrDB = new User();
        int rowCount = 0;

        try {
            /* Execute the query, retrieve all the Employee
             * rows from the database */
            query = DBMS.createStatement();

            String queryString = "SELECT * " +
                    "FROM UserApp " +
                    "WHERE ID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);

            statement.setInt(1, userID);

            resultQuery = statement.executeQuery();

            /* Select the right Employee out of all the rows */
            while (resultQuery.next()) {
                ++rowCount;

                /* Fill Employee information */
                usrDB.setName(resultQuery.getString("Name"));
                usrDB.setSurname(resultQuery.getString("Surname"));
                usrDB.setID(resultQuery.getInt("ID"));
                usrDB.setAge(resultQuery.getInt("Age"));
                usrDB.setDateOfBirth(resultQuery.getDate("DateOfBirth"));
                usrDB.setGender(resultQuery.getString("Gender").charAt(0));
                usrDB.setEmail(resultQuery.getString("Email"));
                usrDB.setPassword(resultQuery.getString("Password"));
            }

            if (rowCount == 1) {
                return usrDB;
            } else if (rowCount == 0) {
                System.err.println("Zero Employee rows found in database!");

                return null;
            } else {
                return null;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }



    public ArrayList<Integer> getEmployee(char service) {
        this.enstablishConnection();

        /* Result containers */
        ArrayList<Integer> employeeList = new ArrayList<>();

        try {
            /* Execute the query, retrieve all the Employee
             * rows from the database */
            query = DBMS.createStatement();

            String queryString = "SELECT EmployeeID " +
                    "FROM Employee " +
                    "WHERE Service = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setString(1, String.valueOf(service));

            resultQuery = statement.executeQuery();

            /* Select the right Employee out of all the rows */
            while (resultQuery.next()) {
                /* Fill Employee information */
                employeeList.add(resultQuery.getInt("EmployeeID"));
            }

            if (employeeList.isEmpty()) {
                System.err.println("Zero Employee rows found in database!");

                return null;
            } else {
                return employeeList;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Retrieve an Employee from the database based on the user fields. Used after
     * login if the logged user is an Employee (ID > 0), retrieve more information
     * about the User.
     *
     * @param user An instance of a User
     * @return The Employee fetched or null if any error occurred
     */
    public Employee getEmployee(User user) {
        this.enstablishConnection();

        /* Result containers */
        Employee empDB = new Employee(user);
        int numberOfRows = 0;


        /* Check if the user is the Employer */
        if (user.getID() == 0) {
            System.out.println("Searching employee!");
            return null;
        }

        try {
            /* Execute the query, retrieve one Employee
             * row from the database */
            query = DBMS.createStatement();

            String queryString = "SELECT * " +
                    "FROM Employee " +
                    "WHERE EmployeeID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, user.getID());

            resultQuery = statement.executeQuery();

            /* Extract the result of the query */
            while (resultQuery.next()) {
                ++numberOfRows;

                /* Fill remaining employee information */
                empDB.setExpectedWorkHours(resultQuery.getInt("ExpectedWorkHours"));
                empDB.setFinalWorkHours(resultQuery.getInt("FinalWorkHours"));
                empDB.setSalary(resultQuery.getInt("Salary"));
                empDB.setService(resultQuery.getString("Service").charAt(0));
            }

            if (numberOfRows == 1) {
                return empDB;
            } else if (numberOfRows == 0) {
                System.out.println("Zero Employee rows found in database!");

                return empDB;
            } else {
                System.out.println("Multiple Employee rows found in database!");

                return null;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Retrieve from the database all the Notifications associated with the User
     *
     * @param userID The ID associated with the user
     * @return A list of Notifications, return null if any error occurred
     */
    public ArrayList<Notification> getNotificationList(int userID, boolean receiver) {
        this.enstablishConnection();

        /* Query result containers */
        Notification notifDB;
        ArrayList<Notification> notifListDB = new ArrayList<>();

        try {
            /* Execute the query, retrieve all the Notifications
             * rows associated with the User from the database */
            query = DBMS.createStatement();

            String queryString;
            if (receiver) {
                queryString = "SELECT * " +
                        "FROM Notification " +
                        "WHERE ReceiverID = ? ";
            } else {
                queryString = "SELECT * " +
                        "FROM Notification " +
                        "WHERE SenderID = ? ";
            }
            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, userID);

            resultQuery = statement.executeQuery();

            while (resultQuery.next()) {
                /* Create a new notification with the corresponding ID */
                notifDB = new Notification();

                notifDB.setNotificationID(resultQuery.getInt("NotificationID"));

                /* Set sender and receiver */
                notifDB.setReceiverID(userID);
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
        } finally {
            this.closeConnection();
        }
    }


    public Notification getNotification(int notificationID) {
        this.enstablishConnection();

        /* Query result containers */
        Notification notifDB = new Notification();

        try {
            /* Execute the query, retrieve all the Notifications
             * rows associated with the User from the database */
            query = DBMS.createStatement();

            String queryString = "SELECT * " +
                    "FROM Notification " +
                    "WHERE NotificationID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, notificationID);

            resultQuery = statement.executeQuery();
            if (!resultQuery.next()) {
                System.err.println("NULL NOTIFICATION!");
                return null;
            } else {
                notifDB.setNotificationID(resultQuery.getInt("NotificationID"));

                /* Set sender and receiver */
                notifDB.setReceiverID(resultQuery.getInt("ReceiverID"));
                notifDB.setSenderID(resultQuery.getInt("SenderID"));

                /* Fill notification text */
                notifDB.setDescription(resultQuery.getString("Description"));
                notifDB.setTitle(resultQuery.getString("Title"));

                return notifDB;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Retrieve from the database all the Abstention Request notifications associated with a specific user
     *
     * @return A list of AbstentionRequest, return null if any error occurred
     */
    public ArrayList<AbstentionRequest> getAbstentionRequest(int userID, boolean receiver) {
        this.enstablishConnection();

        /* Query result containers */
        ArrayList<AbstentionRequest> list = new ArrayList<>();

        try {
            /* Execute the query, retrieve all the Notifications
             * rows associated with the User from the database */
            query = DBMS.createStatement();

            String queryString;
            if (receiver) {
                queryString = "SELECT * FROM AbstentionRequest, Notification WHERE (ReceiverID = ? ) AND (NotificationID = RequestID) ";
            } else {
                queryString = "SELECT * FROM AbstentionRequest, Notification WHERE (SenderID = ? ) AND (NotificationID = RequestID) ";
            }

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, userID);
            resultQuery = statement.executeQuery();

            while (resultQuery.next()) {
                int notificationID = resultQuery.getInt("NotificationID");
                int receiverID = resultQuery.getInt("ReceiverID");
                int senderID = resultQuery.getInt("SenderID");

                String title = resultQuery.getString("Title");
                String description = resultQuery.getString("Description");

                /* Fill status info */
                boolean status = resultQuery.getBoolean("AcceptanceStatus");

                Date startAbstention = resultQuery.getDate("StartAbstention");
                Date setEndAbstention = resultQuery.getDate("EndAbstention");

                /* Cast the integer value into AbstentionType value */
                int type = resultQuery.getInt("RequestType");

                list.add(new AbstentionRequest(new Notification(title, description, receiverID, senderID, notificationID), status, type, startAbstention, setEndAbstention));
            }

            if (list.isEmpty()) {
                System.err.println("Zero Notifications rows found in database!");

                return null;
            } else {
                return list;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }

    public AbstentionRequest getAbstentionRequest(int notificationID) {
        try {
            this.enstablishConnection();

            query = DBMS.createStatement();

            String queryString = "SELECT * " +
                    "FROM AbstentionRequest AR, Notification NT " +
                    "WHERE (NT.NotificationID = AR.RequestID) AND (AR.RequestID = ? ) ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, notificationID);

            resultQuery = statement.executeQuery();
            resultQuery.next();

            int receiverID = resultQuery.getInt("ReceiverID");
            int senderID = resultQuery.getInt("SenderID");

            String title = resultQuery.getString("Title");
            String description = resultQuery.getString("Description");

            boolean status = resultQuery.getBoolean("AcceptanceStatus");
            int type = resultQuery.getInt("RequestType");
            Date start = resultQuery.getDate("StartAbstention");
            Date end = resultQuery.getDate("EndAbstention");

            return new AbstentionRequest(title, description, receiverID, senderID, status, type, start, end);
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public String getShiftStart(int day, int employeeID) {
        try {
            this.enstablishConnection();

            query = DBMS.createStatement();

            String queryString = "SELECT StartShift " +
                    "FROM Turnation " +
                    "WHERE (Day = ?) AND (EmployeeID = ?) ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, day);
            statement.setInt(2, employeeID);

            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getString("StartShift");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public Shift getShift(int day, int employee) {
        try {
            this.enstablishConnection();

            query = DBMS.createStatement();

            String queryString = "SELECT * " +
                    "FROM Turnation " +
                    "WHERE (Day = ? ) AND (EmployeeID = ? ) ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, day);
            statement.setInt(2, employee);

            resultQuery = statement.executeQuery();

            if (!resultQuery.next()) {
                System.err.println("NO ROWS FOUND!");

                return null;
            } else {
                char service = resultQuery.getString("Service").charAt(0);
                String start = resultQuery.getString("StartShift");
                String end = resultQuery.getString("EndShift");

                return new Shift(start, end, day, SystemControl.weekOfTrimester(true), service, employee);
            }

        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Retrieve the trimester time information from the database.
     *
     * @return An array of two Date object, the first one is the start of the trimester,
     * the second one is the end. Return null if any error occurred
     */
    public Date[] getTrimester() {
        this.enstablishConnection();

        /* Query result container */
        Date[] trimester = new Date[2];

        try {
            try {
                DBMS = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + this.databaseName, this.username, this.password);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            query = DBMS.createStatement();

            String queryString = "SELECT * " +
                    "FROM Trimester ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);

            resultQuery = statement.executeQuery();
            resultQuery.next();

            trimester[0] = resultQuery.getDate("StartDate");
            trimester[1] = resultQuery.getDate("EndDate");

            return trimester;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public int getGlobalUserID() {
        try {
            this.enstablishConnection();

            String queryString = "SELECT GlobalUserID " +
                    "FROM Utils ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getInt("GlobalUserID");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return 0;
        } finally {
            this.closeConnection();
        }
    }


    public int getSearchEmployeeID() {
        try {
            String queryString = "SELECT SearchID " +
                    "FROM Utils ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getInt("SearchID");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return 0;
        }
    }


    public int getLoggedUserID() {
        try {
            this.enstablishConnection();

            String queryString = "SELECT LoggedUserID " +
                    "FROM Utils ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getInt("LoggedUserID");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return 0;
        } finally {
            this.closeConnection();
        }
    }


    public int getGlobalNotificationID() {
        try {
            this.enstablishConnection();

            String queryString = "SELECT GlobalNotificationID " +
                    "FROM Utils ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getInt("GlobalNotificationID");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return 0;
        } finally {
            this.closeConnection();
        }
    }


    public Shift getTurnation(int day, int employeeID) {
        try {
            this.enstablishConnection();

            String queryString = "SELECT * " +
                    "FROM Turnation " +
                    "WHERE (Day = ? ) AND (EmployeeID = ? ) ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, day);
            statement.setInt(2, employeeID);

            resultQuery = statement.executeQuery();

            if (resultQuery.next()) {
                int empID = resultQuery.getInt("EmployeeID");
                int dayID = resultQuery.getInt("Day");
                char service = resultQuery.getString("Service").charAt(0);
                String start = resultQuery.getString("StartShift");
                String end = resultQuery.getString("EndShift");

                System.out.println("Rows found on day " + day);

                return new Shift(start, end, dayID, 0, service, empID);
            } else {
                System.err.println("No rows on day " + day);

                return null;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }

    public Shift[] getWeekShift(int week, int employeeID) {
        try {
            this.enstablishConnection();

            String queryString = "SELECT * " +
                    "FROM Shift " +
                    "WHERE (Week = ? ) AND (EmployeeID = ? ) ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, week);
            statement.setInt(2, employeeID);

            resultQuery = statement.executeQuery();

            Shift[] weekly = new Shift[7];

            for (int i = 0; i < weekly.length; ++i) {
                weekly[i] = new Shift(i + 1, employeeID);
            }

            while (resultQuery.next()) {
                Shift temp = new Shift();

                temp.setDay(resultQuery.getInt("Day"));
                temp.setEmployeeID(resultQuery.getInt("EmployeeID"));
                temp.setExpectedEntrance(resultQuery.getString("ExpectedEntrance"));
                temp.setExpectedExit(resultQuery.getString("ExpectedExit"));
                temp.setService(resultQuery.getString("Service").charAt(0));

                weekly[temp.getDay() - 1] = temp;
            }

            return weekly;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public int getMaximumEmployeeID() {
        try {
            this.enstablishConnection();

            String queryString = "SELECT MAX(EmployeeID) AS MaxID " +
                    "FROM Employee ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getInt("MaxID");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return 0;
        } finally {
            this.closeConnection();
        }
    }

    public int getBaseDay(int employeeID) {
        try {
            this.enstablishConnection();

            String queryString = "SELECT MIN(Day) AS BaseDay " +
                    "FROM Turnation " +
                    "WHERE EmployeeID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, employeeID);

            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getInt("BaseDay");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return 0;
        } finally {
            this.closeConnection();
        }
    }

    public ArrayList<Integer> getShiftDays(int employeeID) {
        try {
            this.enstablishConnection();

            ArrayList<Integer> dayList = new ArrayList<>();

            String queryString = "SELECT Day " +
                    "FROM Turnation " +
                    "WHERE EmployeeID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, employeeID);

            resultQuery = statement.executeQuery();

            while (resultQuery.next()) {
                dayList.add(resultQuery.getInt("Day"));
            }

            if (dayList.isEmpty()) {
                System.err.println("NO ROWS");

                return null;
            } else {
                return dayList;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }

    public ArrayList<Integer> getShiftDays(int employeeID, int week) {
        try {
            this.enstablishConnection();

            ArrayList<Integer> dayList = new ArrayList<>();

            String queryString = "SELECT Day " +
                    "FROM Shift " +
                    "WHERE (EmployeeID = ? ) AND (Week = ? ) ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, employeeID);
            statement.setInt(2, week);

            resultQuery = statement.executeQuery();

            while (resultQuery.next()) {
                dayList.add(resultQuery.getInt("Day"));
            }

            if (dayList.isEmpty()) {
                System.err.println("ZERO ROWS!");

                return null;
            } else {
                return dayList;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public int getSelectedNotificationID() {
        try {
            this.enstablishConnection();

            String queryString = "SELECT SelectedNotificationID " +
                    "FROM Utils ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getInt("SelectedNotificationID");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return 0;
        } finally {
            this.closeConnection();
        }
    }

    public ArrayList<Integer> getFreeEmployee(int day, int week, String start, String end) {
        try {
            this.enstablishConnection();

            String queryString = "SELECT distinct employeeID " +
                    "FROM Shift " +
                    "WHERE employeeID NOT IN ( " +
                    "       SELECT distinct employeeID " +
                    "       FROM Shift " +
                    "       WHERE (DAY = ? AND WEEK = ? ) OR (ExpectedEntrance = ? AND ExpectedExit = ? ) ) ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, day);
            statement.setInt(2, week);
            statement.setString(3, start);
            statement.setString(4, end);

            resultQuery = statement.executeQuery();

            ArrayList<Integer> free = new ArrayList<>();

            while (resultQuery.next()) {
                free.add(resultQuery.getInt("employeeID"));
            }

            if (free.isEmpty()) {
                System.err.println("ZERO ROWS!");

                return null;
            } else {
                return free;
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public String getInfoMessage() {
        try {
            this.enstablishConnection();

            String queryString = "SELECT InfoMessage " +
                    "FROM Utils ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getString("InfoMessage");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


    public String getErrorMessage() {
        try {
            this.enstablishConnection();

            String queryString = "SELECT ErrorMessage " +
                    "FROM Utils ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            resultQuery = statement.executeQuery();
            resultQuery.next();

            return resultQuery.getString("ErrorMessage");
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return null;
        } finally {
            this.closeConnection();
        }
    }


//--------------------//
//  ADD ROWS METHODS  //
//--------------------//

    /**
     * Insert an Employee in the database
     *
     * @param row Employee tuple to add
     * @return Boolean value for error checking
     */
    public boolean addEmployee(Employee row) {
        try {
            this.enstablishConnection();

            for (int i = 2; i < this.getGlobalUserID() - 1; ++i) {
                if (this.getUser(i).getEmail().equals(row.getEmail())) {
                    System.err.println("Two matching emails!");
                    return false;
                }
            }

            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();

            /* Two insertions needs to be done, the first for the User information
             * the second for the Employee information correlated with the User */
            query.executeUpdate(DatabaseUtils.insertUser(row));
            query.executeUpdate(DatabaseUtils.insertEmployee(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Insert a Notification in the database
     *
     * @param row Notification tuple to add
     * @return Boolean value for error checking
     */
    public boolean addNotification(Notification row) {
        try {
            this.enstablishConnection();

            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();
            query.executeUpdate(DatabaseUtils.insertNotification(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Insert an Abstention Request in the database
     *
     * @param row AbstentionRequest tuple to add
     * @return Boolean value for error checking
     */
    public boolean addAbstentionRequest(AbstentionRequest row) {
        try {
            this.enstablishConnection();

            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();

            /* Two insertions needs to be done, the first for the Notification information
             * the second for the AbstentionRequest information correlated with the Notification */
            query.executeUpdate(DatabaseUtils.insertNotification((Notification) row));
            query.executeUpdate(DatabaseUtils.insertAbstentionRequest(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Insert a Shift in the database
     *
     * @param row Shift tuple to add
     * @return Boolean value for error checking
     */
    public boolean addShift(Shift row) {
        try {
            this.enstablishConnection();

            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();
            query.executeUpdate(DatabaseUtils.insertShift(row));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean addTurnation(SystemControl.GeneralShift row) {
        try {
            this.enstablishConnection();

            String day = DatabaseUtils.SQLvalue(row.getDay());
            String employee = DatabaseUtils.SQLvalue(row.getEmployee());
            String start = DatabaseUtils.SQLvalue(row.getStart());
            String end = DatabaseUtils.SQLvalue(row.getEnd());
            String service = DatabaseUtils.SQLvalue(row.getService());

            String queryString = "INSERT INTO Turnation (Day, EmployeeID, StartShift, EndShift, Service) " +
                    "VALUES (" + day + "," + employee + "," + start + "," + end + "," + service + ")";

            query = DBMS.createStatement();
            query.executeUpdate(queryString);

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }

    public boolean addTrimester(Date[] trimester) {
        try {
            this.enstablishConnection();

            if (trimester.length > 2) {
                System.err.println("Array too long");

                return false;
            }

            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();
            query.executeUpdate(DatabaseUtils.insertTrimester(trimester));

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean pushEmployeeFire(int employeeID) {
        try {
            this.enstablishConnection();

            String queryString = "INSERT INTO FireQueue " +
                    "VALUES ( '" + employeeID + "' ) ";

            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();
            query.executeUpdate(queryString);

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean pushEmployeeHire(int employeeID) {
        try {
            this.enstablishConnection();

            String queryString = "INSERT INTO HireQueue " +
                    "VALUES ( " + employeeID + " ) ";

            /* Create an SQL statement and execute an insertion */
            query = DBMS.createStatement();
            query.executeUpdate(queryString);

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }

//------------------//
//  DELETE METHODS  //
//------------------//

    /**
     * Remove an employee and all the associated data.
     *
     * @param employeeID ID of the employee to remove
     */
    public void deleteEmployee(int employeeID) {
        try {
            this.enstablishConnection();

            String queryString = "DELETE FROM Employee " +
                    "WHERE EmployeeID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, employeeID);

            statement.executeUpdate();

            /* Cascaded deletion */
            deleteNotificationAssociated(employeeID);
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }

    /**
     * Delete all the shifts from the database. Used when making a new
     * trimester shift.
     */
    public void deleteShift() {
        try {
            this.enstablishConnection();

            String queryString = "DELETE FROM Shift " +
                    "WHERE ShiftID = ? ";

            /* Cycle through all the shifts of the week */
            for (int i = 1; i <= 6; ++i) {
                PreparedStatement statement = DBMS.prepareStatement(queryString);
                statement.setInt(1, i);

                statement.executeUpdate();
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Delete all the notifications associated with the employee
     * and also all the abstention requests.
     *
     * @param employeeID The associated employee ID
     */
    public void deleteNotificationAssociated(int employeeID) {
        try {
            this.enstablishConnection();

            /* First retrieve all the notifications ID associated
             * with the employee */
            String queryString = "SELECT NotificationID " +
                    "FROM Notification " +
                    "WHERE ReceiverID = ? ";

            PreparedStatement queryStatement = DBMS.prepareStatement(queryString);
            queryStatement.setInt(1, employeeID);

            resultQuery = queryStatement.executeQuery();

            /* Result container */
            ArrayList<Integer> notifID = new ArrayList<>();

            /* Get all the results */
            while (resultQuery.next()) {
                notifID.add(resultQuery.getInt("NotificationID"));
            }

            /* Delete all the notifications associated with the employee
             * which is the receiver */
            String deleteString = "DELETE FROM Notification " +
                    "WHERE ReceiverID = ? ";

            PreparedStatement deleteStatement = DBMS.prepareStatement(deleteString);
            deleteStatement.setInt(1, employeeID);

            deleteStatement.executeUpdate();

            /* Delete all the requests connected with the deleted
             * notifications */
            for (int i = 0; !notifID.isEmpty(); ++i) {
                // PROBLEM: THIS FUNCTION ALREADY DELETE NOTIFS
                deleteAbstentionRequest(notifID.get(i));
            }
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();
        } finally {
            this.closeConnection();
        }
    }


    /**
     * Remove an abstention request
     *
     * @param requestID The associated notification ID
     */
    public boolean deleteAbstentionRequest(int requestID) {
        try {
            this.enstablishConnection();

            String queryString1 = "DELETE FROM AbstentionRequest " +
                    "WHERE RequestID = ? ";

            PreparedStatement statement1 = DBMS.prepareStatement(queryString1);
            statement1.setInt(1, requestID);

            String queryString2 = "DELETE FROM Notification " +
                    "WHERE NotificationID = ? ";

            PreparedStatement statement2 = DBMS.prepareStatement(queryString2);
            statement2.setInt(1, requestID);

            statement1.executeUpdate();
            statement2.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }

    public boolean deleteNotification(int notificationID) {
        try {
            this.enstablishConnection();

            String queryString = "DELETE FROM Notification " +
                    "WHERE NotificationID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, notificationID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }

//------------------//
//  MODIFY METHODS  //
//------------------//

    public boolean modifyGlobalUserID(int newGlbID) {
        try {
            this.enstablishConnection();
            String queryString = "UPDATE Utils " +
                    "SET GlobalUserID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, newGlbID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }

    public boolean modifySearchEmployeeID(int searchID) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Utils " +
                    "SET SearchID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, searchID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean modifyLoggedUserID(int userID) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Utils " +
                    "SET LoggedUserID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, userID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean modifyGlobalNotificationID(int notificationID) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Utils " +
                    "SET GlobalNotificationID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, notificationID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean modifyPassword(int userID, String newPassword) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE UserApp " +
                    "SET Password = ? " +
                    "WHERE ID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setString(1, newPassword);
            statement.setInt(2, userID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean modifyShiftEntrance(String entrance, int day, int week, int employeeID) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Shift " +
                    "SET DetectedEntrance = ? " +
                    "WHERE (EmployeeID = ?) AND (Day = ?) AND (Week = ?)";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setString(1, entrance);
            statement.setInt(2, employeeID);
            statement.setInt(3, day);
            statement.setInt(4, week);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean modifySelectedNotificationID(int notificationID) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Utils " +
                    "SET SelectedNotificationID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, notificationID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean acceptAbstentionRequest(int notificationID) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE AbstentionRequest " +
                    "SET AcceptanceStatus = true " +
                    "WHERE RequestID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, notificationID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }

    public boolean isAccepted(int requestID) {
        try {
            this.enstablishConnection();

            String queryString = "SELECT AcceptanceStatus " +
                    "FROM AbstentionRequest " +
                    "WHERE RequestID = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, requestID);

            statement.executeQuery();
            resultQuery = statement.executeQuery();

            return resultQuery.next();
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }

    public boolean insertInfoMessage(String message) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Utils " +
                    "SET InfoMessage = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setString(1, message);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean insertErrorMessage(String message) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Utils " +
                    "SET ErrorMessage = ? ";

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setString(1, message);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }


    public boolean changeEmployee(int week, int day, String start, String end, int oldID, int newID) {
        try {
            this.enstablishConnection();

            String queryString = "UPDATE Shift " +
                    "SET EmployeeID = ? " +
                    "WHERE (Week = ? ) AND (Day = ? ) AND (ExpectedEntrance = ? ) AND (ExpectedExit = ? ) AND (EmployeeID = ? ) ";

//            System.out.println(week);
//            System.out.println(day);
//            System.out.println(start);
//            System.out.println(end);
//            System.out.println(oldID);
//            System.out.println(newID);

            PreparedStatement statement = DBMS.prepareStatement(queryString);
            statement.setInt(1, newID);
            statement.setInt(2, week);
            statement.setInt(3, day);
            statement.setString(4, start);
            statement.setString(5, end);
            statement.setInt(6, oldID);

            statement.executeUpdate();

            return true;
        } catch (SQLException queryExc) {
            queryExc.printStackTrace();

            return false;
        } finally {
            this.closeConnection();
        }
    }
}