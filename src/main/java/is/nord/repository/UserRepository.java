package is.nord.repository;

import is.nord.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * An interface which queries the database for user-related things
 * @Author Ólafur Georg Gylfason (ogg4@hi.is)
 * @Author Kári Snær Kárason (ksk12@hi.is)
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    // Fetches a single (or none) user by the specified username
    User findByUsername(String username);

    // Fetches all users sorted in alphabetical order by username
    List<User> findAllByOrderByUsername();

    // Fetches the users with the highest number of points
    List<User> findTop5ByOrderByPointsDesc();
}
