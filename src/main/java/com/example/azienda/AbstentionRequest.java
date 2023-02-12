package com.example.azienda;

import java.sql.Date;
import java.util.Calendar;

public class AbstentionRequest extends Notification {
    
    public enum AbstentionType {
        VACATION (1),      /* Ferie */
        PARENTAL_LEAVE (2), /* Congedo parentale */
        SICK_LEAVE (3),     /* Malattia */
        STRIKE (4);         /* Sciopero */

        private final int value;

        AbstentionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }

        public String toString() {
            switch (this.getValue()) {
                case 1: return "Vacation";
                case 2: return "Parental Leave";
                case 3: return "Sick Leave";
                case 4: return "Strike";
                default: return "Illegal";
            }
        }
    }

    /* Class attributes */
    private Boolean acceptanceStatus;
    private AbstentionType type;
    private Date startAbstention;
    private Date endAbstention;


    /**
     * Default constructor
     */
    public AbstentionRequest() {
        super();

        this.acceptanceStatus = false;
        this.type = AbstentionType.VACATION;
        this.startAbstention = new Date(0L);
        this.endAbstention = new Date(0L);
    }

    public AbstentionRequest(AbstentionRequest request) {
        super(request.getTitle(), request.getDescription(), request.getReceiverID(), request.getSenderID(), request.getNotifID());
        this.acceptanceStatus = request.getAcceptanceStatus();
        this.startAbstention = request.getStartAbstention();
        this.endAbstention = request.getEndAbstention();
        this.type = request.getType();
    }

    /**
     * Constructor used to create a new AbstentionRequest object. It increments
     * the global Notification ID
     * 
     * @param notf A Notification object
     * @param stat If the request was accepted or not
     * @param type The type of request
     * @param start The start date of the abstention in "YYYY/MM/DD"
     * @param end The end date of the abstention in "YYYY/MM/DD"
     */
    public AbstentionRequest(Notification notf, Boolean stat, int type, Date start, Date end) {
        super(notf.getTitle(), notf.getDescription(), notf.getReceiverID(), notf.getSenderID(), notf.getNotifID());

        this.acceptanceStatus = stat;
        setType(type);

        this.startAbstention = start;
        this.endAbstention = end;
    }


    /**
     * Constructor used to create a new AbstentionRequest object. It does not
     * increment the global Notification ID
     * 
     * @param title The title of the Notification
     * @param description The description of the Notification
     * @param receiverID The receiver id 
     * @param senderID The sender id
     * @param accStat If the request was accepted or not
     * @param type Type of request
     */
    public AbstentionRequest(String title, String description, int receiverID, int senderID, Boolean accStat, AbstentionType type) {
        super(title, description, receiverID, senderID);

        this.acceptanceStatus = accStat;
        this.type = type;
    }

    public AbstentionRequest(String title, String description, int receiverID, int senderID, Boolean accStat, int type, Date start, Date end) {
        super(title, description, receiverID, senderID);

        this.startAbstention = start;
        this.endAbstention = end;
        this.acceptanceStatus = accStat;
        this.setType(type);
    }


//-----------------------//
//  GET AND SET METHODS  //
//-----------------------//

    /**
     * Simple get method for the status of the request
     * 
     * @return The request status
     */
    public Boolean getAcceptanceStatus() {
        return this.acceptanceStatus;
    }

    /**
     * Simple set method for the status of the request
     * 
     * @param acceptanceStatus If the request was accepted (true) or not (false)
     * 
     * @return Class AbstentionRequest handle for subsequent set calls
     */
    public AbstentionRequest setAcceptanceStatus(Boolean acceptanceStatus) {
        this.acceptanceStatus = acceptanceStatus;

        return this;
    }


    /**
     * Simple get method for the type of the request
     * 
     * @return The request type
     */
    public AbstentionType getType() {
        return this.type;
    }

    /**
     * Simple get method for the type of the request
     * 
     * @return The request type as an integer
     */
    public int getTypeInt() {
        return this.type.getValue();
    }


    /**
     * Simple set method for the type of the request
     * 
     * @param type The type of the abstention request
     * 
     * @return Class AbstentionRequest handle for subsequent set calls
     */
    public AbstentionRequest setType(AbstentionType type) {
        this.type = type;

        return this;
    }


    public AbstentionRequest setType(int type) {
        switch (type) {
            case 1:
                this.type = AbstentionType.VACATION;
            break;

            case 2:
                this.type = AbstentionType.PARENTAL_LEAVE;
            break;

            case 3:
                this.type = AbstentionType.SICK_LEAVE;
            break;

            case 4:
                this.type = AbstentionType.STRIKE;
            break;

            default: return null;
        }

        return this;
    }


    public Date getStartAbstention() {
        return new Date(this.startAbstention.getTime());
    }

    public AbstentionRequest setStartAbstention(int startYear, int startMonth, int startDay) {
        /* Convert Date into Calendar */
        Calendar endAbstention = Calendar.getInstance();
        endAbstention.setTime(this.endAbstention);

        if (startYear < endAbstention.get(Calendar.YEAR)) {
            System.err.println("END DATE earlier than START DATE");

            return null;
        } else if (startMonth < endAbstention.get(Calendar.MONTH)) {
            System.err.println("END DATE earlier than START DATE");

            return null;
        } else if (startDay < endAbstention.get(Calendar.DAY_OF_MONTH)) {
            System.err.println("END DATE earlier than START DATE");

            return null;
        }

        /* Create a Calendar object and convert into a Date */
        Calendar startAbstention = Calendar.getInstance();
        startAbstention.set(startYear, startMonth, startDay);
        this.startAbstention.setTime(startAbstention.getTimeInMillis());

        return this;
    }

    public AbstentionRequest setStartAbstention(Date startAbstention) {
        this.startAbstention = startAbstention;

        return this;
    }


    public Date getEndAbstention() {
        return new Date(this.endAbstention.getTime());
    }

    public AbstentionRequest setEndAbstention(int endYear, int endMonth, int endDay) {
        /* Convert Date into Calendar */
        Calendar startAbstention = Calendar.getInstance();
        startAbstention.setTime(this.startAbstention);

        if (endYear < startAbstention.get(Calendar.YEAR)) {
            System.err.println("END DATE earlier than START DATE");

            return null;
        } else if (endMonth < startAbstention.get(Calendar.MONTH)) {
            System.err.println("END DATE earlier than START DATE");

            return null;
        } else if (endDay < startAbstention.get(Calendar.DAY_OF_MONTH)) {
            System.err.println("END DATE earlier than START DATE");

            return null;
        }

        /* Create a Calendar object and convert into a Date */
        Calendar endAbstention = Calendar.getInstance();
        endAbstention.set(endYear, endMonth, endDay);
        this.endAbstention.setTime(endAbstention.getTimeInMillis());

        return this;
    }

    public AbstentionRequest setEndAbstention(Date endAbstention) {
        this.endAbstention = endAbstention;

        return this;
    }

//-------------------//
//  GENERIC METHODS  //
//-------------------//

    public String toString() {
        return "ABSTENTION REQUEST\n" + super.toString() + "\nAcceptance status: " + this.acceptanceStatus.toString() +
                "\nRequest type: " + this.type.toString();
    }
}
