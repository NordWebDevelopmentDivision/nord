package is.nord.service;

import is.nord.model.Event;
import is.nord.model.Registration;
import is.nord.model.User;

/**
 * A service layer between the Registrationcontroller and the Registrationrepository
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 * @Author Kári Snær Kárason (ksk12@hi.is)
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

    // Returns the registration for a specific event and user
    Registration findRegistrationByEventAndUser(Event event, User user);

    // Returns all registrations to the specified event
    Iterable<Registration> findRegistrationsByEvent(Event event);

    // Delete all registrations in which are passed to the method
    // This is used when deleting an event which contains registrations
    void deleteAll(Iterable<Registration> registrations);

    // Finds registration by its Id
    Registration findRegistrationById(Long id);

    // Return whether a certain registration is confirmed
    boolean isConfirmed(Long id);

    // Updates information about a certain registration
    void update(Registration registration);
}
