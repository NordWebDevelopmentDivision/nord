package is.nord.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

@Entity
@Table(name="registration")
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;                                // The id of the registration

    @ManyToOne
    private Event event;                            // The event registered to

    @ManyToOne
    private User user;                              // The user registered
    private LocalDateTime timeOfRegistration;       // The time of the registration
    private boolean confirmed;                      // If the user showed up to the event or not
    private boolean questionAnswer;                            // If the user wants to take the bus

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

    public LocalDateTime getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(LocalDateTime timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }

    public boolean getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public boolean getQuestionAnswer() {
        return questionAnswer;
    }

    public void setQuestionAnswer(boolean questionAnswer) {
        this.questionAnswer = questionAnswer;
    }
}
