package is.nord.repository;

import is.nord.model.Event;
import is.nord.model.Registration;
import is.nord.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 * Author:
 *       Ólafur Georg Gylfason (ogg4@hi.is)
*/

/**
 * An interface which queries the database for registration-related things
 */
@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Long> {
    // Fetches a single (or none) registration containing the user and the event specified
    Registration findRegistrationByEventAndUser(Event event, User user);

    // Fetches all registrations for the specified event
    List<Registration> findRegistrationsByEvent(Event event);
}
