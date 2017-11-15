package is.nord.repository;

import is.nord.model.Event;
import is.nord.model.Registration;
import is.nord.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * An interface which queries the database for registration-related things
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 */
@Repository
public interface RegistrationRepository extends CrudRepository<Registration, Long> {
    // Fetches a single (or none) registration containing the user and the event specified
    Registration findRegistrationByEventAndUser(Event event, User user);

    Registration findOne(Long id);

    // Fetches all registrations for the specified event
    List<Registration> findRegistrationsByEvent(Event event);



    /*@Query(value = "SELECT user, COUNT(user) as total FROM Registration GROUP BY user ORDER BY COUNT(user) DESC", nativeQuery = true)
    List<TopEventUser> findTopEventUsers();
    List<User> getUsersOrderB*/
}
