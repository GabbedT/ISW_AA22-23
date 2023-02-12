package com.example.azienda;


import java.sql.SQLException;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public final class SystemControl {

    public static void ciao(String[] args) {
        Database DBMS = new Database();

//        Integer[][] employeeList = new Integer[4][];
//        ArrayList<Integer> DBemployeeListA = DBMS.getEmployee('A');
//        System.out.println(DBemployeeListA.toString());
//
//        ArrayList<Integer> DBemployeeListB = DBMS.getEmployee('B');
//        System.out.println(DBemployeeListB.toString());
//
//        ArrayList<Integer> DBemployeeListC = DBMS.getEmployee('C');
//        System.out.println(DBemployeeListC.toString());
//
//        ArrayList<Integer> DBemployeeListD = DBMS.getEmployee('D');
//        System.out.println(DBemployeeListD.toString());
//
//        employeeList[0] = new Integer[DBemployeeListA.size()];
//        DBemployeeListA.toArray(employeeList[0]);
//
//        employeeList[1] = new Integer[DBemployeeListB.size()];
//        DBemployeeListB.toArray(employeeList[1]);
//
//        employeeList[2] = new Integer[DBemployeeListC.size()];
//        DBemployeeListC.toArray(employeeList[2]);
//
//        employeeList[3] = new Integer[DBemployeeListD.size()];
//        DBemployeeListD.toArray(employeeList[3]);
//
//
//        SystemControl.generateSchedule(employeeList);
    }

    public static void main(String[] args) {
        Database DBMS = new Database();

//        for (int i = 2; i < DBMS.getGlobalUserID(); ++i) {
//            System.out.println("ITER " + i);
//            SystemControl.fillShift(i);
//        }
        System.out.println(SystemControl.weekOfTrimester(true));
        SystemControl.changeShift(DBMS.getAbstentionRequest(4));
    }

    private SystemControl() { }


//======================================================================
//  TIME CHECK
//======================================================================

    public static void updateTrimester() {
        Database DBMS = new Database();

        Calendar time = Calendar.getInstance();

        /* Set the time of the end of the trimester */
        time.setTimeInMillis(System.currentTimeMillis());

        /* Increment the day of trimester end and set the trimester */
        time.add(Calendar.DATE, 1);
        Date startDate = new Date(time.getTimeInMillis());

        /* Increment the month by 3 and set the end of the trimester */
        time.add(Calendar.MONTH, 3);
        Date endDate = new Date(time.getTimeInMillis());

        /* Cast into a java SQL */
        java.sql.Date[] trimester = new java.sql.Date[2];
        trimester[0] = new java.sql.Date(startDate.getTime());
        trimester[1] = new java.sql.Date(endDate.getTime());

        if (!DBMS.addTrimester(trimester)) {
            System.err.println("ERROR");
        }
    }

    public static int dayOfWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }
    public static int weekOfTrimester(boolean current) {
        Database DBMS = new Database();

        Instant d1i = Instant.ofEpochMilli(DBMS.getTrimester()[0].getTime());
        Instant d2i = Instant.ofEpochMilli(DBMS.getTrimester()[1].getTime());

        if (current) {
            d2i = Instant.ofEpochMilli(System.currentTimeMillis());
        }

        LocalDateTime startDate = LocalDateTime.ofInstant(d1i, ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(d2i, ZoneId.systemDefault());

        return (int) (ChronoUnit.WEEKS.between(startDate, endDate) + 1);
    }

    public static int weekOfTrimester(Date date) {
        Database DBMS = new Database();

        Instant d1i = Instant.ofEpochMilli(DBMS.getTrimester()[0].getTime());
        Instant d2i = Instant.ofEpochMilli(date.getTime());


        LocalDateTime startDate = LocalDateTime.ofInstant(d1i, ZoneId.systemDefault());
        LocalDateTime endDate = LocalDateTime.ofInstant(d2i, ZoneId.systemDefault());

        return (int) (ChronoUnit.WEEKS.between(startDate, endDate) + 1);
    }

    /**
     * Check if the current trimester has ended
     *
     * @return True if the trimester has ended
     */
    public static boolean checkEndTrimester() {
        Database DBMS = new Database();

        long currentTime = System.currentTimeMillis();
        Date endTrimester = DBMS.getTrimester()[1];

        return currentTime >= endTrimester.getTime();
    }


    /**
     * Check if the employee is in time
     *
     * @param shift Shift object for comparison
     *
     * @return True if the employee is in time
     */
    public static boolean checkStartShiftTime(Shift shift) {
        Database DBMS = new Database();

        /* Time is in format HH:MM */

        /* Get the starting time of the shift: Start at 08:00 -> 07:50 */
        int shiftHour = Integer.parseInt(DBMS.getShiftStart(SystemControl.dayOfWeek(), DBMS.getLoggedUserID()).substring(0, 2)) - 1;
        int shiftMinutes = 50;

        System.out.println("Start shift: " + shiftHour + ":" + shiftMinutes + "\n\n");

        Calendar currentTime = Calendar.getInstance();

        /* Get the current time of the employee presence */
        int startHourEmp = currentTime.get(Calendar.HOUR_OF_DAY);
        int startMinutesEmp = currentTime.get(Calendar.MINUTE);

        System.out.println("Employee start shift: " + startHourEmp + ":" + startMinutesEmp + "\n\n");

        return compareTime(startHourEmp, startMinutesEmp, shiftHour, shiftMinutes);
    }


    /**
     *  Compare if the time 1 is earlier than time 2
     *
     *  @return True if the comparison is correct
     */
    private static boolean compareTime(int hour1, int minute1, int hour2, int minute2) {
        if (hour1 > hour2) {
            return false;
        } else if (hour1 < hour2) {
            return true;
        } else {
            return minute1 <= minute2;
        }
    }


    public static String generatePassword() {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;

        Random random = new Random();
        char[] password = new char[10];

        for (int i = 0; i < 10; ++i) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }

        return String.valueOf(password);
    }


    public static boolean checkOverlapDate(Date dateStart1, Date dateEnd1, Date dateStart2, Date dateEnd2) {
        if ((dateStart1.before(dateEnd2) && dateStart1.after(dateStart2)) || (dateStart2.before(dateEnd1) && dateStart2.after(dateStart1))) {
            /* Check if the start of the abstention is in the period of another
             * abstention request (of the same user) */
            return true;
        } else if ((dateEnd1.before(dateEnd2) && dateEnd1.after(dateStart2)) || (dateEnd2.before(dateEnd1) && dateEnd2.after(dateStart1))) {
            /* Check if the end of the abstention is in the period of another
             * abstention request (of the same user) */
            return true;
        } else if (dateStart1.equals(dateStart2) && dateEnd1.equals(dateEnd2)) {
            /* Check if the two periods are the same */
            return true;
        }

        return false;
    }

//=============================================
//  SHIFT
//=============================================

    public static class GeneralShift {
        private String timeSlot;
        private int employee;
        private int day;
        private char service;

        public GeneralShift(int timeSlot, int employee, int day, char service) {
            if (timeSlot == 0) {
                this.timeSlot = "08:00 - 14:00";
            } else {
                this.timeSlot = "14:00 - 22:00";
            }

            this.employee = employee;
            this.day = day;
            this.service = service;

            System.out.println("CREATED SHIFT FOR SERVICE " + service + " FOR THE EMPLOYEE: " + employee + " IN DAY: " + day + " FROM - TO: " + this.timeSlot);
        }

        public String getStart() {
            return this.timeSlot.substring(0, 5);
        }

        public String getEnd() {
            return this.timeSlot.substring(8);
        }

        public int getEmployee() {
            return this.employee;
        }

        public int getDay() {
            return this.day;
        }

        public char getService() {
            return this.service;
        }
    }

    /* First index of the array is the service */
    public static void generateSchedule(Integer[][] employees) {
        int maxLength = 0;
        GeneralShift[][][] shift;

        for (int i = 0; i < 4; ++i) {
            if (maxLength < employees[i].length) {
                maxLength = employees[i].length;
            }
        }

        /* First index: Services
         * Second index: Employee
         * Third index: Day
         *
         * Example: shift[0][2][4] -> The shift of the 5-th day of the 3-th employee of the A service
         */
        shift = new GeneralShift[4][maxLength][5];

        int timeSlot = 0; // 0 = AM, 1 = PM
        int days = 1; // 1 = MONDAY, 7 = SUNDAY
        char service = 'A';

        for (int i = 0; i < 4; ++i) { // Services
            for (int j = 0; j < employees[i].length; ++j) { // Employees
                for (int k = 0; k < 5; ++k) { // Days
                    shift[i][j][k] = new GeneralShift(timeSlot, employees[i][j], days, service);
                    // Increment and wrap around
                    days = (days == 7) ? 1 : (days + 1);
                }
                /* If timeslot is 0 it becomes 1 changing every iteration */
                timeSlot = (timeSlot == 0) ? 1 : 0;
            }
            ++service;
        }

        Database DBMS = new Database();

        for (int i = 0; i < 4; ++i) { // Services
            for (int j = 0; j < employees[i].length; ++j) { // Employees
                for (int k = 0; k < 5; ++k) { // Days
                    DBMS.addTurnation(shift[i][j][k]);
                }
            }
        }
    }

    public static void fillShift(int employeeID) {
        Database DBMS = new Database();

        ArrayList<Integer> daysList = DBMS.getShiftDays(employeeID);
        Shift[] weeklyShift = new Shift[5];

        for (int i = 0; i < daysList.size(); ++i) {
            weeklyShift[i] = DBMS.getShift(daysList.get(i), employeeID);
        }


        for (int i = 1; i <= weekOfTrimester(false); ++i) {
            for (int j = 0; j < weeklyShift.length; ++j) {
                /* Increment the week every 5 days of shifts */
                weeklyShift[j].setWeek(i);

                if (!DBMS.addShift(weeklyShift[j])) {
                    System.err.println("ERROR ON ADDING SHIFT");
                }
            }
        }
    }


    /*
     * Precondition: All the requests have been accepted by the employer
     * PSEUDOCODE
     *
     * 1) Get the abstention period (number of weeks and start, end days)
     * 2) Get the working hours
     * 3) LOOP: The number of weeks
     *      1) LOOP: The number of days on the current week (keep track of start and end days)
     *          1) Retrieve all the employees that don't work on that day
     *          2) Select a random employee in that list
     *          3) Check that the selected employee doesn't have an abstention in that period
     */
    public static void changeShift(AbstentionRequest request) {
        Database DBMS = new Database();

        Employee requestingEmployee = DBMS.getEmployee(DBMS.getUser(request.getSenderID()));

        /* DEBUG */
        System.out.println("START: " + request.getStartAbstention().toString());
        System.out.println("END: " + request.getEndAbstention().toString());

        /* Calculate the number of days and weeks between the two dates */
        Instant startInstant = Instant.ofEpochMilli(request.getStartAbstention().getTime());
        Instant endInstant = Instant.ofEpochMilli(request.getEndAbstention().getTime());

        LocalDate startDate = LocalDate.ofInstant(startInstant, ZoneId.systemDefault());
        LocalDate endDate = LocalDate.ofInstant(endInstant, ZoneId.systemDefault());

        int weeksBetween = 1;

        for (LocalDate i = startDate; i.isBefore(endDate); i = i.plusDays(1)) {
            if (i.getDayOfWeek() == DayOfWeek.SUNDAY) {
                ++weeksBetween;
            }
        }

        /* DEBUG */
        System.out.println(weeksBetween);

        /* Get the start day number */
        int startDay = request.getStartAbstention().getDay();

        /* Get the end day number */
        int endDay = request.getEndAbstention().getDay();

        /* DEBUG */
        System.out.println("START DAY: " + startDay + "\nEND DAY: " + endDay);
        
        /* Inner cycle variables */
        int startDayWeek;
        int endDayWeek;

        ArrayList<Integer> freeEmployees = new ArrayList<>();

        int selectedEmployee;
        ArrayList<AbstentionRequest> selectedRequest;

        Calendar c = new GregorianCalendar();
        LocalDate date = startDate;

        /* Outer cycle go through every week of the period */
        for (int i = 1; i <= weeksBetween; ++i) {
            /* DEBUG */
            System.out.println("ITERATION WEEK: " + i);

            /* Retrieve the working days of the sender of the request in that precise week */
            c.setTime(request.getStartAbstention());
            c.add(Calendar.WEEK_OF_MONTH, i - 1);
            ArrayList<Integer> workingDaysTemp = DBMS.getShiftDays(request.getSenderID(), weekOfTrimester(c.getTime()));
            ArrayList<Integer> workingDays = new ArrayList<>(Collections.nCopies(7, 0));

            if (workingDaysTemp == null) {
                System.err.println("Exit procedure, no working days available!");
                return;
            }

            for (int j = 0; j < 5; ++j) {
                /* Sort */
                workingDays.set(workingDaysTemp.get(j) - 1, workingDaysTemp.get(j));
            }
            System.out.println(workingDays);

            if (i == 1) {
                /* First cycle: start from the start abstention day, finish sunday or the
                 * end day if the abstention finish in the same week */
                startDayWeek = startDay;
                endDayWeek = (i == weeksBetween) ? endDay : 8;
            } else if (i == weeksBetween) {
                /* Last cycle: start from monday finish in the last day */
                startDayWeek = 1;
                endDayWeek = endDay + 1;
            } else {
                /* Cycle through the whole week */
                startDayWeek = 1;
                endDayWeek = 8;
            }

            /* DEBUG */
            System.out.println("FIRST CYCLE: " + startDayWeek + " ==> " + endDayWeek + "\n");

            for (int j = startDayWeek; j < endDayWeek; ++j) {
                date = date.plusDays(1);

                /* DEBUG */
                System.out.println("ITERATION DAY: " + j);

                if (workingDays.get(j - 1) == 0) {
                    /* Here the employee doesn't work so no
                     * computation is needed */
                    System.out.println("\n\n");
                    continue;
                }

                String startHour = DBMS.getTurnation(workingDays.get(j - 1), requestingEmployee.getID()).getExpectedEntrance();
                String endHour = DBMS.getTurnation(workingDays.get(j - 1), requestingEmployee.getID()).getExpectedExit();

                System.out.println(startHour);
                System.out.println(endHour);

                freeEmployees = DBMS.getFreeEmployee(j, i, startHour, endHour);

                int breakFlag = 0;

                while (breakFlag == 0) {
                    /* Select a random free employee from the list */
                    Random randomGenerator = new Random();
                    selectedEmployee = freeEmployees.get(randomGenerator.nextInt(freeEmployees.size()));

                    /* DEBUG */
                    System.out.println("RANDOM EMPLOYEE SELECTED: " + selectedEmployee);

                    /* Get all the abstention requests sent by the selected employee */
                    selectedRequest = DBMS.getAbstentionRequest(selectedEmployee, false);

                    if (selectedRequest != null) {
                        /* Check if the employee has any abstention period that correspond */
                        for (int k = 0; k < selectedRequest.size(); ++k) {
                            /* If overlap break loop */
                            if (checkOverlapDate(request.getStartAbstention(), request.getEndAbstention(), selectedRequest.get(k).getStartAbstention(), selectedRequest.get(k).getEndAbstention())) {
                                System.err.println("OVERLAPPED");
                                break;
                            }

                            /* If no overlap until the last cycle set the flag and insert the employee into the shift */
                            breakFlag = (k == selectedRequest.size()) ? 1 : 0;
                            System.out.println("SWAP \nWEEK: " + new Date(c.getTimeInMillis()) + "\nDAY: " + j);
                            DBMS.changeEmployee(SystemControl.weekOfTrimester(new Date(c.getTimeInMillis())), j, startHour, endHour, requestingEmployee.getID(), selectedEmployee);

                            String description = "Gentle employee: you have been assigned for an overtime shift in " + date + ".\nThe shift will start at " + startHour + " and end at " + endHour;
                            SystemControl.sendNotification("Overtime Shift", description, selectedEmployee, 1);
                        }
                    } else {
                        breakFlag = 1;

                        /* Insert employee */
                        if (DBMS.changeEmployee(SystemControl.weekOfTrimester(new Date(c.getTimeInMillis())), j, startHour, endHour, requestingEmployee.getID(), selectedEmployee)) {
                            System.out.println("INSERTED IN:");
                            System.out.println("WEEK: " + SystemControl.weekOfTrimester(new Date(c.getTimeInMillis())) + "\nDAY: " + j);
                        }

                        String description = "Gentle employee: you have been assigned for an overtime shift in " + date + ".\nThe shift will start at " + startHour + " and end at " + endHour;
                        SystemControl.sendNotification("Overtime Shift", description, selectedEmployee, 1);
                    }
                }
                System.out.println("\n\n\n");
            }
        }
    }


    public static void sendNotification(String title, String description, int receiverID, int senderID) {
        Notification sendNotification = new Notification(title, description, receiverID, senderID);
        Database DBMS = new Database();

        if (DBMS.addNotification(sendNotification)) {
            DBMS.modifyGlobalNotificationID(DBMS.getGlobalNotificationID() + 1);
        } else {
            System.err.println("ERROR WHILE SENDING NOTIFICATION!");
        }
    }
}
