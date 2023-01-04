package Database;

public class AbstentionRequest extends Notification {
    
    public enum AbstentionType {
        VACANTION (1),      /* Ferie */
        PARENTAL_LEAVE (2), /* Congedo parentale */
        SICK_LEAVE (3),     /* Malattia */
        STRIKE (4);         /* Sciopero */

        private final int value;

        private AbstentionType(int value) {
            this.value = value;
        }

        public int getValue() {
            return this.value;
        }
    }

    /* Class attributes */
    private Boolean acceptanceStatus;
    private AbstentionType type;


    /**
     * Default constructor
     */
    public AbstentionRequest(int notifID) {
        super(notifID);

        this.acceptanceStatus = false;
        this.type = AbstentionType.VACANTION;
    }

    /**
     * Constructor used to wrap the data retrieved from database
     * 
     * @param title The title of the Notification
     * @param description The description of the Notification
     * @param notifID The ID of the Notification
     * @param receiverID The receiver id 
     * @param sender The sender id
     * @param incID If the notification ID needs to be incremented
     * @param accStat If the request was accepted or not
     * 
     */
    public AbstentionRequest(String title, String description, int notifID, int receiverID, int senderID, Boolean accStat, AbstentionType type) {
        super(title, description, receiverID, senderID, notifID);

        this.acceptanceStatus = accStat;
        this.type = type;
    }


    /**
     * Constructor to instantiate a new AbstentionRequest object to add to the DBMS, 
     * this method increment the global notification ID counter and assign it to
     * the new AbstentionRequest ID object
     * 
     * @param title The title of the Notification
     * @param description The description of the Notification
     * @param receiverID The receiver id 
     * @param sender The sender id
     * @param incID If the notification ID needs to be incremented
     * @param accStat If the request was accepted or not
     * 
     */
    public AbstentionRequest(String title, String description, int receiverID, int senderID, Boolean accStat, AbstentionType type) {
        super(title, description, receiverID, senderID);

        this.acceptanceStatus = accStat;
        this.type = type;
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
}
