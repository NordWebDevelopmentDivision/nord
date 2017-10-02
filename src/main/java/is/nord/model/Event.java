package is.nord.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Entity
public class Event extends News {
    private String host;            // The company or host of the event
    private String location;        // The location of the event
    private int capacity;           // The maximum allowed attendance to the event
    private Date timeOfEvent;       // The time of the event
    private Date registrationOpens; // The time when registration for the event opens
    private Date registrationCloses; // The time when registration for the event closes
    private boolean isPriorityEvent; // Whether 2nd and 3rd year students have priority

    @OneToMany(mappedBy = "event")
    private List<Registration> registrations = new ArrayList<>();

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Date getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(Date timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    public Date getRegistrationOpens() {
        return registrationOpens;
    }

    public void setRegistrationOpens(Date registrationOpens) {
        this.registrationOpens = registrationOpens;
    }

    public Date getRegistrationCloses() {
        return registrationCloses;
    }

    public void setRegistrationCloses(Date registrationCloses) {
        this.registrationCloses = registrationCloses;
    }

    public boolean isPriorityEvent() {
        return isPriorityEvent;
    }

    public void setPriorityEvent(boolean priorityEvent) {
        isPriorityEvent = priorityEvent;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }
}
