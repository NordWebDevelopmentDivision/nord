package is.nord.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * Author:
 *       Kári Snær Kárason (ksk12@hi.is)
*/

@Entity
@Table(name="eventBan")
public class EventBan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // The id of the ban

    @ManyToOne
    private User user;                  // The user banned
    private LocalDateTime timeOfBan;    // The time of the ban
    private boolean currentlyBanned;    // The status of the ban

    public EventBan(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getTimeOfBan() {
        return timeOfBan;
    }

    public void setTimeOfBan(LocalDateTime timeOfBan) {
        this.timeOfBan = timeOfBan;
    }

    public boolean isCurrentlyBanned() {
        return currentlyBanned;
    }

    public void setCurrentlyBanned(boolean currentlyBanned) {
        this.currentlyBanned = currentlyBanned;
    }
}
