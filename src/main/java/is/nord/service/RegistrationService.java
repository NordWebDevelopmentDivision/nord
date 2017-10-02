package is.nord.service;

import is.nord.model.Event;
import is.nord.model.Registration;
import is.nord.model.User;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

/**
 * A service layer between the Registrationcontroller and the Registrationrepository
 */
public interface RegistrationService {
    // Returns all the registrations
    Iterable<Registration> findAll();

    // Saves the specified registration through a call to the newsRepository
    void save(Registration registration);

    // Deletes the registration for the specified user to the specified event
    void delete(Event event, User user);

    // Returns whether the specified user is registered for the specified event
    boolean isRegisteredForEvent(Event event, User user);

    // Returns all registrations to the specified event
    Iterable<Registration> findRegistrationsByEvent(Event event);
}
