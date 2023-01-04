package Database;

public class Shift {

    /* Class attributes */
    private String startHour, exitHour;
    private String startHourEmployee, exitHourEmployee;
    private int shiftID;
    private int priorityLevel;


    /**
     * Default constructor
     */
    public Shift() {
        startHour = exitHour = startHourEmployee = exitHourEmployee = "00:00";
        shiftID = Integer.MAX_VALUE;
        priorityLevel = 1;
    }


    /**
     * Constructor to instantiate a complete Shift object
     * 
     * @param startH Shift start hour
     * @param endH Shift end hour
     * @param startEmp Employee presence detection hour
     * @param endEmp Employee exit detection hour
     * @param shID A number between 1 and 6 that indicates the day of the week
     * @param prioLvl Priority of the shift
     */
    public Shift(String startH, String endH, String startEmp, String endEmp, int shID, int prioLvl) {
        setStartHour(startH);
        setExitHour(endH);
        setStartHourEmployee(startEmp);
        setExitHourEmployee(endEmp);
        setShiftID(shID);
        setPriorityLevel(prioLvl);
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
     * Simple get method for the priority of the shift
     * 
     * @return The shift priority
     */
    public int getPriorityLevel() {
        return this.priorityLevel;
    }

    /**
     * Simple set method for the priority level of the shift 
     * 
     * @param priorityLevel An integer value between 1 and 4 that indicates level priority of the job associated
     * with the shift
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setPriorityLevel(int priorityLevel) {
        if ((priorityLevel > 1) || (priorityLevel < 4)) {
            this.priorityLevel = priorityLevel;

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
}
