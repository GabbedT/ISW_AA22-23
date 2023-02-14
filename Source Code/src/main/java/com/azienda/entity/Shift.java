package com.azienda.entity;

public class Shift {

    /* Class attributes */
    private String detectedEntrance, detectedExit;
    private String expectedEntrance, expectedExit;
    private int day, week, employeeID;
    private char serviceID;


    /**
     * Default constructor
     */
    public Shift() {
        this.detectedEntrance = this.detectedExit = "00:00";
        this.day = this.employeeID = Integer.MAX_VALUE;
        this.serviceID = 'A';
    }

    public Shift(int day, int employeeID) {
        this.detectedEntrance = this.detectedExit = "";
        this.expectedEntrance = this.expectedExit = "";
        this.serviceID = 'A';
        this.day = day;
        this.employeeID = employeeID;
    }


    /**
     */
    public Shift(String enter, String exit, int day, int week, char service, int employeeID) {
        setExpectedEntrance(enter);
        setExpectedExit(exit);
        setService(service);
        setEmployeeID(employeeID);
        this.day = day;
        this.week = week;
    }

    public Shift(String expEntr, String dectEntr, String expExit, String dectExit, int day, int week, char service, int employeeID) {
        setExpectedEntrance(expEntr);
        setExpectedExit(expExit);
        setDetectedEntrance(dectEntr);
        setDetectedExit(dectExit);
        setService(service);
        setEmployeeID(employeeID);

        this.day = day;
        this.week = week;
    }


//-----------------------//
//  GET AND SET METHODS  //
//-----------------------//

    /**
     * Simple get method for the starting hour of the shift
     * 
     * @return The starting hour in 24-hour format
     */
    public String getDetectedEntrance() {
        return this.detectedEntrance;
    }

    public String getExpectedEntrance() {
        return expectedEntrance;
    }

    /**
     * Simple set method for the starting hour of the shift
     * 
     * @param entrance The starting hour of the shift in 24-hour format
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setDetectedEntrance(String entrance) {
        if (checkTime(entrance)) {
            this.detectedEntrance = entrance;

            return this;
        } else {
            return null;
        }
    }

    public Shift setExpectedEntrance(String expectedEntrance) {
        if (checkTime(expectedEntrance)) {
            this.expectedEntrance = expectedEntrance;

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
    public String getDetectedExit() {
        return this.detectedExit;
    }

    public String getExpectedExit() {
        return expectedExit;
    }

    /**
     * Simple set method for ending hour of the shift
     * 
     * @param exit The ending hour of the shift in 24-hour format
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setDetectedExit(String exit) {
        if (checkTime(exit)) {
            this.detectedExit = exit;

            return this;
        } else {
            return null;
        }
    }

    public Shift setExpectedExit(String expectedExit) {
        if (checkTime(expectedExit)) {
            this.expectedExit = expectedExit;

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
    public int getDay() {
        return this.day;
    }

    /**
     * Simple set method for the identification code of the shift 
     * 
     * @param day An integer value between 1 and 7 that indicates the day of the week
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setDay(int day) {
        if ((day > 0) && (day < 8)) {
            this.day = day;

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
    public char getService() {
        return this.serviceID;
    }

    /**
     * Simple set method for the ID of the service
     * 
     * @param serviceID An integer value between 1 and 4 that indicates the ID of the service
     * 
     * @return Class Shift handle for subsequent set calls, returns null if any error occurred
     */
    public Shift setService(char serviceID) {
        if (serviceID >= 'A' && serviceID <= 'D') {
            this.serviceID = serviceID;

            return this;
        } else {
            return null;
        }
    }

    public int getWeek() {
        return this.week;
    }

    public Shift setWeek(int week) {
        this.week = week;

        return this;
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


    public int getHours(int startOrEnd) {
        if (startOrEnd == 0) {
            return Integer.parseInt(this.detectedEntrance.substring(0, 2));
        } else if (startOrEnd == 1) {
            return Integer.parseInt(this.detectedExit.substring(0, 2));
        } else {
            return 0;
        }
    }

    public int getMinutes(int startOrEnd) {
        if (startOrEnd == 0) {
            return Integer.parseInt(this.detectedEntrance.substring(3));
        } else if (startOrEnd == 1) {
            return Integer.parseInt(this.detectedExit.substring(3));
        } else {
            return 0;
        }
    }

//-------------------//
//  GENERIC METHODS  //
//-------------------//

    public String toString() {
        return "SHIFT:\nShiftID: " + this.day + "\nEmployeeID: " + this.employeeID + "\nServiceID " +
                this.serviceID + "\nStart Hour: " + this.detectedEntrance + "\nExitControl Hour: " + this.detectedExit;
    }
}
