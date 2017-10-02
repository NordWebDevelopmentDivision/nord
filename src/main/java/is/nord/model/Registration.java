package is.nord.model;

import javax.persistence.*;
import java.util.Date;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                    // The id of the registration

    @ManyToOne
    private Event event;                // The event registered to

    @ManyToOne
    private User user;                  // The user registered
    private Date timeOfRegistration;    // The time of the registration

    public Registration(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(Date timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }
}
