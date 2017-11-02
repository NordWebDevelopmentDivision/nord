package is.nord.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Entity
public class Event extends News {
    @NotEmpty(message="{event.host.notEmpty}")
    private String host;            // The company or host of the event

    @NotEmpty(message="{event.location.notEmpty}")
    private String location;        // The location of the event

    @URL(message="{event.linkLocation.URL}")
    @NotEmpty(message="{event.linkLocation.notEmpty}")
    private String linkLocation;    // The ja.is link

    private int capacity;           // The maximum allowed attendance to the event

    @NotNull(message="{event.timeOfEvent.notNull}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime timeOfEvent;       // The time of the event

    @NotNull(message="{event.registrationOpens.notNull}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registrationOpens; // The time when registration for the event opens

    @NotNull(message="{event.registrationCloses.notNull}")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime registrationCloses; // The time when registration for the event closes

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

    public String getLinkLocation() {
        return linkLocation;
    }

    public void setLinkLocation(String linkLocation) {
        this.linkLocation = linkLocation;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public LocalDateTime getTimeOfEvent() {
        return timeOfEvent;
    }

    public void setTimeOfEvent(LocalDateTime timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    public LocalDateTime getRegistrationOpens() {
        return registrationOpens;
    }

    public void setRegistrationOpens(LocalDateTime registrationOpens) {
        this.registrationOpens = registrationOpens;
    }

    public LocalDateTime getRegistrationCloses() {
        return registrationCloses;
    }

    public void setRegistrationCloses(LocalDateTime registrationCloses) {
        this.registrationCloses = registrationCloses;
    }

    public boolean getIsPriorityEvent() {
        return isPriorityEvent;
    }

    public void setIsPriorityEvent(boolean isPriorityEvent) {
        this.isPriorityEvent = isPriorityEvent;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }
}
