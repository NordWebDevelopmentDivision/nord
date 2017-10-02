package is.nord.service;

import is.nord.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/*
 * Author:
 *       Chris Ramacciotti, a teacher at teamtreehouse.com
*/

/**
 * A service layer for user objects
 */
public interface UserService extends UserDetailsService {

    Iterable<User> findAll();

    // Returns a user with the specified username. Throws exception if that user does not exist
    User findByUsername(String username);

    // Saves the specified user item through a call to the userRepository
    void save(User user);
}
