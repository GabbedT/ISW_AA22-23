package com.azienda.entity;

import com.azienda.database.DatabaseControl;

public class Notification {
    
    /* Class attributes */
    private int notifID;
    private String title;
    private String description;
    private int receiverID, senderID;


    /**
     * Default Notification constructor
     */
    public Notification() {
        this.title = this.description = "Default";
        this.receiverID = this.senderID = this.notifID = Integer.MAX_VALUE;
    }


    /**
     * Constructor used to wrap the data retrieved from database
     * 
     * @param title The title of the Notification
     * @param description The description of the Notification
     * @param receiverID The receiver User class
     * @param senderID The sender User class
     * @param notifID The notification ID
     */
    public Notification(String title, String description, int receiverID, int senderID, int notifID) {
        this.notifID = notifID;

        this.title = title;
        this.description = description;

        this.receiverID = receiverID;
        this.senderID = senderID;
    }


    /**
     * Constructor to instantiate a new Notification object to add to the DBMS, 
     * this method increment the global notification ID counter and assign it to
     * the new Notification ID object
     * 
     * @param title The title of the Notification
     * @param description The description of the Notification
     * @param receiverID The receiver ID
     * @param senderID The sender ID
     */
    public Notification(String title, String description, int receiverID, int senderID) {
        DatabaseControl DBMS = new DatabaseControl();

        this.notifID = DBMS.getGlobalNotificationID();

        this.title = title;
        this.description = description;

        this.receiverID = receiverID;
        this.senderID = senderID;
    }


//-----------------------//
//  GET AND SET METHODS  //
//-----------------------//

    public Notification setNotificationID(int notifID) {
        this.notifID = notifID;

        return this;
    }

    /**
     * Simple get method for title
     * 
     * @return The title of the Notification 
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Simple set method for title
     * 
     * @param title The title of the Notification
     * 
     * @return Class Notification handle for subsequent set calls
     */
    public Notification setTitle(String title) {
        this.title = title;

        return this;
    }


    /**
     * Simple get method for description
     * 
     * @return The description of the Notification 
     */
    public String getDescription() {
        return this.description;
    }
    
    /**
     * Simple set method for description
     * 
     * @param description The description of the Notification
     * 
     * @return Class Notification handle for subsequent set calls
     */
    public Notification setDescription(String description) {
        this.description = description;

        return this;
    }


    /**
     * Simple get method for the receiver
     * 
     * @return The receiver ID 
     */
    public int getReceiverID() {
        return this.receiverID;
    }

    /**
     * Simple get method for receiver
     * 
     * @return Class Notification handle for subsequent set calls
     */
    public Notification setReceiverID(int receiverID) {
        this.receiverID = receiverID;

        return this;
    }


    /**
     * Simple get method for sender
     * 
     * @return The sender User of the Notification  
     */
    public int getSenderID() {
        return this.senderID;
    }

    /**
     * Simple get method for sender
     * 
     * @return Class Notification handle for subsequent set calls, returns null if any error occurred
     */
    public Notification setSenderID(int senderID) {
        this.senderID = senderID;

        return this;
    }


    /**
     * Simple get method for the Notification ID
     * 
     * @return The identification code of the Notification  
     */
    public int getNotifID() {
        return this.notifID;
    }

}
