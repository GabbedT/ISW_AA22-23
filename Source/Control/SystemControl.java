package Control;

import Database.Database;
import Database.Shift;

import java.util.Calendar;
import java.util.Date;

public class SystemControl {

    Database DBMS;
    public static void main(String[] args) {
        Shift shift = new Shift("14:57", "22:00", 1, 1, 1);

        SystemControl ctrl = new SystemControl();
        if (ctrl.checkStartShiftTime(shift)) {
            System.out.println("In time!");
        } else {
            System.out.println("Not in time :(");
        }
    }

    public SystemControl() {
        DBMS = new Database("javaapp_db", "postgres", "DBMS_Password");
    }


    /**
     * Check if the current trimester has ended
     *
     * @return True if the trimester has ended
     */
    public boolean checkEndTrimester() {
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
    public boolean checkStartShiftTime(Shift shift) {
        /* Time is in format HH:MM */

        /* Get the starting time of the shift */
        int shiftHour = Integer.parseInt(shift.getStartHour().substring(0, 2));
        int shiftMinutes = Integer.parseInt(shift.getStartHour().substring(3, 5));

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
    private boolean compareTime(int hour1, int minute1, int hour2, int minute2) {
        if (hour1 > hour2) {
            return false;
        } else if (hour1 < hour2) {
            return true;
        } else {
            return minute1 <= minute2;
        }
    }
}
