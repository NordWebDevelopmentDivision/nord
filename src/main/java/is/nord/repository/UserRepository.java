package is.nord.repository;

import is.nord.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/*
 * Author:
 *       Chris Ramacciotti, a teacher at teamtreehouse.com
*/

/**
 * An interface which queries the database for user-related things
 */
@Repository
public interface UserRepository extends CrudRepository<User,Long> {
    // Fetches a single (or none) user by the specified username
    User findByUsername(String username);
}
