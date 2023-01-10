package Database;

public class Shift {

    /* Class attributes */
    private String startHour, exitHour;
    private String startHourEmployee, exitHourEmployee;
    private int shiftID, employeeID;
    private int serviceID;


    /**
     * Default constructor
     */
    public Shift() {
        this.startHour = this.exitHour = this.startHourEmployee = this.exitHourEmployee = "00:00";
        this.shiftID = this.employeeID = Integer.MAX_VALUE;
        this.serviceID = 1;
    }


    /**
     * Constructor to instantiate a complete Shift object
     * 
     * @param startH Shift start hour
     * @param endH Shift end hour
     * @param startEmp Employee presence detection hour
     * @param endEmp Employee exit detection hour
     * @param shID A number between 1 and 6 that indicates the day of the week
     * @param srvID Service ID
     */
    public Shift(String startH, String endH, String startEmp, String endEmp, int shID, int srvID, int empID) {
        setStartHour(startH);
        setExitHour(endH);
        setStartHourEmployee(startEmp);
        setExitHourEmployee(endEmp);
        setShiftID(shID);
        setServiceID(srvID);
        setEmployeeID(empID);
    }


    public Shift(String startH, String endH, int shID, int srvID, int empID) {
        setStartHour(startH);
        setExitHour(endH);
        setShiftID(shID);
        setServiceID(srvID);
        setEmployeeID(empID);

        this.startHourEmployee = "";
        this.exitHourEmployee = "";
    }


//-----------------------//
//  GET AND SET METHODS  //
//-----------------------//

    /**
     * Simple get method for the starting hour of the shift
     * 
     * @return The starting hour in 24-hour format
     */
    public String getStartHour() {
        return this.startHour;
    }

    /**
     * Simple set method for the starting hour of the shift
     * 
     * @param startHour The starting hour of the shift in 24-hour format
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setStartHour(String startHour) {
        if (checkTime(startHour)) {
            this.startHour = startHour;

            return this;
        } else {
            return null;
        }
    }


    /**
     * Simple get method for the id of the associated employee
     *
     * @return The value of the identification code of the associated employee
     */
    public int getEmployeeID() {
        return this.employeeID;
    }

    /**
     * Simple set method for the id of the associated employee
     *
     * @param employeeID Id of the associated employee
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setEmployeeID(int employeeID) {
        if (employeeID == 0) {
            return null;
        } else {
            this.employeeID = employeeID;

            return this;
        }
    }

    /**
     * Simple get method for the ending hour of the shift
     * 
     * @return The ending hour in 24-hour format
     */
    public String getExitHour() {
        return this.exitHour;
    }

    /**
     * Simple set method for ending hour of the shift
     * 
     * @param exitHour The ending hour of the shift in 24-hour format
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setExitHour(String exitHour) {
        if (checkTime(exitHour)) {
            this.exitHour = exitHour;

            return this;
        } else {
            return null;
        }
    }


    /**
     * Simple get method for the detected starting hour
     * 
     * @return The detected starting hour of the Employee in 24-hour format
     */
    public String getStartHourEmployee() {
        return this.startHourEmployee;
    }

    /**
     * Simple set method for the detected starting hour 
     * 
     * @param startHourEmployee The Employee's detected starting hour of the shift in 24-hour format
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setStartHourEmployee(String startHourEmployee) {
        if (checkTime(startHourEmployee)) {
            this.startHourEmployee = startHourEmployee;

            return this;
        } else {
            return null;
        }
    }


    /**
     * Simple get method for the ending hour of the shift
     * 
     * @return The ending hour in 24-hour format
     */
    public String getExitHourEmployee() {
        return this.exitHourEmployee;
    }

    /**
     * Simple set method for ending hour of the shift
     * 
     * @param exitHourEmployee The ending hour of the shift in 24-hour format
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setExitHourEmployee(String exitHourEmployee) {
        if (checkTime(exitHourEmployee)) {
            this.exitHourEmployee = exitHourEmployee;

            return this;
        } else {
            return null;
        }
    }


    /**
     * Simple get method for the identification code of the shift
     * 
     * @return The shift ID
     */
    public int getShiftID() {
        return this.shiftID;
    }

    /**
     * Simple set method for the identification code of the shift 
     * 
     * @param shiftID An integer value between 1 and 6 that indicates the day of the week
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setShiftID(int shiftID) {
        if ((shiftID > 0) && (shiftID < 7)) {
            this.shiftID = shiftID;

            return this;
        } else { 
            return null;
        }
    }


    /**
     * Simple get method for the service ID
     * 
     * @return The service ID
     */
    public int getServiceID() {
        return this.serviceID;
    }

    /**
     * Simple set method for the ID of the service
     * 
     * @param serviceID An integer value between 1 and 4 that indicates the ID of the service
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setServiceID(int serviceID) {
        if ((serviceID >= 1) || (serviceID <= 4)) {
            this.serviceID = serviceID;

            return this;
        } else {
            return null;
        }
    }

//-------------------//
//  GENERAL METHODS  //
//-------------------//

    /**
     * This method is used internally to check the time format
     * 
     * @param time Time expressed in a 24-hour format (ex: 11:45)
     * @return The check result in boolean
     */
    private Boolean checkTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 1));
        int minutes = Integer.parseInt(time.substring(3, 4));

        if (time.length() > 6) {
            return false;
        }

        /* Check if the hour is bigger than 23:59 */
        if (hour > 23 || hour < 0) {
            System.err.println("Illegal hour inserted: " + Integer.toString(hour));

            return false;
        } else if (minutes > 59 || minutes < 0) {
            System.err.println("Illegal minute inserted: %d " + Integer.toString(minutes));
            
            return false;
        } 

        return true;
    }


//-------------------//
//  GENERIC METHODS  //
//-------------------//

    public String toString() {
        return "SHIFT:\nShiftID: " + this.shiftID + "\nEmployeeID: " + this.employeeID + "\nServiceID " +
                this.serviceID + "\nStart Hour: " + this.startHour + "\nExit Hour: " + this.exitHour +
                "\nStart Hour Employee: " + this.startHourEmployee + "\nExit Hour Employee: " + this.exitHourEmployee;
    }
}
