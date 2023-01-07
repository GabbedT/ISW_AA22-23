package Database;

public final class DatabaseUtils {

    /* DBMS Tables */
    private static final String userFields = "(ID, Name, Surname, Email, Password, Gender)\n";
    private static final String employeeFields = "(EmployeeID, Salary, ExpectedWorkHours, FinalWorkHours)\n";
    private static final String notificationFields = "(NotificationID, Title, Description, ReceiverID, SenderID)\n";
    private static final String abstentionRequestFields = "(RequestID, AcceptanceStatus, RequestType)\n";
    private static final String shiftFields = "(ShiftID, StartHour, ExitHour, StartHourEmployee, ExitHourEmployee, PriorityLevel)\n";



    /**
     * Insert a User row into the database
     * 
     * @param usr A User object that holds the fields
     * @return A string for the SQL statement
     */
    public static String insertUser(User usr) {
        return "INSERT INTO UserApp " + userFields + 
               "VALUES (" + SQLvalue(usr.getID()) + "," + SQLvalue(usr.getName()) + "," + SQLvalue(usr.getSurname()) + "," + SQLvalue(usr.getEmail()) +
                "," + SQLvalue(usr.getPassword()) + "," + SQLvalue(usr.getGender()) + ")";
    } 

    /**
     * Insert an Employee row into the database
     * 
     * @param emp An Employee object that holds the fields
     * @return A string for the SQL statement
     */
    public static String insertEmployee(Employee emp) {
        return "INSERT INTO Employee " + employeeFields + 
               "VALUES (" + SQLvalue(emp.getID()) + "," + SQLvalue(emp.getSalary()) + "," + SQLvalue(emp.getExpectedWorkHours()) + "," + SQLvalue(emp.getFinalWorkHours()) + ")";
    }

    /**
     * Insert a Notification row into the database
     * 
     * @param notif A Notification object that holds the fields
     * @return A string for the SQL statement
     */
    public static String insertNotification(Notification notif) {
        return "INSERT INTO Notification " + notificationFields + 
               "VALUES (" + SQLvalue(notif.getNotifID()) + "," + SQLvalue(notif.getTitle()) + "," + SQLvalue(notif.getDescription()) +
                "," + SQLvalue(notif.getReceiverID()) + "," + SQLvalue(notif.getSenderID()) + ")";
    }

    /**
     * Insert a AbstentionRequest row into the database
     * 
     * @param req An AbstentionRequest object that holds the fields
     * @return A string for the SQL statement
     */
    public static String insertAbstentionRequest(AbstentionRequest req) {
        return "INSERT INTO AbstentionRequest " + abstentionRequestFields + 
               "VALUES (" + SQLvalue(req.getNotifID()) + "," + SQLvalue(req.getAcceptanceStatus()) + "," + SQLvalue(req.getTypeInt()) + ")";
    }

    /**
     * Insert a Shift row into the database
     * 
     * @param shift A Shift object that holds the fields
     * @return A string for the SQL statement
     */
    public static String insertShift(Shift shift) {
        return "INSERT INTO Shift" + shiftFields + 
               "VALUES (" + SQLvalue(shift.getShiftID()) + "," + SQLvalue(shift.getStartHour()) + "," + SQLvalue(shift.getExitHour()) +
                "," + SQLvalue(shift.getStartHourEmployee()) + "," + SQLvalue(shift.getExitHourEmployee()) + "," + SQLvalue(shift.getPriorityLevel()) + ")";
    }


    /**
     * Insert a string between two apexes: 'str'
     * 
     * @param str String to convert
     * @return A string that is legal in SQL syntax 
     */
    private static String SQLvalue(String str) {
        return "'" + str + "'";
    }

    /**
     * Insert a character between two apexes: 'chr'
     * 
     * @param chr Character to convert
     * @return A string that is legal in SQL syntax 
     */
    private static String SQLvalue(char chr) {
        return "'" + chr + "'";
    }

    /**
     * Less verbose way of converting an integer into a string
     * 
     * @param val Integer value to convert
     * @return A string that represent the integer value
     */
    private static String SQLvalue(int val) {
        return "'" + val + "'";
    }

    /**
     * Less verbose way of converting a boolean into a string
     * 
     * @param val Boolean value to convert
     * @return A string that represent the boolean value
     */
    private static String SQLvalue(Boolean val) {
        return "'" + Boolean.toString(val).toUpperCase() + "'";
    }
}
